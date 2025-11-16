# Running in IDE - Step by Step Guide

## Option A: IntelliJ IDEA (Recommended)

### Step 1: Download and Install IntelliJ IDEA
1. Download **IntelliJ IDEA Community Edition** (Free): https://www.jetbrains.com/idea/download/
2. Install it (default settings are fine)

### Step 2: Open the Project
1. Launch IntelliJ IDEA
2. Click **File** → **Open**
3. Navigate to: `C:\Users\91700\hospital-management-system`
4. Click **OK**
5. IntelliJ will detect it's a Maven project and start importing

### Step 3: Wait for Maven Import
- IntelliJ will automatically download all dependencies
- Wait for the progress bar to complete (bottom right)
- This may take 2-5 minutes on first run

### Step 4: Set Up MySQL Database
1. **Start MySQL** (if not already running)
2. **Open MySQL Command Line** or MySQL Workbench
3. **Create the database**:
   ```sql
   CREATE DATABASE hospital_db;
   ```
4. **Verify database exists**:
   ```sql
   SHOW DATABASES;
   ```

### Step 5: Configure Database Connection (if needed)
If your MySQL has different credentials, edit:
- File: `src/main/resources/application.yml`
- Update username/password if needed (default: root/root)

### Step 6: Run the Application
1. In IntelliJ, find: `src/main/java/com/hospital/HospitalManagementSystemApplication.java`
2. **Right-click** on the file
3. Click **Run 'HospitalManagementSystemApplication'**
   - Or click the green play button ▶️ next to the class
4. Wait for "Started HospitalManagementSystemApplication" in console

### Step 7: Access the Application
1. Open browser: **http://localhost:8080**
2. You'll see the login page
3. **Login credentials**:
   - Username: `admin`
   - Password: `admin123`

### Step 8: Verify It's Working
- You should see the Dashboard with statistics tiles
- Navigate through Patients, Doctors, Appointments, etc.

---

## Option B: Eclipse IDE

### Step 1: Download Eclipse
1. Download **Eclipse IDE for Enterprise Java and Web Developers**: https://www.eclipse.org/downloads/
2. Install it

### Step 2: Import Project
1. Launch Eclipse
2. **File** → **Import**
3. Select **Maven** → **Existing Maven Projects**
4. Browse to: `C:\Users\91700\hospital-management-system`
5. Click **Finish**
6. Wait for Maven to download dependencies

### Step 3: Set Up MySQL (Same as IntelliJ)
- Create database: `CREATE DATABASE hospital_db;`

### Step 4: Run Application
1. Find: `HospitalManagementSystemApplication.java`
2. **Right-click** → **Run As** → **Spring Boot App**
3. Wait for application to start

### Step 5: Access Application
- Open: **http://localhost:8080**
- Login: `admin` / `admin123`

---

## Option C: VS Code / Cursor (Current Editor)

### Step 1: Install Extensions
1. Open Extensions (Ctrl+Shift+X)
2. Install these extensions:
   - **Extension Pack for Java** (by Microsoft)
   - **Spring Boot Extension Pack** (by VMware)
   - **Maven for Java** (by Microsoft)

### Step 2: Reload Window
- Press **Ctrl+Shift+P**
- Type: "Reload Window"
- Press Enter

### Step 3: Open Project
- The project should already be open
- VS Code/Cursor will detect Java project

### Step 4: Set Up MySQL
- Same as above: Create `hospital_db` database

### Step 5: Run Application
1. Open: `src/main/java/com/hospital/HospitalManagementSystemApplication.java`
2. Click **Run** button above `main` method
   - Or press **F5** and select "Java"
3. Wait for application to start

### Step 6: Access Application
- Open: **http://localhost:8080**
- Login: `admin` / `admin123`

---

## Troubleshooting

### "Port 8080 already in use"
- Another application is using port 8080
- Solution: Stop that application or change port in `application.yml`

### "Cannot connect to database"
- Check MySQL is running
- Verify database `hospital_db` exists
- Check credentials in `application.yml`

### "Maven dependencies not downloading"
- Check internet connection
- In IntelliJ: **File** → **Invalidate Caches** → **Invalidate and Restart**
- In Eclipse: **Right-click project** → **Maven** → **Update Project**

### "Class not found" errors
- Clean and rebuild:
  - IntelliJ: **Build** → **Rebuild Project**
  - Eclipse: **Project** → **Clean**

---

## Quick Checklist

- [ ] IDE installed (IntelliJ/Eclipse/VS Code)
- [ ] Project opened in IDE
- [ ] Maven dependencies downloaded
- [ ] MySQL running
- [ ] Database `hospital_db` created
- [ ] Application started successfully
- [ ] Browser opened to http://localhost:8080
- [ ] Logged in with admin/admin123

---

## Next Steps After Running

1. **Explore the Dashboard** - See statistics
2. **Add a Patient** - Go to Patients → Add Patient
3. **Add a Doctor** - Go to Doctors → Add Doctor
4. **Schedule Appointment** - Go to Appointments → Schedule
5. **Create Medical Record** - After appointment
6. **Generate Invoice** - Create billing

Enjoy using the Hospital Management System! 🏥

