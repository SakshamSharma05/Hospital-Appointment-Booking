# Hospital Management System

A complete Hospital Management System built with Java 17, Spring Boot 3, Spring Security, Spring Data JPA, MySQL, Thymeleaf, and Bootstrap 5.

## Features

- **User Authentication**: Role-based access control (ADMIN, DOCTOR, RECEPTIONIST, PATIENT)
- **Patient Management**: Complete CRUD operations for patients
- **Doctor Management**: Manage doctors with specializations and departments
- **Department Management**: Organize hospital departments
- **Appointment Scheduling**: Schedule appointments with conflict checking
- **Medical Records**: Maintain patient medical records
- **Prescriptions**: Create and manage prescriptions with medications
- **Invoicing**: Generate and manage invoices
- **REST API**: Basic REST endpoints for patients, doctors, and appointments
- **Responsive UI**: Modern Bootstrap 5 interface

## Technology Stack

- **Backend**: Java 17, Spring Boot 3.2.0
- **Security**: Spring Security with form-based authentication
- **Database**: MySQL 8.0 with Flyway migrations
- **Frontend**: Thymeleaf templates with Bootstrap 5
- **Build Tool**: Maven
- **Containerization**: Docker & Docker Compose

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+ (or use Docker)
- Docker & Docker Compose (optional)

## Setup Instructions

### Option 1: Using Docker (Recommended)

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd hospital-management-system
   ```

2. **Build the application**
   ```bash
   mvn clean package
   ```

3. **Start with Docker Compose**
   ```bash
   docker-compose up -d
   ```

   This will start:
   - MySQL database on port 3306
   - Application on port 8080

4. **Access the application**
   - Open browser: http://localhost:8080
   - Login with default credentials:
     - Username: `admin`
     - Password: `admin123`

### Option 2: Manual Setup

1. **Create MySQL database**
   ```sql
   CREATE DATABASE hospital_db;
   ```

2. **Update application.yml**
   - Update database connection details if needed
   - Default: `localhost:3306/hospital_db`
   - Username: `root`
   - Password: `root`

3. **Run Flyway migrations**
   ```bash
   mvn flyway:migrate
   ```
   Or migrations will run automatically on application start.

4. **Build and run**
   ```bash
   mvn clean package
   java -jar target/hospital-management-system-1.0.0.jar
   ```

5. **Access the application**
   - Open browser: http://localhost:8080
   - Login with default credentials:
     - Username: `admin`
     - Password: `admin123`

## Default Credentials

- **Username**: admin
- **Password**: admin123
- **Role**: ADMIN

## Project Structure

```
hospital-management-system/
├── src/
│   ├── main/
│   │   ├── java/com/hospital/
│   │   │   ├── config/          # Configuration classes
│   │   │   ├── controller/      # Web and REST controllers
│   │   │   ├── entity/          # JPA entities
│   │   │   ├── repository/      # JPA repositories
│   │   │   ├── service/         # Business logic
│   │   │   ├── security/        # Security configuration
│   │   │   ├── exception/       # Exception handling
│   │   │   └── dto/             # Data transfer objects
│   │   └── resources/
│   │       ├── templates/       # Thymeleaf templates
│   │       ├── db/migration/    # Flyway migrations
│   │       └── application.yml  # Application configuration
│   └── test/                    # Test files
├── docker-compose.yml
├── Dockerfile
└── pom.xml
```

## API Endpoints

### REST API (Basic - No Authentication Required)

#### Patients
- `GET /api/patients` - Get all patients
- `GET /api/patients/{id}` - Get patient by ID
- `POST /api/patients?userId={userId}` - Create patient
- `DELETE /api/patients/{id}` - Delete patient

#### Doctors
- `GET /api/doctors` - Get all doctors
- `GET /api/doctors/{id}` - Get doctor by ID
- `POST /api/doctors?userId={userId}&departmentId={deptId}` - Create doctor
- `DELETE /api/doctors/{id}` - Delete doctor

#### Appointments
- `GET /api/appointments` - Get all appointments
- `GET /api/appointments/{id}` - Get appointment by ID
- `POST /api/appointments` - Create appointment
- `DELETE /api/appointments/{id}` - Delete appointment

## Web Pages

- `/login` - Login page
- `/dashboard` - Main dashboard
- `/patients` - Patient list
- `/doctors` - Doctor list
- `/departments` - Department list
- `/appointments` - Appointment list
- `/medical-records` - Medical records list
- `/prescriptions` - Prescription list
- `/invoices` - Invoice list

## Database Schema

The application uses Flyway for database migrations. All migrations are in `src/main/resources/db/migration/`.

Key tables:
- `users` - User accounts
- `roles` - User roles
- `patients` - Patient information
- `doctors` - Doctor information
- `departments` - Hospital departments
- `appointments` - Appointment scheduling
- `medical_records` - Medical records
- `prescriptions` - Prescriptions
- `invoices` - Invoices

## Development

### Running Tests
```bash
mvn test
```

### Building for Production
```bash
mvn clean package -DskipTests
```

### Docker Commands

Stop containers:
```bash
docker-compose down
```

View logs:
```bash
docker-compose logs -f app
```

## Configuration

### Application Properties

Key configuration in `application.yml`:
- Database connection
- JWT settings (if needed)
- File upload directory
- Email settings (if configured)

### Environment Variables

For Docker deployment, you can set:
- `DB_HOST` - Database host
- `DB_PORT` - Database port
- `DB_NAME` - Database name
- `DB_USERNAME` - Database username
- `DB_PASSWORD` - Database password
- `JWT_SECRET` - JWT secret key
- `MAIL_USERNAME` - Email username
- `MAIL_PASSWORD` - Email password

## Troubleshooting

1. **Database Connection Error**
   - Ensure MySQL is running
   - Check database credentials in `application.yml`
   - Verify database exists

2. **Port Already in Use**
   - Change port in `application.yml`: `server.port: 8081`
   - Or stop the process using port 8080

3. **Migration Errors**
   - Drop and recreate database
   - Or manually fix migration issues

## License

This project is for educational purposes.

## Support

For issues and questions, please create an issue in the repository.

