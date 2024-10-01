package ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    @NotBlank(message = "Username cannot be empty")
    @Size(min = 2, max = 16, message = "Name should be between 2 and 16 characters")
    private String username;

    @Column(name = "age", nullable = false)
    @Min(value = 0, message = "Age should be greater than 0")
    @Max(value = 130, message = "Too big age")
    private Integer age;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password cannot be empty")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @NotEmpty(message = "User must have at least one role")
    @ToString.Exclude
    private Set<Role> roles;

    public User(String username, Integer age, String password, Set<Role> roles) {
        this.username = username;
        this.age = age;
        this.password = password;
        this.roles = roles;
    }

    public void setRole(Role role) {
        this.roles.add(role);
    }
}
