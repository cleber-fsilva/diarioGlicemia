package com.cleber.diarioGlicemia.mapper;

import com.cleber.diarioGlicemia.controller.request.UserRequest;
import com.cleber.diarioGlicemia.controller.response.UserResponse;
import com.cleber.diarioGlicemia.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Usermapper {

    public static User toUser(UserRequest userRequest) {
        return User.builder()
                .name(userRequest.name())
                .email(userRequest.email())
                .password(userRequest.password())
                .build();
    }

    public static UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
