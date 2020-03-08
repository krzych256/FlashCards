package com.flashCards.services;

import com.flashCards.domain.auth.User;
import com.flashCards.mapper.UserMapper;
import com.flashCards.model.UserDTO;
import com.flashCards.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    private final Long ID = 1L;
    private final String USERNAME = "John";
    private final String EMAIL = "john@gmail.com";

    @Mock
    UserRepository userRepository;

    UserMapper userMapper = UserMapper.INSTANCE;

    UserService userService;

    User user;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        userService = new UserServiceImpl(userMapper, userRepository);

        user = new User();
        user.setId(1L);
        user.setUserName(USERNAME);
        user.setEmail(EMAIL);
    }

    @Test
    public void getAllUsers() {

        //given
        User user1 = new User();
        user1.setId(2L);

        when(userRepository.findAll()).thenReturn(Arrays.asList(user, user1));

        //when
        List<UserDTO> userDTOS = userService.getAllUsers();

        //then
        assertEquals(2, userDTOS.size());
    }

    @Test
    public void testGetUserById() {

        //given
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        //when
        UserDTO userDTO = userService.getUserById(ID);

        //then
        assertEquals(USERNAME, userDTO.getUsername());
        assertEquals(EMAIL, userDTO.getEmail());
    }

    @Test
    public void testCreateNewUser() {

        //given
        UserDTO userDTO = new UserDTO();
        userDTO.setId(ID);
        userDTO.setUsername(USERNAME);
        userDTO.setEmail(EMAIL);

        when(userRepository.save(any(User.class))).thenReturn(user);

        //when
        UserDTO savedDto = userService.createNewUser(userDTO);

        //then
        assertEquals(USERNAME, savedDto.getUsername());
        assertEquals(EMAIL, savedDto.getEmail());
    }

    @Test
    public void testUpdateOrCreateUser() {
        //TODO
        //given

        //when

        //then

    }

    @Test
    public void deleteCustomerById() throws Exception {

        //given
        Long id = 1L;

        //when
        userService.deleteById(id);

        //then
        verify(userRepository, times(1)).deleteById(anyLong());
    }
}