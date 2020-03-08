package com.flashCards.mapper;

import com.flashCards.domain.auth.User;
import com.flashCards.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "userName", target = "username")
    UserDTO userToUserDTO(User user);

    @Mapping(source = "username", target = "userName")
    User userDTOToUser(UserDTO userDTO);
}
