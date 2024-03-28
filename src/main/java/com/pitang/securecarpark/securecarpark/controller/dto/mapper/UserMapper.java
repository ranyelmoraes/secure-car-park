package com.pitang.securecarpark.securecarpark.controller.dto.mapper;

import com.pitang.securecarpark.securecarpark.controller.dto.user.UserRequest;
import com.pitang.securecarpark.securecarpark.controller.dto.user.UserResponse;
import com.pitang.securecarpark.securecarpark.entity.User;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static User dtoToUser(UserRequest createUserDto) {
        return new ModelMapper().map(createUserDto, User.class);
    }

    public static UserResponse userToDto(User user) {
        return new ModelMapper().map(user, UserResponse.class);
    }

    public static List<UserResponse> toList(List<User> users) {
        return users.stream().map(UserMapper::userToDto).collect(Collectors.toList());
    }
}
