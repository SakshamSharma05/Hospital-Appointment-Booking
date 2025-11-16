# 🚀 Quick Start Guide - IDE Setup

## ✅ Pre-Flight Checklist

Before running, ensure you have:

1. **IDE Installed** (Choose one):
   - ✅ IntelliJ IDEA Community Edition (Recommended)
   - ✅ Eclipse IDE
   - ✅ VS Code/Cursor with Java extensions

2. **MySQL Running**
   - MySQL Server must be installed and running
   - Default port: 3306
   - Default credentials: root/root (or update in application.yml)

3. **Database** (Auto-created)
   - The app will create `hospital_db` automatically
   - But you can create it manually: `CREATE DATABASE hospital_db;`

---

## 📋 Step-by-Step: IntelliJ IDEA

### 1. Open Project
```
File → Open → Select: C:\Users\91700\hospital-management-system
```

### 2. Wait for Maven Import
- Bottom right: "Maven projects need to be imported" → Click "Import"
- Wait 2-5 minutes for dependencies to download

### 3. Verify MySQL is Running
- Check MySQL service is running
- Or start MySQL from command line/services

### 4. Run Application
- Find: `HospitalManagementSystemApplication.java`
- Right-click → **Run 'HospitalManagementSystemApplication'**
- Or click the green ▶️ button

### 5. Check Console
You should see:
```
Started HospitalManagementSystemApplication in X.XXX seconds
```

### 6. Open Browser
- Go to: **http://localhost:8080**
- Login page should appear

### 7. Login
- Username: `admin`
- Password: `admin123`

### 8. Success! 🎉
You should see the Dashboard with statistics tiles.

---

## 🔧 If Something Goes Wrong

### Error: "Cannot connect to database"
**Solution:**
1. Check MySQL is running
2. Verify credentials in `src/main/resources/application.yml`
3. Try creating database manually:
   ```sql
   CREATE DATABASE hospital_db;
   ```

### Error: "Port 8080 already in use"
**Solution:**
1. Find what's using port 8080:
   ```powershell
   netstat -ano | findstr :8080
   ```
2. Stop that application
3. Or change port in `application.yml`:
   ```yaml
   server:
     port: 8081
   ```

### Error: "Maven dependencies not found"
**Solution:**
1. In IntelliJ: **File** → **Invalidate Caches** → **Invalidate and Restart**
2. Wait for re-import
3. Check internet connection

### Error: "ClassNotFoundException"
**Solution:**
1. **Build** → **Rebuild Project**
2. Wait for compilation to complete
3. Try running again

---

## 📝 Database Configuration

If your MySQL has different settings, edit:
**File:** `src/main/resources/application.yml`

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/hospital_db
    username: your_username
    password: your_password
```

---

## 🎯 What to Do After Login

1. **Dashboard** - View statistics
2. **Add Patient** - Patients → Add Patient
3. **Add Doctor** - Doctors → Add Doctor  
4. **Add Department** - Departments → Add Department
5. **Schedule Appointment** - Appointments → Schedule
6. **Create Medical Record** - Medical Records → Add Record
7. **Generate Invoice** - Invoices → Create Invoice

---

## 💡 Tips

- **First Run**: May take longer as Flyway creates all database tables
- **Subsequent Runs**: Much faster
- **Default Admin**: Already created in database (admin/admin123)
- **Auto-Refresh**: Changes to code require restart (or use Spring DevTools)

---

## 📞 Need Help?

Check `RUN_IN_IDE.md` for detailed instructions for each IDE.

