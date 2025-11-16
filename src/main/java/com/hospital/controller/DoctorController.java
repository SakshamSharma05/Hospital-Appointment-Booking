package com.hospital.controller;

import com.hospital.entity.Doctor;
import com.hospital.service.DepartmentService;
import com.hospital.service.DoctorService;
import com.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String listDoctors(Model model) {
        List<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "doctors-list";
    }

    @GetMapping("/new")
    public String showDoctorForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "doctor-form";
    }

    @PostMapping
    public String saveDoctor(@Valid @ModelAttribute Doctor doctor,
                            BindingResult result,
                            @RequestParam Long userId,
                            @RequestParam(required = false) Long departmentId,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "doctor-form";
        }
        try {
            doctorService.createDoctor(doctor, userId, departmentId);
            redirectAttributes.addFlashAttribute("successMessage", "Doctor created successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error creating doctor: " + e.getMessage());
        }
        return "redirect:/doctors";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Doctor doctor = doctorService.getDoctorById(id);
        model.addAttribute("doctor", doctor);
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "doctor-form";
    }

    @PostMapping("/{id}")
    public String updateDoctor(@PathVariable Long id,
                              @Valid @ModelAttribute Doctor doctor,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "doctor-form";
        }
        try {
            doctorService.updateDoctor(id, doctor);
            redirectAttributes.addFlashAttribute("successMessage", "Doctor updated successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating doctor: " + e.getMessage());
        }
        return "redirect:/doctors";
    }

    @PostMapping("/{id}/delete")
    public String deleteDoctor(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            doctorService.deleteDoctor(id);
            redirectAttributes.addFlashAttribute("successMessage", "Doctor deleted successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting doctor: " + e.getMessage());
        }
        return "redirect:/doctors";
    }
}

