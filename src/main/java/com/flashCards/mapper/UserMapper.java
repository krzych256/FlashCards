package com.flashCards.mapper;

import com.flashCards.domain.User;
import com.flashCards.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);

    @Mapping( target = "password", ignore = true)
    User userDTOToUser(UserDTO userDTO);
}
