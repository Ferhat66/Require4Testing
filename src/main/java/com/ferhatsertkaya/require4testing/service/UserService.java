package com.ferhatsertkaya.require4testing.service;

import com.ferhatsertkaya.require4testing.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    void deleteUser(Long id);
}