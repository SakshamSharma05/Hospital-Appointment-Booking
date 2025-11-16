package com.hospital.service;

import com.hospital.entity.Invoice;
import com.hospital.entity.InvoiceItem;
import com.hospital.exception.ResourceNotFoundException;
import com.hospital.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id: " + id));
    }

    public List<Invoice> getInvoicesByPatient(Long patientId) {
        return invoiceRepository.findByPatientId(patientId);
    }

    public Invoice createInvoice(Invoice invoice) {
        // Generate invoice number
        invoice.setInvoiceNumber("INV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        invoice.setIssuedDate(LocalDateTime.now());
        invoice.setStatus("PENDING");
        
        // Calculate total from items
        if (invoice.getItems() != null && !invoice.getItems().isEmpty()) {
            BigDecimal total = invoice.getItems().stream()
                    .map(InvoiceItem::getTotalPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            invoice.setTotalAmount(total);
            
            for (InvoiceItem item : invoice.getItems()) {
                item.setInvoice(invoice);
            }
        }
        
        return invoiceRepository.save(invoice);
    }

    public Invoice updateInvoice(Long id, Invoice invoiceDetails) {
        Invoice invoice = getInvoiceById(id);
        
        // Update items and recalculate total
        if (invoiceDetails.getItems() != null) {
            invoice.getItems().clear();
            BigDecimal total = BigDecimal.ZERO;
            for (InvoiceItem item : invoiceDetails.getItems()) {
                item.setInvoice(invoice);
                invoice.getItems().add(item);
                total = total.add(item.getTotalPrice());
            }
            invoice.setTotalAmount(total);
        }
        
        invoice.setStatus(invoiceDetails.getStatus());
        invoice.setNotes(invoiceDetails.getNotes());
        invoice.setDueDate(invoiceDetails.getDueDate());
        
        if ("PAID".equals(invoiceDetails.getStatus()) && invoice.getPaidDate() == null) {
            invoice.setPaidDate(LocalDateTime.now());
            invoice.setPaidAmount(invoice.getTotalAmount());
        }
        
        return invoiceRepository.save(invoice);
    }

    public void deleteInvoice(Long id) {
        Invoice invoice = getInvoiceById(id);
        invoiceRepository.delete(invoice);
    }
}

