package com.ferhatsertkaya.require4testing.controller;

import com.ferhatsertkaya.require4testing.model.Tester;
import com.ferhatsertkaya.require4testing.service.TesterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/testers")
public class TesterRestController {

    private final TesterService testerService;

    public TesterRestController(TesterService testerService) {
        this.testerService = testerService;
    }

    @GetMapping
    public List<Tester> getAllTesters() {
        return testerService.getAllTesters();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tester> getTesterById(@PathVariable Long id) {
        return testerService.getTesterById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Tester> createTester(@Valid @RequestBody Tester tester) {
        Tester saved = testerService.saveTester(tester);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tester> updateTester(@PathVariable Long id, @Valid @RequestBody Tester tester) {
        if (testerService.getTesterById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        tester.setId(id);
        Tester saved = testerService.saveTester(tester);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTester(@PathVariable Long id) {
        if (testerService.getTesterById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        testerService.deleteTester(id);
        return ResponseEntity.noContent().build();
    }
}