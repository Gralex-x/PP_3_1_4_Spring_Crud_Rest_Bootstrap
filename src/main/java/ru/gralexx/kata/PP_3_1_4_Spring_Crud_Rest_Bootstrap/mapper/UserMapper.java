package ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.mapper;


import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.dto.UserDTO;
import ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.model.Role;
import ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.model.User;
import ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.service.RoleService;
import ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.util.BadPasswordException;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Autowired
    public UserMapper(PasswordEncoder passwordEncoder, RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    public User mapToUser(@NonNull UserDTO userDTO) {
        
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setAge(userDTO.getAge());
        user.setRoles(getRolesFromDTO(userDTO.getRoles()));

        String password = userDTO.getPassword();
        if (password != null && !password.isBlank()) {
            validatePasswords(password, userDTO.getConfirmPassword());
            user.setPassword(passwordEncoder.encode(password));
        }

        return user;
    }

    private void validatePasswords(String password, String confirmPassword) {
        if (password != null && confirmPassword != null && !password.equals(confirmPassword)) {
            throw new BadPasswordException("Passwords do not match");
        }
    }

    private Set<Role> getRolesFromDTO(Set<String> roleNames) {
        Set<Role> roles = new HashSet<>(roleService.getRolesByName(roleNames));

        if (roles.size() != roleNames.size()) {
            throw new IllegalArgumentException("One or more roles are invalid");
        }

        return roles;
    }

    @Transactional(readOnly = true, noRollbackFor = RuntimeException.class)
    public UserDTO mapToUserDTO(@NonNull User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setAge(user.getAge());
        Set<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
        userDTO.setRoles(roles);
        return userDTO;
    }
}
