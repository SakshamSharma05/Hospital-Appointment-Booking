CREATE TABLE IF NOT EXISTS medical_records (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    patient_id BIGINT NOT NULL,
    doctor_id BIGINT NOT NULL,
    appointment_id BIGINT,
    diagnosis TEXT NOT NULL,
    symptoms TEXT,
    treatment TEXT,
    file_path VARCHAR(500),
    record_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES patients(id) ON DELETE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES doctors(id) ON DELETE CASCADE,
    FOREIGN KEY (appointment_id) REFERENCES appointments(id) ON DELETE SET NULL
);

CREATE INDEX idx_medical_records_patient_id ON medical_records(patient_id);
CREATE INDEX idx_medical_records_doctor_id ON medical_records(doctor_id);

