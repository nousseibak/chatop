//package com.openclassrooms.chatop.controller;
//
//import com.openclassrooms.chatop.dto.UserDto;
//import com.openclassrooms.chatop.dto.UserLoginDto;
//import com.openclassrooms.chatop.dto.UserRegisterDto;
//import com.openclassrooms.chatop.mapper.UserLoginMapper;
//import com.openclassrooms.chatop.mapper.UserMapper;
//import com.openclassrooms.chatop.mapper.UserRegisterMapper;
//import com.openclassrooms.chatop.model.User;
//import com.openclassrooms.chatop.service.UserService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth")
//@Tag(name = "Authentication Controller", description = "APIs for user authentication and authorization")
//public class AuthController {
//
//    //@Autowired
//    //private AuthenticationManager authenticationManager;
//
//    //@Autowired
//    //private JwtTokenProvider jwtTokenProvider;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private UserMapper userMapper;
//
//    @Autowired
//    private UserLoginMapper userLoginMapper;
//
//    @Autowired
//    private UserRegisterMapper userRegisterMapper;
//
//    @PostMapping("/register")
//    @Operation(summary = "Register user", description = "Register a new user with email, name, and password")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "User registered successfully", content = @Content),
//            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
//    })
//    public ResponseEntity<String> registerUser(@RequestBody UserRegisterDto userRegisterDto) {
//        if (userRegisterDto.getEmail() == null || userRegisterDto.getName() == null || userRegisterDto.getPassword() == null) {
//            return ResponseEntity.badRequest().body("400 Bad Request: All variables must be provided");
//        }
//
//        User user = userRegisterMapper.userRegisterDtoToUser(userRegisterDto);
//        userService.saveUser(user);
//
//        //String token = jwtTokenProvider.createToken(user.getEmail());
//        return ResponseEntity.ok("token");
//    }
//
//    @PostMapping("/login")
//    @Operation(summary = "Login user", description = "Authenticate and log in a user with email and password")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "User logged in successfully", content = @Content),
//            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
//    })
//    public ResponseEntity<String> loginUser(@RequestBody UserLoginDto userLoginDto) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword())
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        //String token = jwtTokenProvider.createToken(userLoginDto.getEmail());
//        return ResponseEntity.ok("token");
//    }
//
//    @GetMapping("/me")
//    @Operation(summary = "Get current user", description = "Get details of the currently authenticated user")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "User details retrieved successfully", content = {
//                    @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = UserDto.class))
//            }),
//            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
//    })
//    public ResponseEntity<UserDto> getCurrentUser() {
//        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        UserDto userDto = userMapper.userToUserDto(authenticatedUser);
//        return ResponseEntity.ok(userDto);
//    }
//}
