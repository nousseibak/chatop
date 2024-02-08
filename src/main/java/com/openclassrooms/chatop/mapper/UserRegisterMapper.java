package com.openclassrooms.chatop.mapper;

import com.openclassrooms.chatop.dto.UserRegisterDto;
import com.openclassrooms.chatop.model.DbUser;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserRegisterMapper {

    UserRegisterDto userToUserRegisterDto(DbUser dbUser);
    DbUser userRegisterDtoToUser(UserRegisterDto userRegisterDto);
    List<UserRegisterDto> usersToUsersRegisterDto(List<DbUser> dbUsers);
    List<DbUser> usersRegisterDtoToUsers(List<UserRegisterDto> usersRegisterDto);

}
