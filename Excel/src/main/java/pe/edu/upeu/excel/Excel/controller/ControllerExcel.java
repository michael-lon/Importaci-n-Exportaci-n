package pe.edu.upeu.excel.Excel.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControllerExcel {

    @FXML private TableView<ObservableList<String>> tableView;
    @FXML private Label statusLabel;
    @FXML private Label rowCountLabel;
    @FXML private Label columnCountLabel;
    @FXML private Label fileNameLabel;

    private ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
    private List<String> columnNames = new ArrayList<>();
    private File currentFile = null;

    @FXML
    public void initialize() {
        tableView.setPlaceholder(new Label("No hay datos para mostrar"));
        updateStatus("Listo para importar/exportar");
    }

    @FXML
    private void importExcel() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo Excel");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Archivos Excel", "*.xlsx", "*.xls"),
            new FileChooser.ExtensionFilter("Todos los archivos", "*.*")
        );

        Stage stage = (Stage) tableView.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            loadExcelFile(selectedFile);
        }
    }

    @FXML
    private void exportExcel() {
        if (data.isEmpty()) {
            showAlert("Error", "No hay datos para exportar", Alert.AlertType.WARNING);
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar archivo Excel");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Archivo Excel (.xlsx)", "*.xlsx"),
            new FileChooser.ExtensionFilter("Archivo Excel (.xls)", "*.xls")
        );

        Stage stage = (Stage) tableView.getScene().getWindow();
        File selectedFile = fileChooser.showSaveDialog(stage);

        if (selectedFile != null) {
            saveExcelFile(selectedFile);
        }
    }

    @FXML
    private void clearData() {
        data.clear();
        columnNames.clear();
        tableView.getColumns().clear();
        tableView.setItems(data);
        currentFile = null;
        updateStatus("Datos limpiados");
        updateCounts();
        fileNameLabel.setText("Ninguno seleccionado");
    }

    private void loadExcelFile(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            Workbook workbook;
            
            if (file.getName().toLowerCase().endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(fis);
            } else {
                workbook = new HSSFWorkbook(fis);
            }

            Sheet sheet = workbook.getSheetAt(0);
            data.clear();
            columnNames.clear();
            tableView.getColumns().clear();

            // Read headers
            Row headerRow = sheet.getRow(0);
            if (headerRow != null) {
                for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                    org.apache.poi.ss.usermodel.Cell cell = headerRow.getCell(i);
                    String columnName = (cell != null) ? getCellValueAsString(cell) : "Columna " + (i + 1);
                    columnNames.add(columnName);
                    
                    TableColumn<ObservableList<String>, String> column = new TableColumn<>(columnName);
                    final int colIndex = i;
                    column.setCellValueFactory(param -> {
                        ObservableList<String> row = param.getValue();
                        if (row != null && colIndex < row.size()) {
                            return new javafx.beans.property.SimpleStringProperty(row.get(colIndex));
                        }
                        return new javafx.beans.property.SimpleStringProperty("");
                    });
                    tableView.getColumns().add(column);
                }
            }

            // Read data rows
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    ObservableList<String> rowData = FXCollections.observableArrayList();
                    for (int j = 0; j < columnNames.size(); j++) {
                        org.apache.poi.ss.usermodel.Cell cell = row.getCell(j);
                        rowData.add(getCellValueAsString(cell));
                    }
                    data.add(rowData);
                }
            }

            tableView.setItems(data);
            currentFile = file;
            updateStatus("Archivo importado exitosamente: " + file.getName());
            updateCounts();
            fileNameLabel.setText(file.getName());

            workbook.close();

        } catch (IOException e) {
            showAlert("Error", "Error al leer el archivo: " + e.getMessage(), Alert.AlertType.ERROR);
            updateStatus("Error al importar archivo");
        }
    }

    private void saveExcelFile(File file) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Datos");

            // Write headers
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < columnNames.size(); i++) {
                org.apache.poi.ss.usermodel.Cell cell = headerRow.createCell(i);
                cell.setCellValue(columnNames.get(i));
            }

            // Write data
            for (int i = 0; i < data.size(); i++) {
                Row row = sheet.createRow(i + 1);
                ObservableList<String> rowData = data.get(i);
                for (int j = 0; j < rowData.size(); j++) {
                    org.apache.poi.ss.usermodel.Cell cell = row.createCell(j);
                    cell.setCellValue(rowData.get(j));
                }
            }

            // Auto-size columns
            for (int i = 0; i < columnNames.size(); i++) {
                sheet.autoSizeColumn(i);
            }

            try (FileOutputStream fos = new FileOutputStream(file)) {
                workbook.write(fos);
            }

            updateStatus("Archivo exportado exitosamente: " + file.getName());

        } catch (IOException e) {
            showAlert("Error", "Error al guardar el archivo: " + e.getMessage(), Alert.AlertType.ERROR);
            updateStatus("Error al exportar archivo");
        }
    }

    private String getCellValueAsString(org.apache.poi.ss.usermodel.Cell cell) {
        if (cell == null) {
            return "";
        }
        
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    private void updateStatus(String message) {
        statusLabel.setText(message);
    }

    private void updateCounts() {
        rowCountLabel.setText(String.valueOf(data.size()));
        columnCountLabel.setText(String.valueOf(columnNames.size()));
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
} 