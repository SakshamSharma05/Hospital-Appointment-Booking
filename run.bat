@echo off
echo ========================================
echo Hospital Management System
echo ========================================
echo.

echo Checking Java...
java -version
if %errorlevel% neq 0 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install Java 17 or higher
    pause
    exit /b 1
)

echo.
echo Checking if project is built...
if not exist "target\hospital-management-system-1.0.0.jar" (
    echo.
    echo Project not built yet. Building with Maven...
    echo.
    
    REM Try Maven wrapper first
    if exist "mvnw.cmd" (
        echo Using Maven Wrapper...
        call mvnw.cmd clean package -DskipTests
    ) else (
        echo.
        echo ERROR: Maven is not installed and Maven wrapper not found.
        echo.
        echo Please do ONE of the following:
        echo 1. Install Maven: https://maven.apache.org/download.cgi
        echo 2. Use an IDE (IntelliJ IDEA or Eclipse) - Recommended!
        echo 3. Install Java extensions in VS Code/Cursor
        echo.
        echo See RUN_IN_IDE.md for IDE setup instructions.
        echo.
        pause
        exit /b 1
    )
)

echo.
echo Starting application...
echo.
echo Application will be available at: http://localhost:8080
echo Default login: admin / admin123
echo.
echo Press Ctrl+C to stop the application
echo.

java -jar target\hospital-management-system-1.0.0.jar

pause

