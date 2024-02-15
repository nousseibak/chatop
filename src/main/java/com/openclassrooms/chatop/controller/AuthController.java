package com.openclassrooms.chatop.controller;

import com.openclassrooms.chatop.dto.JWTAuthResponse;
import com.openclassrooms.chatop.dto.UserLoginDto;
import com.openclassrooms.chatop.dto.UserRegisterDto;
import com.openclassrooms.chatop.mapper.UserLoginMapper;
import com.openclassrooms.chatop.mapper.UserMapper;
import com.openclassrooms.chatop.mapper.UserRegisterMapper;
import com.openclassrooms.chatop.model.DbUser;
import com.openclassrooms.chatop.model.ErrorRes;
import com.openclassrooms.chatop.service.AuthService;
import com.openclassrooms.chatop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

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



    @Operation(summary = "Register user", description = "Register a new user with email, name, and password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterDto userRegisterDto) {
        if (userRegisterDto.getEmail() == null || userRegisterDto.getName() == null || userRegisterDto.getPassword() == null) {
            return ResponseEntity.badRequest().body("400 Bad Request: All variables must be provided");
        }
        DbUser user=userService.findByEmail(userRegisterDto.getEmail());
        if (user!=null){
            return ResponseEntity.badRequest().body("400 Bad Request: Email exists already");
        }
        String response = authService.register(userRegisterDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
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
        DbUser user=userService.findByEmail(loginDto.getEmail());
        if (user==null){
            return ResponseEntity.badRequest().body("400 Bad Request: Invalid username or password");
        }
     try {
        return ResponseEntity.ok(authService.login(loginDto));

    }catch (Exception e){
        ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}

//    @GetMapping("/me")
//    @Operation(summary = "Get current user", description = "Get details of the currently authenticated user")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "User details retrieved successfully", content = {
//                    @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = UserDto.class))
//            }),
//            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
//    })
//
//
//    }
}
