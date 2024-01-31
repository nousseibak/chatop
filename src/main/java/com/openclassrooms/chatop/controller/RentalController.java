package com.openclassrooms.chatop.controller;

import com.openclassrooms.chatop.dto.RentalDto;
import com.openclassrooms.chatop.mapper.RentalMapper;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
@Tag(name = "Rental Controller", description = "APIs for managing rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @Autowired
    private RentalMapper rentalMapper;

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "Get all rentals", description = "Get a list of all rentals")
    public ResponseEntity<List<RentalDto>> getAllRentals() {
        List<Rental> rentals = rentalService.getAllRentals();
        List<RentalDto> rentalDtos = rentalMapper.rentalsToRentalsDto(rentals);
        return ResponseEntity.ok(rentalDtos);
    }

    @GetMapping("/{rentalId}")
    @Operation(summary = "Get rental by ID", description = "Get details of a rental by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental found", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RentalDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Rental not found")
    })
    public ResponseEntity<RentalDto> getRentalById(
            @Parameter(description = "ID of the rental to be retrieved", required = true)
            @PathVariable Integer rentalId) {

        Rental rental = rentalService.getRentalById(rentalId);

        if (rental != null) {
            RentalDto rentalDto = rentalMapper.rentalToRentalDto(rental);
            return ResponseEntity.ok(rentalDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Create rental", description = "Create a new rental")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental created"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    public ResponseEntity<String> createRental(@RequestBody RentalDto rentalDto) {

        if (rentalDto.getName() == null || rentalDto.getSurface() == null || rentalDto.getPrice() == null || rentalDto.getDescription() == null) {
            return ResponseEntity.badRequest().body("400 Bad Request: All variables must be provided");
        }

        Rental rental = rentalMapper.rentalDtoToRental(rentalDto);
        rentalService.saveRental(rental);

        return ResponseEntity.ok("Rental created!");
    }

    @PutMapping("/{rentalId}")
    @Operation(summary = "Update rental", description = "Update an existing rental")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Rental not found")
    })
    public ResponseEntity<String> updateRental(
            @Parameter(description = "ID of the rental to be updated", required = true)
            @PathVariable Integer rentalId, @RequestBody RentalDto rentalDto) {

        if (rentalDto.getName() == null || rentalDto.getSurface() == null || rentalDto.getPrice() == null || rentalDto.getDescription() == null) {
            return ResponseEntity.badRequest().body("400 Bad Request: All variables must be provided");
        }

        Rental existingRental = rentalService.getRentalById(rentalId);

        if (existingRental == null) {
            return ResponseEntity.notFound().build();
        }

        existingRental.setName(rentalDto.getName());
        existingRental.setSurface(rentalDto.getSurface());
        existingRental.setPrice(rentalDto.getPrice());
        existingRental.setDescription(rentalDto.getDescription());

        rentalService.saveRental(existingRental);

        return ResponseEntity.ok("Rental updated!");
    }
}
