package com.hospital.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @NotNull(message = "User is required")
    private User user;
    
    @Column(name = "date_of_birth", nullable = false)
    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;
    
    @Column(nullable = false, length = 10)
    @NotBlank(message = "Gender is required")
    private String gender;
    
    @Column(columnDefinition = "TEXT")
    private String address;
    
    @Column(name = "emergency_contact_name", length = 100)
    private String emergencyContactName;
    
    @Column(name = "emergency_contact_phone", length = 20)
    private String emergencyContactPhone;
    
    @Column(name = "blood_group", length = 5)
    private String bloodGroup;
    
    @Column(columnDefinition = "TEXT")
    private String allergies;
    
    @Column(name = "medical_history", columnDefinition = "TEXT")
    private String medicalHistory;
    
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Appointment> appointments = new ArrayList<>();
    
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<MedicalRecord> medicalRecords = new ArrayList<>();
    
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Prescription> prescriptions = new ArrayList<>();
    
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Invoice> invoices = new ArrayList<>();
    
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

