package com.ferhatsertkaya.require4testing.controller;

import com.ferhatsertkaya.require4testing.model.Requirement;
import com.ferhatsertkaya.require4testing.service.RequirementService;
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

    // GET /requirements 
    @GetMapping
    public List<Requirement> getAllRequirements() {
        return requirementService.getAllRequirements();
    }

    // GET /requirements/{id} 
    @GetMapping("/{id}")
    public ResponseEntity<Requirement> getRequirementById(@PathVariable Long id) {
        Optional<Requirement> requirement = requirementService.getRequirementById(id);
        return requirement.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST /requirements 
    @PostMapping
    public Requirement createRequirement(@RequestBody Requirement requirement) {
        return requirementService.saveRequirement(requirement);
    }

    // PUT /requirements/{id} 
    @PutMapping("/{id}")
    public ResponseEntity<Requirement> updateRequirement(@PathVariable Long id,
                                                         @RequestBody Requirement updatedRequirement) {
        Optional<Requirement> existing = requirementService.getRequirementById(id);

        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        updatedRequirement.setId(id);
        Requirement saved = requirementService.saveRequirement(updatedRequirement);
        return ResponseEntity.ok(saved);
    }

    // DELETE /requirements/{id} 
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