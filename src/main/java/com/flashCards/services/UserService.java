package com.flashCards.services;

import com.flashCards.model.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    UserDTO createNewUser(UserDTO userDTO);

    UserDTO updateOrCreateUser(Long id, UserDTO userDTO);

    void deleteById(Long id);
}
