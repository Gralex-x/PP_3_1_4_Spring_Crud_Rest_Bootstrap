package ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.model.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String role);
}
