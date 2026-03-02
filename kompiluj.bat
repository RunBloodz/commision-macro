@echo off
echo ========================================
echo   Skyblock Macro - Szybka Kompilacja
echo ========================================
echo.
echo Sprawdzanie Javy...
java -version
if %errorlevel% neq 0 (
    echo BLAD: Nie znaleziono Javy! Zainstaluj Java 21 JDK (64-bit).
    pause
    exit /b
)
echo.
echo Budowanie moda...
call gradlew.bat build
if %errorlevel% neq 0 (
    echo.
    echo BLAD: Kompilacja nie powiodla sie!
    pause
    exit /b
)
echo.
echo ========================================
echo   SUKCES! Gotowy mod znajdziesz w:
echo   build\libs\SkyblockMacro.jar
echo ========================================
pause
