package com.openclassrooms.chatop.mapper;

import com.openclassrooms.chatop.dto.UserDto;
import com.openclassrooms.chatop.dto.UserLoginDto;
import com.openclassrooms.chatop.dto.UserRegisterDto;
import com.openclassrooms.chatop.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserLoginMapper {

    UserLoginDto userToUserLoginDto(User user);
    User userLoginDtoToUser(UserLoginDto userLoginDto);
    List<UserLoginDto> usersToUsersLoginDto(List<User> users);
    List<User> usersLoginDtoToUsers(List<UserLoginDto> usersLoginDto);
}
