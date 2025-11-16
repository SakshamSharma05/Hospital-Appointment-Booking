package com.hospital.service;

import com.hospital.entity.Prescription;
import com.hospital.entity.PrescriptionItem;
import com.hospital.exception.ResourceNotFoundException;
import com.hospital.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    public Prescription getPrescriptionById(Long id) {
        return prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription not found with id: " + id));
    }

    public List<Prescription> getPrescriptionsByPatient(Long patientId) {
        return prescriptionRepository.findByPatientId(patientId);
    }

    public Prescription createPrescription(Prescription prescription) {
        if (prescription.getItems() != null) {
            for (PrescriptionItem item : prescription.getItems()) {
                item.setPrescription(prescription);
            }
        }
        return prescriptionRepository.save(prescription);
    }

    public Prescription updatePrescription(Long id, Prescription prescriptionDetails) {
        Prescription prescription = getPrescriptionById(id);
        prescription.setNotes(prescriptionDetails.getNotes());
        
        // Update items
        if (prescriptionDetails.getItems() != null) {
            prescription.getItems().clear();
            for (PrescriptionItem item : prescriptionDetails.getItems()) {
                item.setPrescription(prescription);
                prescription.getItems().add(item);
            }
        }
        
        return prescriptionRepository.save(prescription);
    }

    public void deletePrescription(Long id) {
        Prescription prescription = getPrescriptionById(id);
        prescriptionRepository.delete(prescription);
    }
}

