package com.hospital.repository;

import com.hospital.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatientId(Long patientId);
    List<Appointment> findByDoctorId(Long doctorId);
    List<Appointment> findByAppointmentDate(LocalDate date);
    
    @Query("SELECT a FROM Appointment a WHERE a.doctor.id = :doctorId " +
           "AND a.appointmentDate = :date " +
           "AND a.appointmentTime = :time " +
           "AND a.status IN ('SCHEDULED', 'CONFIRMED')")
    List<Appointment> findConflictingAppointments(
        @Param("doctorId") Long doctorId,
        @Param("date") LocalDate date,
        @Param("time") LocalTime time
    );
    
    @Query("SELECT a FROM Appointment a WHERE a.doctor.id = :doctorId " +
           "AND a.appointmentDate = :date " +
           "AND a.status IN ('SCHEDULED', 'CONFIRMED')")
    List<Appointment> findByDoctorAndDate(
        @Param("doctorId") Long doctorId,
        @Param("date") LocalDate date
    );
}

