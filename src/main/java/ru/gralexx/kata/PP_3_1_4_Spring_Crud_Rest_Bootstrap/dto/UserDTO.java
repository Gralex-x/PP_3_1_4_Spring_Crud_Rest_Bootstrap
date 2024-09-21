package ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    @NotBlank(message = "Username cannot be empty")
    @Size(min = 2, max = 16, message = "Name should be between 2 and 16 characters")
    private String username;

    @Min(value = 0, message = "Age should be greater than 0")
    private Integer age;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, max = 32)
    private String password;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, max = 32)
    private String confirmPassword;

    @NotEmpty(message = "User must have at least one role")
    private Set<String> roles;

}
