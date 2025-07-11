package com.ferhatsertkaya.require4testing.controller;

import com.ferhatsertkaya.require4testing.model.Tester;
import com.ferhatsertkaya.require4testing.service.TesterService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping({"/testers", "/testers/"})  // Slash-Varianten abfangen
public class TesterController {

    private final TesterService testerService;

    public TesterController(TesterService testerService) {
        this.testerService = testerService;
    }

    @GetMapping
    public String showTesterList(Model model) {
        List<Tester> testers = testerService.getAllTesters();
        model.addAttribute("testers", testers);
        return "testers";  // Template src/main/resources/templates/testers.html
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("tester", new Tester());
        return "tester_form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Tester> tester = testerService.getTesterById(id);
        if (tester.isPresent()) {
            model.addAttribute("tester", tester.get());
            return "tester_form";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Tester nicht gefunden");
            return "redirect:/testers";
        }
    }

    @PostMapping("/save")
    public String saveTester(@Valid @ModelAttribute("tester") Tester tester,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "tester_form";
        }
        testerService.saveTester(tester);
        redirectAttributes.addFlashAttribute("successMessage", "Tester erfolgreich gespeichert");
        return "redirect:/testers";
    }

    @GetMapping("/delete/{id}")
    public String deleteTester(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<Tester> tester = testerService.getTesterById(id);
        if (tester.isPresent()) {
            testerService.deleteTester(id);
            redirectAttributes.addFlashAttribute("successMessage", "Tester erfolgreich gelöscht");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Tester nicht gefunden");
        }
        return "redirect:/testers";
    }
}