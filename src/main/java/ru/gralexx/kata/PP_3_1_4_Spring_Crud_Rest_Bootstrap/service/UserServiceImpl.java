package ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.dao.UserRepository;
import ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.model.User;
import ru.gralexx.kata.PP_3_1_4_Spring_Crud_Rest_Bootstrap.util.UserNotFoundException;

import java.util.List;

import static io.micrometer.common.util.StringUtils.isBlank;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userDao) {
        this.userRepository = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        existingUser.setAge(user.getAge());
        existingUser.setUsername(user.getUsername());
        String password = user.getPassword();
        if (password != null && !isBlank(password)) {
            existingUser.setPassword(password);
        }
        existingUser.setRoles(user.getRoles());
        userRepository.save(existingUser);
    }


    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
