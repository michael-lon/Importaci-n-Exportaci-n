@echo off
echo ===========================================
echo   Creando Paquete Ejectuable (.JAR)
echo ===========================================
echo.
echo Esto descargara dependencias y empaquetara
echo todo en un solo archivo...
echo.

call mvnw.cmd clean package

echo.
echo ===========================================
echo   ¡LISTO!
echo ===========================================
echo.
echo Revisa la carpeta 'target'.
echo Encontraras un archivo como 'Excel-0.0.1-SNAPSHOT.jar'.
echo.
echo >> Haz doble clic en ese archivo .jar para ejecutar la aplicacion. <<
echo.
pause 