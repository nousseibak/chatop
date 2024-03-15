package com.openclassrooms.chatop.mapper;

import com.openclassrooms.chatop.dto.UserDto;
import com.openclassrooms.chatop.model.DbUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "timestampToLocalDate")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "timestampToLocalDate")
    UserDto userToUserDto(DbUser dbUser);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "localDateToTimestamp")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "localDateToTimestamp")
    DbUser userDtoToUser(UserDto userDto);

    List<UserDto> usersToUsersDto(List<DbUser> dbUsers);
    List<DbUser> usersDtoToUsers(List<UserDto> usersDto);

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
