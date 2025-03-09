package com.example.reminder.web.mappers;

import com.example.reminder.models.user.User;
import com.example.reminder.web.dto.user.UserRequestDto;
import com.example.reminder.web.dto.user.UserResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto toDto(User user);

    User toEntity(UserRequestDto dto);

}
