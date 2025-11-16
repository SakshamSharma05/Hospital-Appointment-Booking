CREATE TABLE IF NOT EXISTS roles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO roles (name, description) VALUES
('ADMIN', 'System Administrator'),
('DOCTOR', 'Medical Doctor'),
('RECEPTIONIST', 'Reception Staff'),
('PATIENT', 'Patient User');

