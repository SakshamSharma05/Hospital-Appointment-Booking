package com.hospital.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "invoices")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "invoice_number", nullable = false, unique = true, length = 50)
    private String invoiceNumber;
    
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @NotNull(message = "Patient is required")
    private Patient patient;
    
    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
    
    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "Total amount is required")
    private BigDecimal totalAmount;
    
    @Column(name = "paid_amount", precision = 10, scale = 2)
    private BigDecimal paidAmount = BigDecimal.ZERO;
    
    @Column(nullable = false, length = 20)
    private String status = "PENDING"; // PENDING, PAID, PARTIAL, CANCELLED
    
    @Column(name = "due_date")
    private LocalDate dueDate;
    
    @Column(name = "issued_date")
    private LocalDateTime issuedDate;
    
    @Column(name = "paid_date")
    private LocalDateTime paidDate;
    
    @Column(columnDefinition = "TEXT")
    private String notes;
    
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceItem> items = new ArrayList<>();
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        issuedDate = LocalDateTime.now();
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    public BigDecimal getRemainingAmount() {
        return totalAmount.subtract(paidAmount);
    }
}

