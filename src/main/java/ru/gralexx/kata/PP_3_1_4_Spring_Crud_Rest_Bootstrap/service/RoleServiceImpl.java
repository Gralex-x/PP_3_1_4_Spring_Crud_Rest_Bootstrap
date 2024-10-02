package ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.dao.RoleRepository;
import ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.model.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException(name));
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Role> getRolesByName(Collection<String> names) {
        Collection<Role> roles = new ArrayList<>();
        names.forEach(name -> roles.add(getRoleByName(name)));
        return roles;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Collection<Role> findAll() {
        return roleRepository.findAll();
    }

}
