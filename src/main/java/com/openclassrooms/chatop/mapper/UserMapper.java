package com.openclassrooms.chatop.mapper;

import com.openclassrooms.chatop.dto.UserDto;
import com.openclassrooms.chatop.dto.UserLoginDto;
import com.openclassrooms.chatop.dto.UserRegisterDto;
import com.openclassrooms.chatop.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
    List<UserDto> usersToUsersDto(List<User> users);
    List<User> usersDtoToUsers(List<UserDto> usersDto);

}
