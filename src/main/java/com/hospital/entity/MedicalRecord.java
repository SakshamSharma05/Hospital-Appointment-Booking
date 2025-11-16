package com.hospital.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "medical_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecord {
    
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
    
    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "Diagnosis is required")
    private String diagnosis;
    
    @Column(columnDefinition = "TEXT")
    private String symptoms;
    
    @Column(columnDefinition = "TEXT")
    private String treatment;
    
    @Column(name = "file_path", length = 500)
    private String filePath;
    
    @Column(name = "record_date")
    private LocalDateTime recordDate;
    
    @OneToMany(mappedBy = "medicalRecord", cascade = CascadeType.ALL)
    private java.util.List<Prescription> prescriptions = new java.util.ArrayList<>();
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        recordDate = LocalDateTime.now();
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

