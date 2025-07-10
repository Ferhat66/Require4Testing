package com.ferhatsertkaya.require4testing.controller;

import com.ferhatsertkaya.require4testing.model.TestRun;
import com.ferhatsertkaya.require4testing.service.TestRunService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/testruns")
public class TestRunController {

    private final TestRunService testRunService;

    public TestRunController(TestRunService testRunService) {
        this.testRunService = testRunService;
    }

    // REST-API: Alle TestRuns als JSON
    @GetMapping(produces = "application/json")
    @ResponseBody
    public List<TestRun> getAllTestRuns() {
        return testRunService.getAllTestRuns();
    }

    // REST-API: TestRun nach ID als JSON
    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseBody
    public Optional<TestRun> getTestRunById(@PathVariable Long id) {
        return testRunService.getTestRunById(id);
    }

    // Thymeleaf: Liste aller TestRuns
    @GetMapping
    public String showTestRunList(Model model) {
        model.addAttribute("testruns", testRunService.getAllTestRuns());
        return "testruns"; // src/main/resources/templates/testruns.html
    }

    // Thymeleaf: Formular für neuen TestRun
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("testrun", new TestRun());
        return "testrun_form"; // src/main/resources/templates/testrun_form.html
    }

    // Thymeleaf: Formular zum Bearbeiten
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<TestRun> testRun = testRunService.getTestRunById(id);
        if (testRun.isPresent()) {
            model.addAttribute("testrun", testRun.get());
            return "testrun_form";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Testlauf nicht gefunden");
            return "redirect:/testruns";
        }
    }

    // Thymeleaf: Speichern (neu und update)
    @PostMapping("/save")
    public String saveTestRun(@Valid @ModelAttribute("testrun") TestRun testRun,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "testrun_form";
        }
        testRunService.saveTestRun(testRun);
        redirectAttributes.addFlashAttribute("successMessage", "Testlauf erfolgreich gespeichert");
        return "redirect:/testruns";
    }

    // Thymeleaf: Löschen
    @GetMapping("/delete/{id}")
    public String deleteTestRun(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<TestRun> testRun = testRunService.getTestRunById(id);
        if (testRun.isPresent()) {
            testRunService.deleteTestRun(id);
            redirectAttributes.addFlashAttribute("successMessage", "Testlauf erfolgreich gelöscht");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Testlauf nicht gefunden");
        }
        return "redirect:/testruns";
    }
}