package com.openclassrooms.chatop.mapper;

import com.openclassrooms.chatop.dto.RentalInDto;
import com.openclassrooms.chatop.model.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RentalMapper {

    RentalInDto rentalToRentalDto(Rental rental);

    Rental rentalDtoToRental(RentalInDto rentalInDto);

    List<RentalInDto> rentalsToRentalsDto(List<Rental> rentals);

    List<Rental> rentalsDtoToRentals(List<RentalInDto> rentalsDto);
}
