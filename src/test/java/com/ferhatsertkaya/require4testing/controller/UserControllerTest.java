package com.ferhatsertkaya.require4testing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

@WebMvcTest(UserRestController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private User exampleUser;

    @BeforeEach
    void setUp() {
        objectMapper.registerModule(new JavaTimeModule());

        exampleUser = new User();
        exampleUser.setId(1L);
        exampleUser.setUsername("testuser");
        exampleUser.setPassword("password");
        exampleUser.setRole("USER");
    }

    @Test
    void testGetAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(Arrays.asList(exampleUser));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(exampleUser.getId()))
                .andExpect(jsonPath("$[0].username").value(exampleUser.getUsername()))
                .andExpect(jsonPath("$[0].role").value(exampleUser.getRole()));
    }

    @Test
    void testGetUserById() throws Exception {
        when(userService.getUserById(1L)).thenReturn(Optional.of(exampleUser));

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(exampleUser.getId()))
                .andExpect(jsonPath("$.username").value(exampleUser.getUsername()))
                .andExpect(jsonPath("$.role").value(exampleUser.getRole()));
    }

    @Test
    void testCreateUser() throws Exception {
        when(userService.saveUser(any(User.class))).thenReturn(exampleUser);

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(exampleUser)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(exampleUser.getId()))
                .andExpect(jsonPath("$.username").value(exampleUser.getUsername()))
                .andExpect(jsonPath("$.role").value(exampleUser.getRole()));
    }

    @Test
    void testUpdateUser() throws Exception {
        when(userService.getUserById(1L)).thenReturn(Optional.of(exampleUser));
        when(userService.saveUser(any(User.class))).thenReturn(exampleUser);

        mockMvc.perform(put("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(exampleUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(exampleUser.getId()))
                .andExpect(jsonPath("$.username").value(exampleUser.getUsername()))
                .andExpect(jsonPath("$.role").value(exampleUser.getRole()));
    }

    @Test
    void testDeleteUser() throws Exception {
        when(userService.getUserById(1L)).thenReturn(Optional.of(exampleUser));

        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isNoContent());
    }
}