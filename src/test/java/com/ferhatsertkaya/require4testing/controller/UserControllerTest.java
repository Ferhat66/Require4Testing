package com.ferhatsertkaya.require4testing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ferhatsertkaya.require4testing.model.User;
import com.ferhatsertkaya.require4testing.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private User exampleUser;

    @BeforeEach
    public void setup() {
        exampleUser = new User();
        exampleUser.setId(1L);
        exampleUser.setUsername("testuser");
        exampleUser.setPassword("password");
        exampleUser.setRole("USER");
    }

    @Test
    public void testGetAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(Arrays.asList(exampleUser));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(exampleUser.getId()))
                .andExpect(jsonPath("$[0].username").value(exampleUser.getUsername()));
    }

    @Test
    public void testGetUserById() throws Exception {
        when(userService.getUserById(1L)).thenReturn(Optional.of(exampleUser));

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(exampleUser.getId()))
                .andExpect(jsonPath("$.username").value(exampleUser.getUsername()));
    }

    @Test
    public void testCreateUser() throws Exception {
        when(userService.saveUser(any(User.class))).thenReturn(exampleUser);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(exampleUser)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value(exampleUser.getUsername()));
    }

    @Test
    public void testUpdateUser() throws Exception {
        when(userService.getUserById(1L)).thenReturn(Optional.of(exampleUser));
        when(userService.saveUser(any(User.class))).thenReturn(exampleUser);

        mockMvc.perform(put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(exampleUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(exampleUser.getUsername()));
    }

    @Test
    public void testDeleteUser() throws Exception {
        when(userService.getUserById(1L)).thenReturn(Optional.of(exampleUser));

        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isNoContent());
    }
}