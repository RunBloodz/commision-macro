@echo off
setlocal
echo ========================================
echo   Skyblock Macro - Szybka Kompilacja v2
echo ========================================
echo.

:: 1. Proba znalezienia Javy w systemie
set "JAVA_EXE="
where java >nul 2>nul
if %errorlevel% equ 0 (
    for /f "delims=" %%i in ('where java') do set "JAVA_EXE=%%i"
)

:: 2. Jesli nie znaleziono lub sciezka jest bledna, szukamy w typowych miejscach
if not exist "%JAVA_EXE%" (
    if exist "C:\Program Files\Java\jdk-21\bin\java.exe" set "JAVA_EXE=C:\Program Files\Java\jdk-21\bin\java.exe"
    if exist "C:\Program Files\Java\jdk-21.0.1\bin\java.exe" set "JAVA_EXE=C:\Program Files\Java\jdk-21.0.1\bin\java.exe"
    if exist "C:\Program Files\Java\jdk-21.0.2\bin\java.exe" set "JAVA_EXE=C:\Program Files\Java\jdk-21.0.2\bin\java.exe"
)

if "%JAVA_EXE%"=="" (
    echo [BLAD] Nie znaleziono Javy 21!
    echo Pobierz ja stad: https://adoptium.net/temurin/releases/?version=21
    echo Wybierz wersje: Windows x64 JDK.
    pause
    exit /b
)

echo Uzywam Javy: %JAVA_EXE%
"%JAVA_EXE%" -version
echo.

:: 3. Czyszczenie starych ustawien Gradle, ktore moga psuć budowanie
if exist ".gradle" (
    echo Czyszczenie cache...
    rd /s /q .gradle
)

:: 4. Budowanie
echo Rozpoczynam budowanie...
set "JAVA_HOME="
call gradlew.bat build --no-daemon
if %errorlevel% neq 0 (
    echo.
    echo [BLAD] Kompilacja nie powiodla sie.
    echo Sprawdz czy na pewno masz zainstalowana Jave 21 (64-bit).
    pause
    exit /b
)

echo.
echo ========================================
echo   SUKCES! Gotowy mod znajdziesz w:
echo   build\libs\SkyblockMacro.jar
echo ========================================
pause
