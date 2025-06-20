# Instrucciones para Ejecutar Excel Viewer

## Opción 1: Usar el script automático (Recomendado)

1. Hacer doble clic en el archivo `run.bat`
2. La aplicación se compilará y ejecutará automáticamente

## Opción 2: Usar comandos manuales

### Si tienes Maven instalado:
```bash
mvn clean compile
mvn javafx:run
```

### Si NO tienes Maven instalado (usar el wrapper):
```bash
.\mvnw.cmd clean compile
.\mvnw.cmd javafx:run
```

## Requisitos del Sistema

- Java 17 o superior instalado
- Windows 10/11 (para el script .bat)

## Funcionalidades de la Aplicación

### Importar Excel:
- Clic en "Importar Excel"
- Seleccionar archivo .xlsx o .xls
- Los datos se muestran en la tabla

### Exportar Excel:
- Clic en "Exportar Excel"
- Elegir ubicación y nombre del archivo
- Los datos se guardan en formato Excel

### Limpiar:
- Clic en "Limpiar" para borrar todos los datos

## Solución de Problemas

### Si no se ejecuta:
1. Verificar que Java 17+ esté instalado: `java -version`
2. Verificar que esté en el PATH del sistema
3. Intentar ejecutar manualmente con los comandos de la Opción 2

### Si hay errores de compilación:
1. Verificar que todos los archivos estén en su lugar
2. Limpiar y recompilar: `.\mvnw.cmd clean compile`

## Estructura de Archivos

```
Excel/
├── src/main/java/pe/edu/upeu/excel/Excel/
│   ├── ExcelApplication.java      # Aplicación principal
│   ├── ExcelViewController.java   # Controlador de la interfaz
│   └── ExcelRow.java             # Modelo de datos
├── src/main/resources/
│   ├── fxml/ExcelViewer.fxml     # Interfaz gráfica
│   └── css/styles.css            # Estilos
├── pom.xml                       # Configuración Maven
├── run.bat                       # Script de ejecución
└── README.md                     # Documentación completa
``` 