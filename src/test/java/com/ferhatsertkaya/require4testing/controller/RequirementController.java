package com.ferhatsertkaya.require4testing.controller;

import com.ferhatsertkaya.require4testing.model.Requirement;
import com.ferhatsertkaya.require4testing.service.RequirementService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/requirements")
public class RequirementController {

    private final RequirementService requirementService;

    public RequirementController(RequirementService requirementService) {
        this.requirementService = requirementService;
    }

    @GetMapping
    public String getAllRequirements(Model model) {
        List<Requirement> requirements = requirementService.getAllRequirements();
        model.addAttribute("requirements", requirements);
        return "requirements"; // referenziert requirements.html
    }

    @GetMapping("/{id}")
    public String getRequirementById(@PathVariable Long id, Model model) {
        Optional<Requirement> requirement = requirementService.getRequirementById(id);
        if (requirement.isPresent()) {
            model.addAttribute("requirement", requirement.get());
            return "requirement_detail"; // Optional: Detailseite, falls gewünscht
        }
        return "redirect:/requirements";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("requirement", new Requirement());
        return "requirement_form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Requirement> requirement = requirementService.getRequirementById(id);
        if (requirement.isPresent()) {
            model.addAttribute("requirement", requirement.get());
            return "requirement_form";
        }
        return "redirect:/requirements";
    }

    @PostMapping("/save")
    public String saveRequirement(@Valid @ModelAttribute("requirement") Requirement requirement,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "requirement_form";
        }
        requirementService.saveRequirement(requirement);
        return "redirect:/requirements";
    }

    @GetMapping("/delete/{id}")
    public String deleteRequirement(@PathVariable Long id) {
        requirementService.deleteRequirement(id);
        return "redirect:/requirements";
    }
}