package com.openclassrooms.chatop.service;


import com.openclassrooms.chatop.model.CustomUserDetails;
import com.openclassrooms.chatop.model.DbUser;
import com.openclassrooms.chatop.repository.UserRepository;
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
        List<String> roles = new ArrayList<>();
        roles.add("USER");
/*        UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                        .username(user.getEmail())
                        .password(user.getPassword())
                        .roles(roles.toArray(new String[0]))
                             .build();*/
        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setId(user.getId());
        userDetails.setEmail(user.getEmail());
        userDetails.setName(user.getName());
        userDetails.setPassword(user.getPassword());
        userDetails.setRoles(roles);
        userDetails.setCreatedAt(user.getCreatedAt());
        userDetails.setUpdatedAt(user.getUpdatedAt());

        return userDetails;
    }
}