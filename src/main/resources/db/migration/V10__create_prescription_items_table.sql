CREATE TABLE IF NOT EXISTS prescription_items (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    prescription_id BIGINT NOT NULL,
    medication_name VARCHAR(255) NOT NULL,
    dosage VARCHAR(100),
    frequency VARCHAR(100),
    duration VARCHAR(100),
    instructions TEXT,
    FOREIGN KEY (prescription_id) REFERENCES prescriptions(id) ON DELETE CASCADE
);

CREATE INDEX idx_prescription_items_prescription_id ON prescription_items(prescription_id);

