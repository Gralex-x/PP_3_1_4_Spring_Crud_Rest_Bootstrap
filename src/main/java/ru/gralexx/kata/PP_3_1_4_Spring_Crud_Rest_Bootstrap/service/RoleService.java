package ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.service;



import ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.model.Role;

import java.util.Collection;
import java.util.List;


public interface RoleService {
    Role getRoleByName(String name);

    Collection<Role> getRolesByName(Collection<String> names);

    List<Role> getAllRoles();

    Role getRoleById(Long id);

    Collection<Role> findAll();
}
