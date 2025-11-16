# Fix: "Main method not found" Error

## The Problem
The error occurs because the IDE needs to:
1. Recognize this as a Java/Spring Boot project
2. Download Maven dependencies
3. Compile the project

## ✅ Solution: Install Java Extensions (Cursor/VS Code)

### Step 1: Install Required Extensions
1. Press `Ctrl+Shift+X` to open Extensions
2. Search and install these extensions:
   - **Extension Pack for Java** (by Microsoft) - This includes:
     - Language Support for Java
     - Debugger for Java
     - Test Runner for Java
     - Maven for Java
     - Project Manager for Java
   - **Spring Boot Extension Pack** (by VMware)

### Step 2: Reload Window
- Press `Ctrl+Shift+P`
- Type: **"Developer: Reload Window"**
- Press Enter
- Wait for Java extensions to initialize

### Step 3: Wait for Maven Download
- Look at bottom-right status bar
- You'll see: "Downloading Java dependencies..."
- Wait 2-5 minutes for Maven to download all dependencies
- Look for: "Java Projects" in the Explorer sidebar

### Step 4: Build the Project
1. Press `Ctrl+Shift+P`
2. Type: **"Java: Clean Java Language Server Workspace"**
3. Press Enter
4. Wait for rebuild

### Step 5: Run the Application
1. Open: `src/main/java/com/hospital/HospitalManagementSystemApplication.java`
2. You should see a **"Run"** button above the `main` method
3. Click it, OR press `F5`
4. Select "Java" as the debugger

---

## 🔧 Alternative: Use IntelliJ IDEA (Easier)

If you're having issues with Cursor/VS Code:

1. **Download IntelliJ IDEA Community** (Free):
   - https://www.jetbrains.com/idea/download/

2. **Open Project**:
   - File → Open → Select this folder
   - Wait for Maven import

3. **Run**:
   - Right-click `HospitalManagementSystemApplication.java`
   - Click "Run"

This is usually easier and more reliable for Spring Boot projects.

---

## 🐛 If Still Not Working

### Check 1: Verify Java is Recognized
1. Open any `.java` file
2. Bottom-right should show Java version
3. If not, Java extension isn't installed properly

### Check 2: Clean and Rebuild
1. `Ctrl+Shift+P`
2. Type: **"Java: Clean Java Language Server Workspace"**
3. Press Enter
4. Restart Cursor/VS Code

### Check 3: Check Maven Status
1. Look at bottom status bar
2. Should see Maven status
3. If errors, check internet connection

### Check 4: Verify File Structure
The file should be at:
```
src/main/java/com/hospital/HospitalManagementSystemApplication.java
```

---

## 📝 Quick Fix Checklist

- [ ] Java extensions installed
- [ ] Window reloaded
- [ ] Maven dependencies downloaded (check status bar)
- [ ] Project compiled (no red errors)
- [ ] Main method visible in file
- [ ] Run button appears above main method

---

## 💡 Why This Happens

The error occurs because:
1. **No Java extensions** = IDE doesn't understand Java
2. **No Maven dependencies** = Can't compile Spring Boot
3. **Project not built** = Classes don't exist yet

Installing the extensions fixes all of these!

---

**After installing extensions and reloading, the error should be gone!** ✅


