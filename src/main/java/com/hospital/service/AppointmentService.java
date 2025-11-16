package com.hospital.service;

import com.hospital.entity.Appointment;
import com.hospital.entity.Doctor;
import com.hospital.entity.Patient;
import com.hospital.exception.BadRequestException;
import com.hospital.exception.ResourceNotFoundException;
import com.hospital.repository.AppointmentRepository;
import com.hospital.repository.DoctorRepository;
import com.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
    }

    public List<Appointment> getAppointmentsByPatient(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    public List<Appointment> getAppointmentsByDoctor(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    public List<Appointment> getAppointmentsByDate(LocalDate date) {
        return appointmentRepository.findByAppointmentDate(date);
    }

    public Appointment createAppointment(Appointment appointment) {
        Patient patient = patientRepository.findById(appointment.getPatient().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        
        Doctor doctor = doctorRepository.findById(appointment.getDoctor().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        // Check for conflicts
        List<Appointment> conflicts = appointmentRepository.findConflictingAppointments(
                doctor.getId(), 
                appointment.getAppointmentDate(), 
                appointment.getAppointmentTime()
        );

        if (!conflicts.isEmpty()) {
            throw new BadRequestException("Doctor already has an appointment at this time");
        }

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setStatus("SCHEDULED");
        
        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointment(Long id, Appointment appointmentDetails) {
        Appointment appointment = getAppointmentById(id);
        
        // If time/date changed, check for conflicts
        if (!appointment.getAppointmentDate().equals(appointmentDetails.getAppointmentDate()) ||
            !appointment.getAppointmentTime().equals(appointmentDetails.getAppointmentTime()) ||
            !appointment.getDoctor().getId().equals(appointmentDetails.getDoctor().getId())) {
            
            Doctor doctor = doctorRepository.findById(appointmentDetails.getDoctor().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
            
            List<Appointment> conflicts = appointmentRepository.findConflictingAppointments(
                    doctor.getId(),
                    appointmentDetails.getAppointmentDate(),
                    appointmentDetails.getAppointmentTime()
            );

            // Exclude current appointment from conflict check
            conflicts.removeIf(a -> a.getId().equals(id));
            
            if (!conflicts.isEmpty()) {
                throw new BadRequestException("Doctor already has an appointment at this time");
            }
            
            appointment.setDoctor(doctor);
            appointment.setAppointmentDate(appointmentDetails.getAppointmentDate());
            appointment.setAppointmentTime(appointmentDetails.getAppointmentTime());
        }
        
        appointment.setStatus(appointmentDetails.getStatus());
        appointment.setReason(appointmentDetails.getReason());
        appointment.setNotes(appointmentDetails.getNotes());
        
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long id) {
        Appointment appointment = getAppointmentById(id);
        appointmentRepository.delete(appointment);
    }
}

