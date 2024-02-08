package com.openclassrooms.chatop.mapper;

import com.openclassrooms.chatop.dto.UserDto;
import com.openclassrooms.chatop.model.DbUser;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserDto userToUserDto(DbUser dbUser);
    DbUser userDtoToUser(UserDto userDto);
    List<UserDto> usersToUsersDto(List<DbUser> dbUsers);
    List<DbUser> usersDtoToUsers(List<UserDto> usersDto);

}
