package com.ferhatsertkaya.require4testing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ferhatsertkaya.require4testing.model.TestRun;
import com.ferhatsertkaya.require4testing.model.Tester;
import com.ferhatsertkaya.require4testing.service.TesterService;

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

@WebMvcTest(TesterRestController.class)
public class TesterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TesterService testerService;

    @Autowired
    private ObjectMapper objectMapper;

    private Tester exampleTester;

    @BeforeEach
    public void setup() {
        objectMapper.registerModule(new JavaTimeModule());

        exampleTester = new Tester();
        exampleTester.setId(1L);
        exampleTester.setName("Max Mustermann");
        exampleTester.setEmail("max@example.com");
        exampleTester.setTestRuns(Arrays.asList(new TestRun()));
    }

    @Test
    public void testGetAllTesters() throws Exception {
        when(testerService.getAllTesters()).thenReturn(Arrays.asList(exampleTester));

        mockMvc.perform(get("/api/testers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(exampleTester.getId()))
                .andExpect(jsonPath("$[0].name").value(exampleTester.getName()));
    }

    @Test
    public void testGetTesterById() throws Exception {
        when(testerService.getTesterById(1L)).thenReturn(Optional.of(exampleTester));

        mockMvc.perform(get("/api/testers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(exampleTester.getId()))
                .andExpect(jsonPath("$.name").value(exampleTester.getName()));
    }

    @Test
    public void testCreateTester() throws Exception {
        when(testerService.saveTester(any(Tester.class))).thenReturn(exampleTester);

        mockMvc.perform(post("/api/testers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(exampleTester)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(exampleTester.getName()));
    }

    @Test
    public void testUpdateTester() throws Exception {
        when(testerService.getTesterById(1L)).thenReturn(Optional.of(exampleTester));
        when(testerService.saveTester(any(Tester.class))).thenReturn(exampleTester);

        mockMvc.perform(put("/api/testers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(exampleTester)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(exampleTester.getName()));
    }

    @Test
    public void testDeleteTester() throws Exception {
        when(testerService.getTesterById(1L)).thenReturn(Optional.of(exampleTester));

        mockMvc.perform(delete("/api/testers/1"))
                .andExpect(status().isNoContent());
    }
}