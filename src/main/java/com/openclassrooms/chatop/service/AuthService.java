package com.openclassrooms.chatop.service;


import com.openclassrooms.chatop.config.security.JwtUtil;
import com.openclassrooms.chatop.dto.UserLoginDto;
import com.openclassrooms.chatop.dto.UserRegisterDto;
import com.openclassrooms.chatop.dto.UserReponseDto;
import com.openclassrooms.chatop.mapper.UserLoginMapper;
import com.openclassrooms.chatop.model.DbUser;
import com.openclassrooms.chatop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;
    private UserLoginMapper userLogginMapper;


    public UserReponseDto login(UserLoginDto loginDto) {

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        String email = authentication.getName();
        DbUser user = new DbUser(email,"");
        return new UserReponseDto(jwtUtil.createToken(user));
    }


    public UserReponseDto register(UserRegisterDto registerDto) {

        DbUser user = new DbUser();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        userRepository.save(user);
        return new UserReponseDto(jwtUtil.createToken(user));
    }


}