# 🚀 How to Run the Project

## Current Situation
- ✅ Java 21 is installed
- ❌ Maven is NOT installed
- ❌ MySQL command line not in PATH (but may be installed as service)

## ⚡ EASIEST WAY: Use an IDE

### Option 1: IntelliJ IDEA (5 minutes setup)

1. **Download IntelliJ IDEA Community** (Free):
   - https://www.jetbrains.com/idea/download/
   - Install it

2. **Open Project**:
   - Launch IntelliJ
   - File → Open → Select this folder
   - Wait for Maven to download dependencies (2-5 min)

3. **Ensure MySQL is Running**:
   - Check Windows Services (services.msc)
   - Look for "MySQL" service
   - Start it if not running

4. **Run**:
   - Find: `HospitalManagementSystemApplication.java`
   - Right-click → Run
   - Wait for "Started..." message

5. **Open Browser**: http://localhost:8080
   - Login: `admin` / `admin123`

---

### Option 2: VS Code/Cursor (Your Current Editor)

1. **Install Extensions**:
   - Press `Ctrl+Shift+X`
   - Search and install:
     - "Extension Pack for Java" (by Microsoft)
     - "Spring Boot Extension Pack" (by VMware)

2. **Reload Window**:
   - `Ctrl+Shift+P` → "Reload Window"

3. **Open Main Class**:
   - Open: `src/main/java/com/hospital/HospitalManagementSystemApplication.java`
   - Click "Run" button above `main` method
   - Or press `F5`

4. **Ensure MySQL Running** (check Windows Services)

5. **Open Browser**: http://localhost:8080

---

## 🔧 Alternative: Install Maven (Command Line)

If you prefer command line:

1. **Download Maven**:
   - https://maven.apache.org/download.cgi
   - Download: `apache-maven-3.9.5-bin.zip`

2. **Extract and Add to PATH**:
   - Extract to: `C:\Program Files\Apache\maven`
   - Add to PATH: `C:\Program Files\Apache\maven\bin`
   - Restart terminal

3. **Build and Run**:
   ```powershell
   mvn clean package
   java -jar target\hospital-management-system-1.0.0.jar
   ```

---

## 📋 Quick Checklist

Before running, ensure:
- [ ] IDE installed (IntelliJ/Eclipse) OR Maven installed
- [ ] MySQL Server is running (check Windows Services)
- [ ] Database will be created automatically (or create `hospital_db` manually)

---

## 🎯 Recommended: IntelliJ IDEA

**Why?**
- ✅ Built-in Maven support
- ✅ One-click run
- ✅ Automatic dependency management
- ✅ Best Spring Boot support
- ✅ Free Community Edition

**Steps:**
1. Download: https://www.jetbrains.com/idea/download/
2. Install
3. Open this project folder
4. Wait for Maven import
5. Run `HospitalManagementSystemApplication.java`
6. Done! 🎉

---

## ⚠️ Important Notes

1. **MySQL Must Be Running**: 
   - Check Windows Services
   - Or start MySQL service manually

2. **First Run**: 
   - Takes longer (creates database tables)
   - Subsequent runs are faster

3. **Port 8080**: 
   - Must be free
   - Change in `application.yml` if needed

---

## 🆘 Still Having Issues?

1. Check `QUICK_START.md` for detailed steps
2. Check `RUN_IN_IDE.md` for IDE-specific instructions
3. Verify MySQL is running in Windows Services

