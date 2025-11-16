-- Default admin user: admin / admin123
-- Password is bcrypt hash of "admin123"
INSERT INTO users (username, email, password, first_name, last_name, phone, enabled) VALUES
('admin', 'admin@hospital.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iwy7p8f5O', 'Admin', 'User', '1234567890', TRUE);

INSERT INTO user_roles (user_id, role_id) 
SELECT u.id, r.id 
FROM users u, roles r 
WHERE u.username = 'admin' AND r.name = 'ADMIN';

