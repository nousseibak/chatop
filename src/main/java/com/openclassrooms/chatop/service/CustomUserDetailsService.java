package com.openclassrooms.chatop.service;


import com.openclassrooms.chatop.model.CustomUserDetails;
import com.openclassrooms.chatop.model.DbUser;
import com.openclassrooms.chatop.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        DbUser user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setId(user.getId());
        userDetails.setEmail(user.getEmail());
        userDetails.setName(user.getName());
        userDetails.setPassword(user.getPassword());
        userDetails.setCreatedAt(user.getCreatedAt());
        userDetails.setUpdatedAt(user.getUpdatedAt());

        return userDetails;
    }
}