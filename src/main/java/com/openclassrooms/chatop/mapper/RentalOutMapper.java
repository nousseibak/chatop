package com.openclassrooms.chatop.mapper;

import com.openclassrooms.chatop.dto.RentalOutDto;
import com.openclassrooms.chatop.model.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RentalOutMapper {

    @Mapping(source = "owner", target = "owner")
    RentalOutDto rentalToRentalOutDto(Rental rental);

    Rental RentalOutDtoToRental(RentalOutDto RentalOutDto);

    List<RentalOutDto> rentalsToRentalsOutDto(List<Rental> rentals);

    List<Rental> rentalsOutDtoToRentals(List<RentalOutDto> rentalsOutDto);
}
