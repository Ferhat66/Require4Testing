package com.ferhatsertkaya.require4testing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ferhatsertkaya.require4testing.model.Requirement;
import com.ferhatsertkaya.require4testing.service.RequirementService;

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

@WebMvcTest(RequirementController.class)
public class RequirementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RequirementService requirementService;

    @Autowired
    private ObjectMapper objectMapper;

    private Requirement exampleRequirement;

    @BeforeEach
    public void setup() {
        exampleRequirement = new Requirement();
        exampleRequirement.setId(1L);
        exampleRequirement.setTitle("Test Requirement");
        exampleRequirement.setDescription("This is a test requirement.");
    }

    @Test
    public void testGetAllRequirements() throws Exception {
        when(requirementService.getAllRequirements()).thenReturn(Arrays.asList(exampleRequirement));

        mockMvc.perform(get("/requirements"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(exampleRequirement.getId()))
                .andExpect(jsonPath("$[0].title").value(exampleRequirement.getTitle()));
    }

    @Test
    public void testGetRequirementById() throws Exception {
        when(requirementService.getRequirementById(1L)).thenReturn(Optional.of(exampleRequirement));

        mockMvc.perform(get("/requirements/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(exampleRequirement.getId()))
                .andExpect(jsonPath("$.title").value(exampleRequirement.getTitle()));
    }

    @Test
    public void testCreateRequirement() throws Exception {
        when(requirementService.saveRequirement(any(Requirement.class))).thenReturn(exampleRequirement);

        mockMvc.perform(post("/requirements")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(exampleRequirement)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(exampleRequirement.getTitle()));
    }

    @Test
    public void testUpdateRequirement() throws Exception {
        when(requirementService.getRequirementById(1L)).thenReturn(Optional.of(exampleRequirement));
        when(requirementService.saveRequirement(any(Requirement.class))).thenReturn(exampleRequirement);

        mockMvc.perform(put("/requirements/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(exampleRequirement)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(exampleRequirement.getTitle()));
    }

    @Test
    public void testDeleteRequirement() throws Exception {
        when(requirementService.getRequirementById(1L)).thenReturn(Optional.of(exampleRequirement));

        mockMvc.perform(delete("/requirements/1"))
                .andExpect(status().isNoContent());
    }
}