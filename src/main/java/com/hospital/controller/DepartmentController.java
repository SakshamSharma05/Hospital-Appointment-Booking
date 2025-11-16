package com.hospital.controller;

import com.hospital.entity.Department;
import com.hospital.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String listDepartments(Model model) {
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "departments-list";
    }

    @GetMapping("/new")
    public String showDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        return "department-form";
    }

    @PostMapping
    public String saveDepartment(@Valid @ModelAttribute Department department,
                                BindingResult result,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "department-form";
        }
        try {
            departmentService.createDepartment(department);
            redirectAttributes.addFlashAttribute("successMessage", "Department created successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error creating department: " + e.getMessage());
        }
        return "redirect:/departments";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Department department = departmentService.getDepartmentById(id);
        model.addAttribute("department", department);
        return "department-form";
    }

    @PostMapping("/{id}")
    public String updateDepartment(@PathVariable Long id,
                                   @Valid @ModelAttribute Department department,
                                   BindingResult result,
                                   RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "department-form";
        }
        try {
            departmentService.updateDepartment(id, department);
            redirectAttributes.addFlashAttribute("successMessage", "Department updated successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating department: " + e.getMessage());
        }
        return "redirect:/departments";
    }

    @PostMapping("/{id}/delete")
    public String deleteDepartment(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            departmentService.deleteDepartment(id);
            redirectAttributes.addFlashAttribute("successMessage", "Department deleted successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting department: " + e.getMessage());
        }
        return "redirect:/departments";
    }
}

