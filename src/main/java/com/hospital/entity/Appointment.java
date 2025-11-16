package com.hospital.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "appointments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @NotNull(message = "Patient is required")
    private Patient patient;
    
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    @NotNull(message = "Doctor is required")
    private Doctor doctor;
    
    @Column(name = "appointment_date", nullable = false)
    @NotNull(message = "Appointment date is required")
    private LocalDate appointmentDate;
    
    @Column(name = "appointment_time", nullable = false)
    @NotNull(message = "Appointment time is required")
    private LocalTime appointmentTime;
    
    @Column(nullable = false, length = 20)
    private String status = "SCHEDULED"; // SCHEDULED, CONFIRMED, COMPLETED, CANCELLED
    
    @Column(columnDefinition = "TEXT")
    private String reason;
    
    @Column(columnDefinition = "TEXT")
    private String notes;
    
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;
    
    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL)
    private java.util.List<MedicalRecord> medicalRecords = new java.util.ArrayList<>();
    
    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL)
    private java.util.List<Prescription> prescriptions = new java.util.ArrayList<>();
    
    @OneToOne(mappedBy = "appointment")
    private Invoice invoice;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

