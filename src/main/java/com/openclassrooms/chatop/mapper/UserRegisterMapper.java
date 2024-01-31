package com.openclassrooms.chatop.mapper;

import com.openclassrooms.chatop.dto.UserDto;
import com.openclassrooms.chatop.dto.UserLoginDto;
import com.openclassrooms.chatop.dto.UserRegisterDto;
import com.openclassrooms.chatop.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserRegisterMapper {

    UserRegisterDto userToUserRegisterDto(User user);
    User userRegisterDtoToUser(UserRegisterDto userRegisterDto);
    List<UserRegisterDto> usersToUsersRegisterDto(List<User> users);
    List<User> usersRegisterDtoToUsers(List<UserRegisterDto> usersRegisterDto);

}
