package ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.model.Role;
import ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.service.RoleService;

import java.util.Collection;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<Collection<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping
    public ResponseEntity<Role> getRoleById(@RequestParam Long id) {
        Role role = roleService.getRoleById(id);
        return ResponseEntity.ok(role);
    }
}
