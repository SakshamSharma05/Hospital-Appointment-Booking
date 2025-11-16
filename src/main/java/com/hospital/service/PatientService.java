package com.hospital.service;

import com.hospital.entity.Patient;
import com.hospital.entity.User;
import com.hospital.exception.ResourceNotFoundException;
import com.hospital.repository.PatientRepository;
import com.hospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
    }

    public Patient getPatientByUserId(Long userId) {
        return patientRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found for user id: " + userId));
    }

    public Patient createPatient(Patient patient, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        patient.setUser(user);
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Long id, Patient patientDetails) {
        Patient patient = getPatientById(id);
        patient.setDateOfBirth(patientDetails.getDateOfBirth());
        patient.setGender(patientDetails.getGender());
        patient.setAddress(patientDetails.getAddress());
        patient.setEmergencyContactName(patientDetails.getEmergencyContactName());
        patient.setEmergencyContactPhone(patientDetails.getEmergencyContactPhone());
        patient.setBloodGroup(patientDetails.getBloodGroup());
        patient.setAllergies(patientDetails.getAllergies());
        patient.setMedicalHistory(patientDetails.getMedicalHistory());
        return patientRepository.save(patient);
    }

    public void deletePatient(Long id) {
        Patient patient = getPatientById(id);
        patientRepository.delete(patient);
    }
}

