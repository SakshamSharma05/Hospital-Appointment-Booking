package com.hospital.service;

import com.hospital.entity.MedicalRecord;
import com.hospital.exception.ResourceNotFoundException;
import com.hospital.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordRepository.findAll();
    }

    public MedicalRecord getMedicalRecordById(Long id) {
        return medicalRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medical record not found with id: " + id));
    }

    public List<MedicalRecord> getMedicalRecordsByPatient(Long patientId) {
        return medicalRecordRepository.findByPatientId(patientId);
    }

    public List<MedicalRecord> getMedicalRecordsByDoctor(Long doctorId) {
        return medicalRecordRepository.findByDoctorId(doctorId);
    }

    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }

    public MedicalRecord updateMedicalRecord(Long id, MedicalRecord medicalRecordDetails) {
        MedicalRecord medicalRecord = getMedicalRecordById(id);
        medicalRecord.setDiagnosis(medicalRecordDetails.getDiagnosis());
        medicalRecord.setSymptoms(medicalRecordDetails.getSymptoms());
        medicalRecord.setTreatment(medicalRecordDetails.getTreatment());
        medicalRecord.setFilePath(medicalRecordDetails.getFilePath());
        return medicalRecordRepository.save(medicalRecord);
    }

    public void deleteMedicalRecord(Long id) {
        MedicalRecord medicalRecord = getMedicalRecordById(id);
        medicalRecordRepository.delete(medicalRecord);
    }
}

