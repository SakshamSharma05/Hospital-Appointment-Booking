package com.hospital.controller;

import com.hospital.entity.Appointment;
import com.hospital.entity.Doctor;
import com.hospital.entity.Patient;
import com.hospital.service.AppointmentService;
import com.hospital.service.DoctorService;
import com.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;

    // Patients API
    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PostMapping("/patients")
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient, @RequestParam Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.createPatient(patient, userId));
    }

    @DeleteMapping("/patients/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    // Doctors API
    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/doctors/{id}")
    public ResponseEntity<Doctor> getDoctor(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @PostMapping("/doctors")
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor, 
                                              @RequestParam Long userId,
                                              @RequestParam(required = false) Long departmentId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.createDoctor(doctor, userId, departmentId));
    }

    @DeleteMapping("/doctors/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }

    // Appointments API
    @GetMapping("/appointments")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping("/appointments/{id}")
    public ResponseEntity<Appointment> getAppointment(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

    @PostMapping("/appointments")
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.createAppointment(appointment));
    }

    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}

