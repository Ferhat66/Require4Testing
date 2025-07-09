package com.ferhatsertkaya.require4testing.controller;

import com.ferhatsertkaya.require4testing.model.Requirement;
import com.ferhatsertkaya.require4testing.service.RequirementService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/requirements")
public class RequirementController {

    private final RequirementService requirementService;

    public RequirementController(RequirementService requirementService) {
        this.requirementService = requirementService;
    }

    @GetMapping
    public List<Requirement> getAllRequirements() {
        return requirementService.getAllRequirements();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Requirement> getRequirementById(@PathVariable Long id) {
        Optional<Requirement> requirement = requirementService.getRequirementById(id);
        return requirement.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Requirement> createRequirement(@Valid @RequestBody Requirement requirement) {
        Requirement saved = requirementService.saveRequirement(requirement);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Requirement> updateRequirement(@PathVariable Long id,
                                                         @Valid @RequestBody Requirement updatedRequirement) {
        Optional<Requirement> existing = requirementService.getRequirementById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        updatedRequirement.setId(id);
        Requirement saved = requirementService.saveRequirement(updatedRequirement);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequirement(@PathVariable Long id) {
        Optional<Requirement> existing = requirementService.getRequirementById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        requirementService.deleteRequirement(id);
        return ResponseEntity.noContent().build();
    }
}