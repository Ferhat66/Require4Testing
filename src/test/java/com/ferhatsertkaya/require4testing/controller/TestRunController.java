package com.ferhatsertkaya.require4testing.controller;

import com.ferhatsertkaya.require4testing.model.TestRun;
import com.ferhatsertkaya.require4testing.service.TestRunService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/testruns")
public class TestRunController {

    private final TestRunService testRunService;

    public TestRunController(TestRunService testRunService) {
        this.testRunService = testRunService;
    }

    @GetMapping
    public List<TestRun> getAllTestRuns() {
        return testRunService.getAllTestRuns();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestRun> getTestRunById(@PathVariable Long id) {
        Optional<TestRun> testRun = testRunService.getTestRunById(id);
        return testRun.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TestRun> createTestRun(@Valid @RequestBody TestRun testRun) {
        TestRun saved = testRunService.saveTestRun(testRun);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestRun> updateTestRun(@PathVariable Long id,
                                                 @Valid @RequestBody TestRun updatedTestRun) {
        Optional<TestRun> existing = testRunService.getTestRunById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        updatedTestRun.setId(id);
        TestRun saved = testRunService.saveTestRun(updatedTestRun);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTestRun(@PathVariable Long id) {
        Optional<TestRun> existing = testRunService.getTestRunById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        testRunService.deleteTestRun(id);
        return ResponseEntity.noContent().build();
    }
}