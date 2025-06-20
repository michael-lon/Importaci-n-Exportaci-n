@echo off
echo ========================================
echo    Excel Viewer - JavaFX Application
echo ========================================
echo.

echo Paso 1: Descargando dependencias...
call mvnw.cmd dependency:resolve

echo.
echo Paso 2: Compilando proyecto...
call mvnw.cmd clean compile

echo.
echo Paso 3: Ejecutando aplicacion...
echo (Se abrira una ventana nueva)
echo.
call mvnw.cmd javafx:run

echo.
echo Aplicacion cerrada.
pause 