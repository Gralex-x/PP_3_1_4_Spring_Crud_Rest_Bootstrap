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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.model.User;
import ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.security.UserDetailsImpl;
import ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.service.UserService;
import ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.util.UserNotCreatedException;
import ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.util.UserNotUpdatedException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class AdminsController {

    private final UserService userService;

    @Autowired
    public AdminsController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<User>> users() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/current")
    public ResponseEntity<User> currentUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new ResponseEntity<>(userDetails.user(), HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> user(@PathVariable("username") String username) {
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }

    @PostMapping("/addNew")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMsg = bindingResult.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getField() + " - " + fieldError.getDefaultMessage())
                    .collect(Collectors.joining(";"));
            throw new UserNotCreatedException(errorMsg);
        }
        userService.addUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{username}")
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMsg = bindingResult.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getField() + " - " + fieldError.getDefaultMessage())
                    .collect(Collectors.joining(";"));

            throw new UserNotUpdatedException(errorMsg);
        }
        userService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("username") String username) {
        userService.deleteUserByUsername(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
