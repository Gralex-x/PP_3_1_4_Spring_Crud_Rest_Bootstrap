package ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.dto.UserDTO;
import ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.mapper.UserMapper;
import ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.model.User;
import ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.security.UserDetailsImpl;
import ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.service.UserService;
import ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.util.UserNotCreatedException;
import ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.util.UserNotUpdatedException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminsController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public AdminsController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;

    }

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> users() {
        return new ResponseEntity<>(
                userService.getUsers().stream()
                        .map(userMapper::mapToUserDTO)
                        .toList(),
                HttpStatus.OK);
    }

    @GetMapping("/current")
    public ResponseEntity<UserDTO> currentUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new ResponseEntity<>(userMapper.mapToUserDTO(userDetails.user()), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<UserDTO> user(@RequestParam Long id) {
        return new ResponseEntity<>(userMapper.mapToUserDTO(userService.getUserById(id)), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid UserDTO userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMsg = bindingResult.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getField() + " - " + fieldError.getDefaultMessage())
                    .collect(Collectors.joining(";"));
            throw new UserNotCreatedException(errorMsg);
        }
        userService.addUser(userMapper.mapToUser(userDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping(value = "/update", params = "id")
    public ResponseEntity<HttpStatus> update(@RequestParam("id") Long id, @RequestBody @Valid UserDTO userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMsg = bindingResult.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getField() + " - " + fieldError.getDefaultMessage())
                    .collect(Collectors.joining(";"));
            throw new UserNotUpdatedException(errorMsg);
        }

        User user = userMapper.mapToUser(userDto);
        user.setId(id);
        userService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> delete(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
