package com.ferhatsertkaya.require4testing.controller;

import com.ferhatsertkaya.require4testing.model.User;
import com.ferhatsertkaya.require4testing.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // REST-API: Alle User als JSON
    @GetMapping(produces = "application/json")
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // REST-API: User nach ID als JSON
    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseBody
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Thymeleaf: Liste aller User anzeigen
    @GetMapping
    public String showUserList(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users"; // src/main/resources/templates/users.html
    }

    // Thymeleaf: Formular für neuen User
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "user_form"; // src/main/resources/templates/user_form.html
    }

    // Thymeleaf: Formular zum Bearbeiten eines Users
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "user_form";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Benutzer nicht gefunden");
            return "redirect:/users";
        }
    }

    // Thymeleaf: Speichern (neu und update)
    @PostMapping("/save")
    public String saveUser(@Valid @ModelAttribute("user") User user,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "user_form";
        }
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("successMessage", "Benutzer erfolgreich gespeichert");
        return "redirect:/users";
    }

    // Thymeleaf: Benutzer löschen
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            userService.deleteUser(id);
            redirectAttributes.addFlashAttribute("successMessage", "Benutzer erfolgreich gelöscht");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Benutzer nicht gefunden");
        }
        return "redirect:/users";
    }
}