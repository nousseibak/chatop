package com.openclassrooms.chatop.mapper;

import com.openclassrooms.chatop.dto.UserLoginDto;
import com.openclassrooms.chatop.model.DbUser;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserLoginMapper {

    UserLoginDto userToUserLoginDto(DbUser dbUser);
    DbUser userLoginDtoToUser(UserLoginDto userLoginDto);
    List<UserLoginDto> usersToUsersLoginDto(List<DbUser> dbUsers);
    List<DbUser> usersLoginDtoToUsers(List<UserLoginDto> usersLoginDto);
}
