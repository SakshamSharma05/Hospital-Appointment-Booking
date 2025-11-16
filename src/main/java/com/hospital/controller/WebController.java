package com.hospital.controller;

import com.hospital.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/")
    public String home() {
        return "redirect:/dashboard";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", auth.getName());
        // Add counts for dashboard tiles
        model.addAttribute("patientCount", patientService.getAllPatients().size());
        model.addAttribute("doctorCount", doctorService.getAllDoctors().size());
        model.addAttribute("appointmentCount", appointmentService.getAllAppointments().size());
        model.addAttribute("departmentCount", departmentService.getAllDepartments().size());
        return "dashboard";
    }
}

