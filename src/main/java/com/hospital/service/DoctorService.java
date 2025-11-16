package com.hospital.service;

import com.hospital.entity.Department;
import com.hospital.entity.Doctor;
import com.hospital.entity.User;
import com.hospital.exception.BadRequestException;
import com.hospital.exception.ResourceNotFoundException;
import com.hospital.repository.DepartmentRepository;
import com.hospital.repository.DoctorRepository;
import com.hospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
    }

    public Doctor getDoctorByUserId(Long userId) {
        return doctorRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found for user id: " + userId));
    }

    public List<Doctor> getDoctorsByDepartment(Long departmentId) {
        return doctorRepository.findByDepartmentId(departmentId);
    }

    public Doctor createDoctor(Doctor doctor, Long userId, Long departmentId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        
        if (doctor.getLicenseNumber() != null && 
            doctorRepository.findByLicenseNumber(doctor.getLicenseNumber()).isPresent()) {
            throw new BadRequestException("License number already exists");
        }
        
        doctor.setUser(user);
        if (departmentId != null) {
            Department department = departmentRepository.findById(departmentId)
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + departmentId));
            doctor.setDepartment(department);
        }
        return doctorRepository.save(doctor);
    }

    public Doctor updateDoctor(Long id, Doctor doctorDetails) {
        Doctor doctor = getDoctorById(id);
        
        if (doctorDetails.getLicenseNumber() != null && 
            !doctor.getLicenseNumber().equals(doctorDetails.getLicenseNumber()) &&
            doctorRepository.findByLicenseNumber(doctorDetails.getLicenseNumber()).isPresent()) {
            throw new BadRequestException("License number already exists");
        }
        
        doctor.setSpecialization(doctorDetails.getSpecialization());
        doctor.setLicenseNumber(doctorDetails.getLicenseNumber());
        doctor.setYearsOfExperience(doctorDetails.getYearsOfExperience());
        doctor.setConsultationFee(doctorDetails.getConsultationFee());
        doctor.setBio(doctorDetails.getBio());
        
        if (doctorDetails.getDepartment() != null) {
            Department department = departmentRepository.findById(doctorDetails.getDepartment().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
            doctor.setDepartment(department);
        }
        
        return doctorRepository.save(doctor);
    }

    public void deleteDoctor(Long id) {
        Doctor doctor = getDoctorById(id);
        doctorRepository.delete(doctor);
    }
}

