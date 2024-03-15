package com.openclassrooms.chatop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.chatop.config.media.FileHelper;
import com.openclassrooms.chatop.dto.ErrorResDto;
import com.openclassrooms.chatop.dto.RentalInDto;
import com.openclassrooms.chatop.dto.RentalOutDto;
import com.openclassrooms.chatop.mapper.RentalInMapper;
import com.openclassrooms.chatop.mapper.RentalOutMapper;
import com.openclassrooms.chatop.model.DbUser;
import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.service.RentalService;
import com.openclassrooms.chatop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/rentals")
@Tag(name = "Rental Controller", description = "APIs for managing rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @Autowired
    private RentalInMapper rentalMapper;

    @Autowired
    private RentalOutMapper rentalOutMapper;

    @Autowired
    private UserService userService;


    @Operation(summary = "Get all rentals", description = "Get a list of all rentals")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rentals found", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RentalInDto.class))
            }),
            @ApiResponse(responseCode = "403", description = "Forbidden")   })
    @GetMapping("")
    public ResponseEntity<List<RentalOutDto>> getAllRentals() {
        List<Rental> rentals = rentalService.getAllRentals();
        List<RentalOutDto> rentalOutDtos = rentalOutMapper.rentalsToRentalsOutDto(rentals);
        return ResponseEntity.ok(rentalOutDtos);
    }

    @GetMapping("/{rentalId}")
    @Operation(summary = "Get rental by ID", description = "Get details of a rental by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental found", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RentalInDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Rental not found")
    })
    public ResponseEntity<RentalOutDto> getRentalById(
            @Parameter(description = "ID of the rental to be retrieved", required = true)
            @PathVariable Integer rentalId) {

        Rental rental = rentalService.getRentalById(rentalId);

        if (rental != null) {
            RentalOutDto rentalOutDto = rentalOutMapper.rentalToRentalOutDto(rental);
            return ResponseEntity.ok(rentalOutDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value="/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Create rental", description = "Create a new rental")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental created"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    public ResponseEntity createRental(@RequestPart("rentalDto") String rentalDto, @RequestPart(value="picture", required = true)  MultipartFile media) throws IOException {

        RentalInDto rentalInDto=new ObjectMapper().readValue(rentalDto, RentalInDto.class);
        if (rentalInDto.getName() == null || rentalInDto.getSurface() == null || rentalInDto.getPrice() == null || rentalInDto.getDescription() == null) {
            return ResponseEntity.badRequest().body("400 Bad Request: All variables must be provided");
        }
        Rental rental = rentalMapper.rentalDtoToRental(rentalInDto);
        rental.setOwner(userService.getUserById(rentalInDto.getOwnerId()));

        String fileName = StringUtils.cleanPath(media.getOriginalFilename());
        rental.setPicture(fileName);

        Rental rentalcreated= rentalService.saveRental(rental);
        String uploadDir = "media/" + rentalcreated.getId();
        FileHelper.saveFile(uploadDir, fileName, media);
        return ResponseEntity.ok().body(new ErrorResDto("Rental created!"));
    }

    @PutMapping(value="/{rentalId}")
    @Operation(summary = "Update rental", description = "Update an existing rental")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Rental not found")
    })
    public ResponseEntity updateRental(
            @Parameter(description = "ID of the rental to be updated", required = true)
            @PathVariable Integer rentalId, @RequestBody RentalInDto rentalInDto) {

        if (rentalInDto.getName() == null || rentalInDto.getSurface() == null || rentalInDto.getPrice() == null || rentalInDto.getDescription() == null) {
            return ResponseEntity.badRequest().body("400 Bad Request: All variables must be provided");
        }

        Rental existingRental = rentalService.getRentalById(rentalId);

        if (existingRental == null) {
            return ResponseEntity.notFound().build();
        }

        existingRental.setName(rentalInDto.getName());
        existingRental.setSurface(rentalInDto.getSurface());
        existingRental.setPrice(rentalInDto.getPrice());
        existingRental.setDescription(rentalInDto.getDescription());

        rentalService.saveRental(existingRental);

        return ResponseEntity.ok().body(new ErrorResDto("Rental updated!"));
    }
}
