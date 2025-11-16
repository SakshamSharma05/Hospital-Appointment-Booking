package com.hospital.controller;

import com.hospital.entity.Role;
import com.hospital.entity.User;
import com.hospital.service.UserService;
import com.hospital.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users-list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleRepository.findAll());
        return "user-form";
    }

    @PostMapping
    public String createUser(@ModelAttribute User user,
                             @RequestParam(value = "roles", required = false) List<String> roleNames,
                             RedirectAttributes redirectAttributes) {
        try {
            Set<String> roles = roleNames != null ? new HashSet<>(roleNames) : new HashSet<>();
            userService.createUser(user, roles);
            redirectAttributes.addFlashAttribute("successMessage", "User created successfully");
            return "redirect:/users";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error creating user: " + e.getMessage());
            return "redirect:/users/new";
        }
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        // Don't show encoded password in the form
        user.setPassword(null);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleRepository.findAll());
        return "user-form";
    }

    @PostMapping("/{id}")
    public String updateUser(@PathVariable Long id,
                             @ModelAttribute User formUser,
                             @RequestParam(value = "roles", required = false) List<String> roleNames,
                             RedirectAttributes redirectAttributes) {
        try {
            User userDetails = new User();
            userDetails.setUsername(formUser.getUsername());
            userDetails.setFirstName(formUser.getFirstName());
            userDetails.setLastName(formUser.getLastName());
            userDetails.setEmail(formUser.getEmail());
            userDetails.setPhone(formUser.getPhone());
            userDetails.setEnabled(formUser.getEnabled());
            // Only update password if a new one was provided
            if (formUser.getPassword() != null && !formUser.getPassword().isEmpty()) {
                userDetails.setPassword(formUser.getPassword());
            }

            Set<String> roles = roleNames != null ? new HashSet<>(roleNames) : null;

            userService.updateUser(id, userDetails, roles);
            redirectAttributes.addFlashAttribute("successMessage", "User updated successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating user: " + e.getMessage());
        }
        return "redirect:/users";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUser(id);
            redirectAttributes.addFlashAttribute("successMessage", "User deleted successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting user: " + e.getMessage());
        }
        return "redirect:/users";
    }
}
