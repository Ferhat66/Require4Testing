package com.ferhatsertkaya.require4testing.service;

import com.ferhatsertkaya.require4testing.model.Requirement;
import com.ferhatsertkaya.require4testing.repository.RequirementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequirementServiceImpl implements RequirementService {

    private final RequirementRepository requirementRepository;

    public RequirementServiceImpl(RequirementRepository requirementRepository) {
        this.requirementRepository = requirementRepository;
    }

    @Override
    public Requirement saveRequirement(Requirement requirement) {
        return requirementRepository.save(requirement);
    }

    @Override
    public List<Requirement> getAllRequirements() {
        return requirementRepository.findAll();
    }

    @Override
    public Optional<Requirement> getRequirementById(Long id) {
        return requirementRepository.findById(id);
    }

    @Override
    public void deleteRequirement(Long id) {
        requirementRepository.deleteById(id);
    }
}