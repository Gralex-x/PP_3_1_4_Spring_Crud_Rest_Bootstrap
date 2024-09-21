package ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.service;



import ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.model.Role;

import java.util.Collection;
import java.util.List;
import java.util.Set;


public interface RoleService {
    Role getRoleByName(String name);

    Collection<Role> getRolesByName(Collection<String> names);

    List<Role> getAllRoles();
}
