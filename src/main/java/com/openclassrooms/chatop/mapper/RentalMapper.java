package com.openclassrooms.chatop.mapper;

import com.openclassrooms.chatop.dto.RentalDto;
import com.openclassrooms.chatop.model.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RentalMapper {

    RentalDto rentalToRentalDto(Rental rental);

    Rental rentalDtoToRental(RentalDto rentalDto);

    List<RentalDto> rentalsToRentalsDto(List<Rental> rentals);

    List<Rental> rentalsDtoToRentals(List<RentalDto> rentalsDto);
}
