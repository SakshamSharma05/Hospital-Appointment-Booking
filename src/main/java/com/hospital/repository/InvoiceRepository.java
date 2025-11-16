package com.hospital.repository;

import com.hospital.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByPatientId(Long patientId);
    Optional<Invoice> findByInvoiceNumber(String invoiceNumber);
    List<Invoice> findByStatus(String status);
}

