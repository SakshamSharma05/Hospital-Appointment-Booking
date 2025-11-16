package com.hospital.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "prescription_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "prescription_id", nullable = false)
    @NotNull(message = "Prescription is required")
    private Prescription prescription;
    
    @Column(name = "medication_name", nullable = false, length = 255)
    @NotBlank(message = "Medication name is required")
    private String medicationName;
    
    @Column(length = 100)
    private String dosage;
    
    @Column(length = 100)
    private String frequency;
    
    @Column(length = 100)
    private String duration;
    
    @Column(columnDefinition = "TEXT")
    private String instructions;
}

