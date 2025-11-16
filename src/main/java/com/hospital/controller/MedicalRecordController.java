package com.hospital.controller;

import com.hospital.entity.MedicalRecord;
import com.hospital.service.DoctorService;
import com.hospital.service.MedicalRecordService;
import com.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/medical-records")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public String listMedicalRecords(Model model) {
        List<MedicalRecord> records = medicalRecordService.getAllMedicalRecords();
        model.addAttribute("records", records);
        return "records-list";
    }

    @GetMapping("/new")
    public String showRecordForm(Model model) {
        model.addAttribute("record", new MedicalRecord());
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "record-form";
    }

    @PostMapping
    public String saveRecord(@Valid @ModelAttribute("record") MedicalRecord record,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "record-form";
        }
        try {
            medicalRecordService.createMedicalRecord(record);
            redirectAttributes.addFlashAttribute("successMessage", "Medical record created successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error creating record: " + e.getMessage());
        }
        return "redirect:/medical-records";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        MedicalRecord record = medicalRecordService.getMedicalRecordById(id);
        model.addAttribute("record", record);
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "record-form";
    }

    @PostMapping("/{id}")
    public String updateRecord(@PathVariable Long id,
                              @Valid @ModelAttribute("record") MedicalRecord record,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "record-form";
        }
        try {
            medicalRecordService.updateMedicalRecord(id, record);
            redirectAttributes.addFlashAttribute("successMessage", "Medical record updated successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating record: " + e.getMessage());
        }
        return "redirect:/medical-records";
    }

    @PostMapping("/{id}/delete")
    public String deleteRecord(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            medicalRecordService.deleteMedicalRecord(id);
            redirectAttributes.addFlashAttribute("successMessage", "Medical record deleted successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting record: " + e.getMessage());
        }
        return "redirect:/medical-records";
    }
}

