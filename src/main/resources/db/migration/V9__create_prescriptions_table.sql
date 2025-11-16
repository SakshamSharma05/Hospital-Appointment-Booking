CREATE TABLE IF NOT EXISTS prescriptions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    patient_id BIGINT NOT NULL,
    doctor_id BIGINT NOT NULL,
    appointment_id BIGINT,
    medical_record_id BIGINT,
    prescription_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES patients(id) ON DELETE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES doctors(id) ON DELETE CASCADE,
    FOREIGN KEY (appointment_id) REFERENCES appointments(id) ON DELETE SET NULL,
    FOREIGN KEY (medical_record_id) REFERENCES medical_records(id) ON DELETE SET NULL
);

CREATE INDEX idx_prescriptions_patient_id ON prescriptions(patient_id);
CREATE INDEX idx_prescriptions_doctor_id ON prescriptions(doctor_id);

