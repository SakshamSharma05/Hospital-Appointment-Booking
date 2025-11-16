package com.hospital.controller;

import com.hospital.entity.Patient;
import com.hospital.service.PatientService;
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
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String listPatients(Model model) {
        List<Patient> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);
        return "patients-list";
    }

    @GetMapping("/new")
    public String showPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        model.addAttribute("users", userService.getAllUsers());
        return "patient-form";
    }

    @PostMapping
    public String savePatient(@Valid @ModelAttribute Patient patient, 
                             BindingResult result,
                             @RequestParam Long userId,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "patient-form";
        }
        try {
            patientService.createPatient(patient, userId);
            redirectAttributes.addFlashAttribute("successMessage", "Patient created successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error creating patient: " + e.getMessage());
        }
        return "redirect:/patients";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Patient patient = patientService.getPatientById(id);
        model.addAttribute("patient", patient);
        return "patient-form";
    }

    @PostMapping("/{id}")
    public String updatePatient(@PathVariable Long id,
                               @Valid @ModelAttribute Patient patient,
                               BindingResult result,
                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "patient-form";
        }
        try {
            patientService.updatePatient(id, patient);
            redirectAttributes.addFlashAttribute("successMessage", "Patient updated successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating patient: " + e.getMessage());
        }
        return "redirect:/patients";
    }

    @PostMapping("/{id}/delete")
    public String deletePatient(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            patientService.deletePatient(id);
            redirectAttributes.addFlashAttribute("successMessage", "Patient deleted successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting patient: " + e.getMessage());
        }
        return "redirect:/patients";
    }
}

