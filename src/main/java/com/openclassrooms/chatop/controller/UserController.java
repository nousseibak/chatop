package com.openclassrooms.chatop.controller;

import com.openclassrooms.chatop.dto.UserDto;
import com.openclassrooms.chatop.mapper.UserMapper;
import com.openclassrooms.chatop.model.DbUser;
import com.openclassrooms.chatop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User Controller", description = "APIs for managing users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/{userId}")
    @Operation(summary = "Get user by ID", description = "Get details of a user by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserDto> getUserById(
            @Parameter(description = "ID of the user to be retrieved", required = true)
            @PathVariable Integer userId) {

        DbUser dbUser = userService.getUserById(userId);

        if (dbUser != null) {
            UserDto userDto = userMapper.userToUserDto(dbUser);
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
