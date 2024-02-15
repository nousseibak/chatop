package com.openclassrooms.chatop.service;

import com.openclassrooms.chatop.model.DbUser;
import com.openclassrooms.chatop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<DbUser> getAllUsers() {
        return userRepository.findAll();
    }

    public DbUser getUserById(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public DbUser saveUser(DbUser dbUser) {
        return userRepository.save(dbUser);
    }

    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }


    public DbUser findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
