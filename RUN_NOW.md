# 🚀 Run the Project NOW - Step by Step

## ⚡ FASTEST WAY: IntelliJ IDEA (Recommended - 5 minutes)

### Step 1: Download IntelliJ IDEA
1. Go to: https://www.jetbrains.com/idea/download/
2. Download **Community Edition** (FREE)
3. Install it (default settings)

### Step 2: Open Project
1. Launch IntelliJ IDEA
2. **File** → **Open**
3. Select folder: `C:\Users\91700\hospital-management-system`
4. Click **OK**
5. When asked "Trust Project?" → Click **Trust Project**

### Step 3: Wait for Maven Import
- IntelliJ will show: "Maven projects need to be imported"
- Click **"Import Maven Projects"** or **"Enable Auto-Import"**
- Wait 2-5 minutes for dependencies to download
- Look for: "Maven: Resolved" in bottom status bar

### Step 4: Install/Start MySQL
**If MySQL is NOT installed:**
1. Download MySQL: https://dev.mysql.com/downloads/installer/
2. Install MySQL Server
3. Remember root password (or use default)

**If MySQL IS installed:**
1. Open **Services** (Press `Win+R`, type `services.msc`)
2. Find **MySQL** service
3. Right-click → **Start** (if stopped)

### Step 5: Update Database Config (if needed)
If your MySQL password is different:
- Open: `src/main/resources/application.yml`
- Change:
  ```yaml
  username: root
  password: your_mysql_password
  ```

### Step 6: Run the Application
1. In IntelliJ, find: `src/main/java/com/hospital/HospitalManagementSystemApplication.java`
2. **Right-click** on the file
3. Click **Run 'HospitalManagementSystemApplication'**
   - OR click the green ▶️ play button next to line 9
4. Wait for console message: **"Started HospitalManagementSystemApplication"**

### Step 7: Open Browser
1. Open: **http://localhost:8080**
2. You'll see the login page
3. Login:
   - Username: `admin`
   - Password: `admin123`

### Step 8: Success! 🎉
You should see the Dashboard!

---

## 🎯 Alternative: Run in Cursor/VS Code

### Step 1: Install Java Extensions
1. In Cursor, press `Ctrl+Shift+X`
2. Search: **"Extension Pack for Java"**
3. Install it (by Microsoft)
4. Search: **"Spring Boot Extension Pack"**
5. Install it (by VMware)

### Step 2: Reload Window
- Press `Ctrl+Shift+P`
- Type: **"Reload Window"**
- Press Enter

### Step 3: Open Main Class
- Open: `src/main/java/com/hospital/HospitalManagementSystemApplication.java`
- You'll see a **"Run"** button above the `main` method
- Click it, OR press `F5`

### Step 4: Ensure MySQL Running
- Check Windows Services
- Start MySQL if needed

### Step 5: Open Browser
- Go to: **http://localhost:8080**
- Login: `admin` / `admin123`

---

## ⚠️ Common Issues & Solutions

### Issue: "Cannot connect to database"
**Solution:**
1. Check MySQL is running (Windows Services)
2. Verify password in `application.yml`
3. Try creating database manually:
   ```sql
   CREATE DATABASE hospital_db;
   ```

### Issue: "Port 8080 already in use"
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

### Issue: "Maven dependencies not found"
**Solution:**
- In IntelliJ: **File** → **Invalidate Caches** → **Invalidate and Restart**
- Wait for re-import

---

## 📝 Quick Checklist

- [ ] IntelliJ IDEA installed (or VS Code with Java extensions)
- [ ] Project opened in IDE
- [ ] Maven dependencies downloaded
- [ ] MySQL installed and running
- [ ] Database config updated (if needed)
- [ ] Application started
- [ ] Browser opened to http://localhost:8080
- [ ] Logged in successfully

---

## 🎓 What You'll See

After login, you'll see:
- **Dashboard** with statistics tiles
- **Navigation menu** with all modules
- **Quick action buttons**

You can now:
- Add Patients
- Add Doctors
- Schedule Appointments
- Create Medical Records
- Generate Invoices

---

## 💡 Pro Tip

**IntelliJ IDEA is the easiest way** because:
- ✅ Handles Maven automatically
- ✅ One-click run
- ✅ Built-in Spring Boot support
- ✅ Free Community Edition
- ✅ No command line needed

---

**Ready? Start with Step 1 above!** 🚀

