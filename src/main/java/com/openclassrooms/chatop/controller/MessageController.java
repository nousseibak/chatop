package com.openclassrooms.chatop.controller;

import com.openclassrooms.chatop.dto.ErrorResDto;
import com.openclassrooms.chatop.dto.MessageDto;
import com.openclassrooms.chatop.mapper.MessageMapper;
import com.openclassrooms.chatop.model.DbUser;
import com.openclassrooms.chatop.model.Message;
import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.service.MessageService;
import com.openclassrooms.chatop.service.RentalService;
import com.openclassrooms.chatop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
@Tag(name = "Message Controller", description = "APIs for managing messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RentalService rentalService;

    @PostMapping("/")
    @Operation(summary = "Create a message", description = "Create a new message for a rental")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message sent with success"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "User or Rental not found")
    })
    public ResponseEntity createMessage(
            @Parameter(description = "Message details", required = true)
            @RequestBody MessageDto messageDto) {

        if (messageDto.getMessage() == null || messageDto.getUserId() == null || messageDto.getRentalId() == null) {
            return ResponseEntity.badRequest().body("400 Bad Request: All variables must be provided");
        }

        DbUser user=userService.getUserById(messageDto.getUserId());
        Rental rental=rentalService.getRentalById(messageDto.getRentalId());

        if (user == null || rental  == null) {
            return ResponseEntity.badRequest().body("400 Bad Request: User or Rental not found");
        }

        Message message = messageMapper.messageDtoToMessage(messageDto);
        message.setDbUser(user);
        message.setRental(rental);
        messageService.saveMessage(message);

        return ResponseEntity.ok().body(new ErrorResDto("Message sent with success"));
    }
}
