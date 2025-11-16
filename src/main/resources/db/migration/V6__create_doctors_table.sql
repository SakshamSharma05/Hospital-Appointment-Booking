CREATE TABLE IF NOT EXISTS doctors (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL UNIQUE,
    department_id BIGINT,
    specialization VARCHAR(100),
    license_number VARCHAR(50) UNIQUE,
    years_of_experience INT,
    consultation_fee DECIMAL(10, 2),
    bio TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (department_id) REFERENCES departments(id) ON DELETE SET NULL
);

CREATE INDEX idx_doctors_user_id ON doctors(user_id);
CREATE INDEX idx_doctors_department_id ON doctors(department_id);

