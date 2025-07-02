package com.ferhatsertkaya.require4testing.service;

import com.ferhatsertkaya.require4testing.model.Requirement;

import java.util.List;
import java.util.Optional;

public interface RequirementService {
    Requirement saveRequirement(Requirement requirement);
    List<Requirement> getAllRequirements();
    Optional<Requirement> getRequirementById(Long id);
    void deleteRequirement(Long id);
}