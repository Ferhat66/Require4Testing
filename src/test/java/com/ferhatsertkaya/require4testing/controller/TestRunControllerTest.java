package com.ferhatsertkaya.require4testing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ferhatsertkaya.require4testing.model.TestRun;
import com.ferhatsertkaya.require4testing.model.TestCase;
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

@WebMvcTest(TestRunController.class)
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
        exampleTestRun = new TestRun();
        exampleTestRun.setId(1L);
        exampleTestRun.setRunDate(LocalDateTime.now());
        exampleTestRun.setStatus("PASSED");

        TestCase testCase = new TestCase();
        testCase.setId(1L);
        exampleTestRun.setTestCase(testCase);

        Tester tester = new Tester();
        tester.setId(1L);
        exampleTestRun.setTester(tester);
    }

    @Test
    public void testGetAllTestRuns() throws Exception {
        when(testRunService.getAllTestRuns()).thenReturn(Arrays.asList(exampleTestRun));

        mockMvc.perform(get("/testruns"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].id").value(exampleTestRun.getId()))
               .andExpect(jsonPath("$[0].status").value(exampleTestRun.getStatus()));
    }

    @Test
    public void testGetTestRunById() throws Exception {
        when(testRunService.getTestRunById(1L)).thenReturn(Optional.of(exampleTestRun));

        mockMvc.perform(get("/testruns/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(exampleTestRun.getId()))
               .andExpect(jsonPath("$.status").value(exampleTestRun.getStatus()));
    }

    @Test
    public void testCreateTestRun() throws Exception {
        when(testRunService.saveTestRun(any(TestRun.class))).thenReturn(exampleTestRun);

        mockMvc.perform(post("/testruns")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(exampleTestRun)))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.status").value(exampleTestRun.getStatus()));
    }

    @Test
    public void testUpdateTestRun() throws Exception {
        when(testRunService.getTestRunById(1L)).thenReturn(Optional.of(exampleTestRun));
        when(testRunService.saveTestRun(any(TestRun.class))).thenReturn(exampleTestRun);

        mockMvc.perform(put("/testruns/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(exampleTestRun)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.status").value(exampleTestRun.getStatus()));
    }

    @Test
    public void testDeleteTestRun() throws Exception {
        when(testRunService.getTestRunById(1L)).thenReturn(Optional.of(exampleTestRun));

        mockMvc.perform(delete("/testruns/1"))
               .andExpect(status().isNoContent());
    }
}