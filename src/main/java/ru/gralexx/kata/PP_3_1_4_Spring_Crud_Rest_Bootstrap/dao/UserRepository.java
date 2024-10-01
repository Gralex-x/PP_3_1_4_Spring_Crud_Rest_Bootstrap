package ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.model.User;


import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

}
