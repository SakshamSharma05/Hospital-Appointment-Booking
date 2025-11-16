package com.hospital.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "prescriptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prescription {
    
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
    
    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
    
    @ManyToOne
    @JoinColumn(name = "medical_record_id")
    private MedicalRecord medicalRecord;
    
    @Column(name = "prescription_date")
    private LocalDateTime prescriptionDate;
    
    @Column(columnDefinition = "TEXT")
    private String notes;
    
    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PrescriptionItem> items = new ArrayList<>();
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        prescriptionDate = LocalDateTime.now();
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

