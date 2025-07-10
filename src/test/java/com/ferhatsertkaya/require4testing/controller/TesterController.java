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
@RequestMapping({"/testers", "/testers/"})
public class TesterController {

    private final TesterService testerService;

    public TesterController(TesterService testerService) {
        this.testerService = testerService;
    }

    // REST-API: GET alle Tester als JSON
    @GetMapping(produces = "application/json")
    @ResponseBody
    public List<Tester> getAllTesters() {
        return testerService.getAllTesters();
    }

    // REST-API: GET Tester nach ID als JSON
    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseBody
    public Optional<Tester> getTesterById(@PathVariable Long id) {
        return testerService.getTesterById(id);
    }

    // Thymeleaf: Liste aller Tester anzeigen
    @GetMapping
    public String showTesterList(Model model) {
        model.addAttribute("testers", testerService.getAllTesters());
        return "testers";  // src/main/resources/templates/testers.html
    }

    // Thymeleaf: Formular für neuen Tester
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("tester", new Tester());
        return "tester_form"; // src/main/resources/templates/tester_form.html
    }

    // Thymeleaf: Formular für Tester bearbeiten
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

    // Thymeleaf: Tester speichern (neu und update)
    @PostMapping("/save")
    public String saveTester(@Valid @ModelAttribute("tester") Tester tester, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "tester_form";
        }
        testerService.saveTester(tester);
        redirectAttributes.addFlashAttribute("successMessage", "Tester erfolgreich gespeichert");
        return "redirect:/testers";
    }

    // Thymeleaf: Tester löschen
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