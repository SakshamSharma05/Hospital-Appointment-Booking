CREATE TABLE IF NOT EXISTS invoices (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    invoice_number VARCHAR(50) NOT NULL UNIQUE,
    patient_id BIGINT NOT NULL,
    appointment_id BIGINT,
    total_amount DECIMAL(10, 2) NOT NULL,
    paid_amount DECIMAL(10, 2) DEFAULT 0.00,
    status VARCHAR(20) DEFAULT 'PENDING',
    due_date DATE,
    issued_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    paid_date TIMESTAMP NULL,
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES patients(id) ON DELETE CASCADE,
    FOREIGN KEY (appointment_id) REFERENCES appointments(id) ON DELETE SET NULL
);

CREATE INDEX idx_invoices_patient_id ON invoices(patient_id);
CREATE INDEX idx_invoices_status ON invoices(status);
CREATE INDEX idx_invoices_invoice_number ON invoices(invoice_number);

