package com.ferhatsertkaya.require4testing.controller;

import com.ferhatsertkaya.require4testing.model.Tester;
import com.ferhatsertkaya.require4testing.service.TesterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/testers")
public class TesterController {

    private final TesterService testerService;

    public TesterController(TesterService testerService) {
        this.testerService = testerService;
    }

    // GET /testers
    @GetMapping
    public List<Tester> getAllTesters() {
        return testerService.getAllTesters();
    }

    // GET /testers/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Tester> getTesterById(@PathVariable Long id) {
        Optional<Tester> tester = testerService.getTesterById(id);
        return tester.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST /testers
    @PostMapping
    public ResponseEntity<Tester> createTester(@RequestBody Tester tester) {
        Tester saved = testerService.saveTester(tester);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // PUT /testers/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Tester> updateTester(@PathVariable Long id, @RequestBody Tester updatedTester) {
        Optional<Tester> existing = testerService.getTesterById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        updatedTester.setId(id);
        Tester saved = testerService.saveTester(updatedTester);
        return ResponseEntity.ok(saved);
    }

    // DELETE /testers/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTester(@PathVariable Long id) {
        Optional<Tester> existing = testerService.getTesterById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        testerService.deleteTester(id);
        return ResponseEntity.noContent().build();
    }
}