package com.hospital.controller;

import com.hospital.entity.Invoice;
import com.hospital.entity.InvoiceItem;
import com.hospital.service.InvoiceService;
import com.hospital.service.PatientService;
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
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private PatientService patientService;

    @GetMapping
    public String listInvoices(Model model) {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        model.addAttribute("invoices", invoices);
        return "invoices-list";
    }

    @GetMapping("/new")
    public String showInvoiceForm(Model model) {
        Invoice invoice = new Invoice();
        invoice.setItems(new ArrayList<>());
        invoice.getItems().add(new InvoiceItem());
        model.addAttribute("invoice", invoice);
        model.addAttribute("patients", patientService.getAllPatients());
        return "invoice-form";
    }

    @PostMapping
    public String saveInvoice(@Valid @ModelAttribute Invoice invoice,
                             BindingResult result,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "invoice-form";
        }
        try {
            invoiceService.createInvoice(invoice);
            redirectAttributes.addFlashAttribute("successMessage", "Invoice created successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error creating invoice: " + e.getMessage());
        }
        return "redirect:/invoices";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        model.addAttribute("invoice", invoice);
        model.addAttribute("patients", patientService.getAllPatients());
        return "invoice-form";
    }

    @PostMapping("/{id}")
    public String updateInvoice(@PathVariable Long id,
                               @Valid @ModelAttribute Invoice invoice,
                               BindingResult result,
                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "invoice-form";
        }
        try {
            invoiceService.updateInvoice(id, invoice);
            redirectAttributes.addFlashAttribute("successMessage", "Invoice updated successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating invoice: " + e.getMessage());
        }
        return "redirect:/invoices";
    }

    @PostMapping("/{id}/delete")
    public String deleteInvoice(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            invoiceService.deleteInvoice(id);
            redirectAttributes.addFlashAttribute("successMessage", "Invoice deleted successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting invoice: " + e.getMessage());
        }
        return "redirect:/invoices";
    }
}

