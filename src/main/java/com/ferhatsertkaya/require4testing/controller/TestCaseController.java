package com.ferhatsertkaya.require4testing.controller;

import com.ferhatsertkaya.require4testing.model.TestCase;
import com.ferhatsertkaya.require4testing.service.TestCaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/testcases")
public class TestCaseController {

    private final TestCaseService testCaseService;

    public TestCaseController(TestCaseService testCaseService) {
        this.testCaseService = testCaseService;
    }

    @GetMapping
    public List<TestCase> getAllTestCases() {
        return testCaseService.getAllTestCases();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestCase> getTestCaseById(@PathVariable Long id) {
        Optional<TestCase> testCase = testCaseService.getTestCaseById(id);
        return testCase.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TestCase> createTestCase(@RequestBody TestCase testCase) {
        TestCase saved = testCaseService.saveTestCase(testCase);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestCase> updateTestCase(@PathVariable Long id,
                                                   @RequestBody TestCase updatedTestCase) {
        Optional<TestCase> existing = testCaseService.getTestCaseById(id);

        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        updatedTestCase.setId(id);
        TestCase saved = testCaseService.saveTestCase(updatedTestCase);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTestCase(@PathVariable Long id) {
        Optional<TestCase> existing = testCaseService.getTestCaseById(id);

        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        testCaseService.deleteTestCase(id);
        return ResponseEntity.noContent().build();
    }
}