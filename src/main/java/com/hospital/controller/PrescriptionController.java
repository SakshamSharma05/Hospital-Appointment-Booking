package com.hospital.controller;

import com.hospital.entity.Prescription;
import com.hospital.entity.PrescriptionItem;
import com.hospital.service.DoctorService;
import com.hospital.service.PatientService;
import com.hospital.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public String listPrescriptions(Model model) {
        List<Prescription> prescriptions = prescriptionService.getAllPrescriptions();
        model.addAttribute("prescriptions", prescriptions);
        return "prescriptions-list";
    }

    @GetMapping("/new")
    public String showPrescriptionForm(Model model) {
        Prescription prescription = new Prescription();
        prescription.setItems(new ArrayList<>());
        prescription.getItems().add(new PrescriptionItem());
        model.addAttribute("prescription", prescription);
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "prescription-form";
    }

    @PostMapping
    public String savePrescription(@Valid @ModelAttribute Prescription prescription,
                                  BindingResult result,
                                  RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "prescription-form";
        }
        try {
            prescriptionService.createPrescription(prescription);
            redirectAttributes.addFlashAttribute("successMessage", "Prescription created successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error creating prescription: " + e.getMessage());
        }
        return "redirect:/prescriptions";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Prescription prescription = prescriptionService.getPrescriptionById(id);
        model.addAttribute("prescription", prescription);
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "prescription-form";
    }

    @PostMapping("/{id}")
    public String updatePrescription(@PathVariable Long id,
                                    @Valid @ModelAttribute Prescription prescription,
                                    BindingResult result,
                                    RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "prescription-form";
        }
        try {
            prescriptionService.updatePrescription(id, prescription);
            redirectAttributes.addFlashAttribute("successMessage", "Prescription updated successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating prescription: " + e.getMessage());
        }
        return "redirect:/prescriptions";
    }

    @PostMapping("/{id}/delete")
    public String deletePrescription(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            prescriptionService.deletePrescription(id);
            redirectAttributes.addFlashAttribute("successMessage", "Prescription deleted successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting prescription: " + e.getMessage());
        }
        return "redirect:/prescriptions";
    }
}

