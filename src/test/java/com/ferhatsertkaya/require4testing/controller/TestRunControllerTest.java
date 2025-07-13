package com.ferhatsertkaya.require4testing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ferhatsertkaya.require4testing.model.Requirement;
import com.ferhatsertkaya.require4testing.model.TestCase;
import com.ferhatsertkaya.require4testing.model.TestRun;
import com.ferhatsertkaya.require4testing.model.Tester;
import com.ferhatsertkaya.require4testing.service.TestRunService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TestRunRestController.class)
public class TestRunControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TestRunService testRunService;

    @Autowired
    private ObjectMapper objectMapper;

    private TestRun exampleTestRun;

    @BeforeEach
    public void setup() {
        objectMapper.registerModule(new JavaTimeModule());

        exampleTestRun = new TestRun();
        exampleTestRun.setId(1L);
        exampleTestRun.setRunDate(LocalDateTime.now());
        exampleTestRun.setStatus("PASSED");

        Requirement requirement = new Requirement();
        requirement.setId(1L);
        requirement.setTitle("Sample Requirement");
        requirement.setDescription("Beschreibung Req");

        TestCase testCase = new TestCase();
        testCase.setId(1L);
        testCase.setTitle("Sample TestCase");
        testCase.setDescription("Beschreibung");
        testCase.setRequirement(requirement);
        exampleTestRun.setTestCase(testCase);

        Tester tester = new Tester();
        tester.setId(1L);
        tester.setName("Max Mustermann");
        tester.setEmail("max@example.com");
        exampleTestRun.setTester(tester);
    }

    @Test
    public void testGetAllTestRuns() throws Exception {
        when(testRunService.getAllTestRuns()).thenReturn(Arrays.asList(exampleTestRun));

        mockMvc.perform(get("/api/testruns"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].id").value(exampleTestRun.getId()))
               .andExpect(jsonPath("$[0].status").value(exampleTestRun.getStatus()));
    }

    @Test
    public void testGetTestRunById() throws Exception {
        when(testRunService.getTestRunById(1L)).thenReturn(Optional.of(exampleTestRun));

        mockMvc.perform(get("/api/testruns/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(exampleTestRun.getId()))
               .andExpect(jsonPath("$.status").value(exampleTestRun.getStatus()));
    }

    @Test
    public void testCreateTestRun() throws Exception {
        when(testRunService.saveTestRun(any(TestRun.class))).thenReturn(exampleTestRun);

        mockMvc.perform(post("/api/testruns")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(exampleTestRun)))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.status").value(exampleTestRun.getStatus()));
    }

    @Test
    public void testUpdateTestRun() throws Exception {
        when(testRunService.getTestRunById(1L)).thenReturn(Optional.of(exampleTestRun));
        when(testRunService.saveTestRun(any(TestRun.class))).thenReturn(exampleTestRun);

        mockMvc.perform(put("/api/testruns/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(exampleTestRun)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.status").value(exampleTestRun.getStatus()));
    }

    @Test
    public void testDeleteTestRun() throws Exception {
        when(testRunService.getTestRunById(1L)).thenReturn(Optional.of(exampleTestRun));

        mockMvc.perform(delete("/api/testruns/1"))
               .andExpect(status().isNoContent());
    }
}