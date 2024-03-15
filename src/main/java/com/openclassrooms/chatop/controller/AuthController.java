package com.openclassrooms.chatop.controller;

import com.openclassrooms.chatop.dto.ErrorResDto;
import com.openclassrooms.chatop.dto.UserDto;
import com.openclassrooms.chatop.dto.UserLoginDto;
import com.openclassrooms.chatop.dto.UserRegisterDto;
import com.openclassrooms.chatop.mapper.UserLoginMapper;
import com.openclassrooms.chatop.mapper.UserMapper;
import com.openclassrooms.chatop.mapper.UserRegisterMapper;
import com.openclassrooms.chatop.model.CustomUserDetails;
import com.openclassrooms.chatop.model.DbUser;
import com.openclassrooms.chatop.model.ErrorRes;
import com.openclassrooms.chatop.service.AuthService;
import com.openclassrooms.chatop.service.CustomUserDetailsService;
import com.openclassrooms.chatop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication Controller", description = "APIs for user authentication and authorization")
public class AuthController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserLoginMapper userLoginMapper;

    @Autowired
    private UserRegisterMapper userRegisterMapper;

    @Autowired
    private AuthService authService;

    @Autowired
    private CustomUserDetailsService detailsService;


    @Operation(summary = "Register user", description = "Register a new user with email, name, and password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody UserRegisterDto userRegisterDto) {
        if (userRegisterDto.getEmail() == null || userRegisterDto.getName() == null || userRegisterDto.getPassword() == null) {
            return ResponseEntity.badRequest().body("400 Bad Request: All variables must be provided");
        }
        DbUser user=userService.findByEmail(userRegisterDto.getEmail());
        if (user!=null){
            return ResponseEntity.badRequest().body("400 Bad Request: Email exists already");
        }

        return ResponseEntity.ok(authService.register(userRegisterDto));
    }


    @Operation(summary = "Login user", description = "Authenticate and log in a user with email and password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged in successfully", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginDto loginDto) {
        if (loginDto.getEmail() == null || loginDto.getPassword() == null) {
            return ResponseEntity.badRequest().body("400 Bad Request: All variables must be provided");
        }
        DbUser user = userService.findByEmail(loginDto.getEmail());
        if (user == null) {
            return ResponseEntity.badRequest().body("400 Bad Request: Invalid username or password");
        }
        try {
            return ResponseEntity.ok(authService.login(loginDto));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResDto("error"));
        }

    }


    @Operation(summary = "Get current user", description = "Get details of the currently authenticated user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User details retrieved successfully", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser() {
        // Récupérer l'authentification à partir du contexte de sécurité
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Vérifier si l'utilisateur est authentifié
        if (authentication != null && authentication.isAuthenticated()) {
            // Récupérer les détails de l'utilisateur à partir de l'authentification
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            UserDto userDto = new UserDto();
            userDto.setEmail(userDetails.getUsername());
            userDto.setName(userDetails.getName());
            userDto.setId(userDetails.getId());
            userDto.setCreatedAt(userDetails.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            userDto.setUpdatedAt(userDetails.getUpdatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

            return ResponseEntity.ok(userDto);
        } else {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


}

