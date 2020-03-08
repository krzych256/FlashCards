package com.flashCards.services;

import com.flashCards.domain.auth.User;
import com.flashCards.exceptions.ResourceNotFoundException;
import com.flashCards.mapper.UserMapper;
import com.flashCards.model.UserDTO;
import com.flashCards.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(UserMapper userMapper,
                           UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {

        return userRepository.findById(id)
                .map(userMapper::userToUserDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public UserDTO createNewUser(UserDTO userDTO) {

        User user = userMapper.userDTOToUser(userDTO);
        user.setEnabled(true);
        //TODO: add profile and userAuthorities

        User savedUser = userRepository.save(user);
        return userMapper.userToUserDTO(savedUser);
    }

    @Override
    public UserDTO updateOrCreateUser(Long id, UserDTO userDTO) {

        return userMapper.userToUserDTO(userRepository.findById(id)
                .map(user -> {
                    user.setUserName(userDTO.getUsername());
                    user.setEmail(userDTO.getEmail());
                    user.setPassword(userDTO.getPassword());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    userDTO.setId(id);
                    return userRepository.save(userMapper.userDTOToUser(userDTO));
                })
        );
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
