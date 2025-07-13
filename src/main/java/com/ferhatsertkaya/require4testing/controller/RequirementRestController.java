package com.ferhatsertkaya.require4testing.controller;

import com.ferhatsertkaya.require4testing.model.Requirement;
import com.ferhatsertkaya.require4testing.service.RequirementService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requirements")
public class RequirementRestController {

    private final RequirementService requirementService;

    public RequirementRestController(RequirementService requirementService) {
        this.requirementService = requirementService;
    }

    @GetMapping
    public List<Requirement> getAllRequirements() {
        return requirementService.getAllRequirements();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Requirement> getRequirementById(@PathVariable Long id) {
        return requirementService.getRequirementById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Requirement> createRequirement(@Valid @RequestBody Requirement requirement) {
        Requirement saved = requirementService.saveRequirement(requirement);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Requirement> updateRequirement(@PathVariable Long id, @Valid @RequestBody Requirement requirement) {
        if (requirementService.getRequirementById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        requirement.setId(id);
        Requirement saved = requirementService.saveRequirement(requirement);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequirement(@PathVariable Long id) {
        if (requirementService.getRequirementById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        requirementService.deleteRequirement(id);
        return ResponseEntity.noContent().build();
    }
}