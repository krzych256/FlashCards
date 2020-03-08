package com.flashCards.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flashCards.model.UserDTO;
import com.flashCards.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {

    public static final String USERNAME = "Jim";
    public static final String EMAIL = "jim@gmail.com";

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void testGetAllUsers() throws Exception {

        //given
        UserDTO userDTO1 = new UserDTO();
        UserDTO userDTO2 = new UserDTO();

        List<UserDTO> userDTOS = Arrays.asList(userDTO1, userDTO2);

        when(userService.getAllUsers()).thenReturn(userDTOS);

        //when/then
        mockMvc.perform(get(UserController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testGetUserById() throws Exception {

        //given
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setUsername(USERNAME);
        userDTO.setEmail(EMAIL);

        when(userService.getUserById(anyLong())).thenReturn(userDTO);

        //when/then
        mockMvc.perform(get(UserController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", equalTo(USERNAME)))
                .andExpect(jsonPath("$.email", equalTo(EMAIL)));
    }

    @Test
    public void testCreateNewUser() throws Exception {

        //given
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setUsername(USERNAME);
        userDTO.setEmail(EMAIL);

        when(userService.createNewUser(any(UserDTO.class))).thenReturn(userDTO);

        //when/then
        mockMvc.perform(post(UserController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username", equalTo(USERNAME)))
                .andExpect(jsonPath("$.email", equalTo(EMAIL)));
    }

    @Test
    public void updateUser() throws Exception {

        //given
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setUsername(USERNAME);
        userDTO.setEmail(EMAIL);

        when(userService.updateOrCreateUser(1L, userDTO)).thenReturn(userDTO);

        //when/then
        mockMvc.perform(put(UserController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.username", equalTo(USERNAME)))
                .andExpect(jsonPath("$.email", equalTo(EMAIL)));
    }

    @Test
    public void testDeleteUser() throws Exception {

        mockMvc.perform(delete(UserController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService).deleteById(anyLong());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}