# Excel Viewer - Importar y Exportar Archivos Excel

Una aplicación JavaFX simple para importar y exportar archivos Excel (.xlsx y .xls), mostrando los datos en una tabla con filas y columnas.

## Características

- **Importar Excel**: Carga archivos Excel (.xlsx y .xls) y muestra los datos en una tabla
- **Exportar Excel**: Guarda los datos de la tabla en un archivo Excel
- **Interfaz intuitiva**: Tabla con filas y columnas claramente organizadas
- **Información en tiempo real**: Muestra el número de filas, columnas y el archivo actual
- **Diseño moderno**: Interfaz con estilos CSS personalizados

## Requisitos

- Java 17 o superior
- Maven 3.6 o superior

## Instalación y Ejecución

1. **Clonar o descargar el proyecto**

2. **Compilar el proyecto**:
   ```bash
   mvn clean compile
   ```

3. **Ejecutar la aplicación**:
   ```bash
   mvn javafx:run
   ```

   O alternativamente:
   ```bash
   mvn exec:java -Dexec.mainClass="pe.edu.upeu.excel.Excel.ExcelApplication"
   ```

## Uso

### Importar un archivo Excel

1. Hacer clic en el botón **"Importar Excel"**
2. Seleccionar un archivo Excel (.xlsx o .xls)
3. Los datos se mostrarán automáticamente en la tabla
4. La información del archivo se mostrará en la barra de estado

### Exportar datos a Excel

1. Asegurarse de que hay datos en la tabla
2. Hacer clic en el botón **"Exportar Excel"**
3. Elegir la ubicación y nombre del archivo
4. Los datos se guardarán en formato Excel

### Limpiar datos

- Hacer clic en el botón **"Limpiar"** para borrar todos los datos de la tabla

## Estructura del Proyecto

```
src/
├── main/
│   ├── java/pe/edu/upeu/excel/Excel/
│   │   ├── ExcelApplication.java      # Clase principal de la aplicación
│   │   ├── ExcelViewController.java   # Controlador de la interfaz
│   │   └── ExcelRow.java             # Modelo de datos
│   └── resources/
│       ├── fxml/
│       │   └── ExcelViewer.fxml      # Archivo de interfaz
│       └── css/
│           └── styles.css            # Estilos CSS
```

## Tecnologías Utilizadas

- **JavaFX**: Framework para la interfaz gráfica
- **Apache POI**: Biblioteca para manejar archivos Excel
- **Maven**: Gestión de dependencias y build
- **FXML**: Definición de la interfaz de usuario

## Dependencias

- `javafx-controls`: Controles de JavaFX
- `javafx-fxml`: Soporte para FXML
- `poi`: Biblioteca Apache POI para Excel
- `poi-ooxml`: Soporte para archivos .xlsx

## Notas

- La aplicación lee la primera hoja del archivo Excel
- Los encabezados se toman de la primera fila
- Se soportan archivos .xlsx y .xls
- La tabla se ajusta automáticamente al contenido 