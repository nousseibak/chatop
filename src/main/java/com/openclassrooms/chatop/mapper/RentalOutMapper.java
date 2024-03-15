package com.openclassrooms.chatop.mapper;

import com.openclassrooms.chatop.dto.RentalOutDto;
import com.openclassrooms.chatop.model.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RentalOutMapper {

    @Mapping(source = "owner.id", target = "owner")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "timestampToLocalDate")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "timestampToLocalDate")
    RentalOutDto rentalToRentalOutDto(Rental rental);

    @Mapping(source = "owner", target = "owner.id")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "localDateToTimestamp")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "localDateToTimestamp")
    Rental RentalOutDtoToRental(RentalOutDto RentalOutDto);

    List<RentalOutDto> rentalsToRentalsOutDto(List<Rental> rentals);

    List<Rental> rentalsOutDtoToRentals(List<RentalOutDto> rentalsOutDto);

    // Méthode de mapping personnalisée pour convertir Timestamp en LocalDate
    @Named("timestampToLocalDate")
    default LocalDate timestampToLocalDate(Timestamp timestamp) {
        return timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    // Méthode de mapping personnalisée pour convertir LocalDate en Timestamp
    @Named("localDateToTimestamp")
    default Timestamp localDateToTimestamp(LocalDate localDate) {
        return Timestamp.valueOf(localDate.atStartOfDay());
    }
}
