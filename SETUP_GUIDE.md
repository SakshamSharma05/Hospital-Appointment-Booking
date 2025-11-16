# Setup and Run Guide

## Option 1: Using an IDE (Easiest - Recommended)

### IntelliJ IDEA or Eclipse
1. **Open the project** in your IDE
2. **Wait for Maven to download dependencies** (automatic)
3. **Ensure MySQL is running** on localhost:3306
4. **Create database**: `CREATE DATABASE hospital_db;`
5. **Run the application**:
   - IntelliJ: Right-click `HospitalManagementSystemApplication.java` → Run
   - Eclipse: Right-click project → Run As → Spring Boot App

### Default Login
- Username: `admin`
- Password: `admin123`

---

## Option 2: Install Maven (Command Line)

### Install Maven
1. Download from: https://maven.apache.org/download.cgi
2. Extract and add to PATH
3. Verify: `mvn --version`

### Install MySQL
1. Download from: https://dev.mysql.com/downloads/mysql/
2. Install and start MySQL service
3. Create database: `CREATE DATABASE hospital_db;`

### Run Application
```bash
# Build
mvn clean package

# Run
java -jar target/hospital-management-system-1.0.0.jar
```

---

## Option 3: Using Docker (Recommended for Production)

### Install Docker Desktop
1. Download from: https://www.docker.com/products/docker-desktop
2. Install and start Docker Desktop

### Run with Docker Compose
```bash
# Build the application first (requires Maven)
mvn clean package

# Start everything
docker-compose up -d

# View logs
docker-compose logs -f app

# Stop
docker-compose down
```

---

## Quick Start (If you have an IDE)

1. **Open project** in IntelliJ IDEA or Eclipse
2. **Wait for dependencies** to download
3. **Start MySQL** (if not running)
4. **Create database**:
   ```sql
   CREATE DATABASE hospital_db;
   ```
5. **Run** `HospitalManagementSystemApplication.java`
6. **Open browser**: http://localhost:8080
7. **Login**: admin / admin123

---

## Database Configuration

If your MySQL is not on default settings, update `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/hospital_db
    username: your_username
    password: your_password
```

---

## Troubleshooting

### Port 8080 already in use
Change port in `application.yml`:
```yaml
server:
  port: 8081
```

### Database connection error
- Ensure MySQL is running
- Check credentials in `application.yml`
- Verify database exists

### Maven not found
- Install Maven or use an IDE with built-in Maven support

