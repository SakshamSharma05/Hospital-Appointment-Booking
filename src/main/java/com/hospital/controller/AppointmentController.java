package com.hospital.controller;

import com.hospital.entity.Appointment;
import com.hospital.service.AppointmentService;
import com.hospital.service.DoctorService;
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
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public String listAppointments(Model model) {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        model.addAttribute("appointments", appointments);
        return "appointments-list";
    }

    @GetMapping("/new")
    public String showAppointmentForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "appointment-form";
    }

    @PostMapping
    public String saveAppointment(@Valid @ModelAttribute Appointment appointment,
                                 BindingResult result,
                                 RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "appointment-form";
        }
        try {
            appointmentService.createAppointment(appointment);
            redirectAttributes.addFlashAttribute("successMessage", "Appointment scheduled successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error scheduling appointment: " + e.getMessage());
            return "redirect:/appointments/new";
        }
        return "redirect:/appointments";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        model.addAttribute("appointment", appointment);
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "appointment-form";
    }

    @PostMapping("/{id}")
    public String updateAppointment(@PathVariable Long id,
                                   @Valid @ModelAttribute Appointment appointment,
                                   BindingResult result,
                                   RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "appointment-form";
        }
        try {
            appointmentService.updateAppointment(id, appointment);
            redirectAttributes.addFlashAttribute("successMessage", "Appointment updated successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating appointment: " + e.getMessage());
        }
        return "redirect:/appointments";
    }

    @PostMapping("/{id}/delete")
    public String deleteAppointment(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            appointmentService.deleteAppointment(id);
            redirectAttributes.addFlashAttribute("successMessage", "Appointment deleted successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting appointment: " + e.getMessage());
        }
        return "redirect:/appointments";
    }
}

