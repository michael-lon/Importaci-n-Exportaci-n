package pe.edu.upeu.excel.Excel.filas;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class FilasExcel {
    private final List<StringProperty> cells;

    public FilasExcel(List<String> values) {
        this.cells = new java.util.ArrayList<>();
        for (String value : values) {
            cells.add(new SimpleStringProperty(value));
        }
    }

    public StringProperty getCellProperty(int index) {
        if (index >= 0 && index < cells.size()) {
            return cells.get(index);
        }
        return new SimpleStringProperty("");
    }

    public String getCellValue(int index) {
        if (index >= 0 && index < cells.size()) {
            return cells.get(index).get();
        }
        return "";
    }

    public void setCellValue(int index, String value) {
        if (index >= 0 && index < cells.size()) {
            cells.get(index).set(value);
        }
    }

    public int getCellCount() {
        return cells.size();
    }
} 