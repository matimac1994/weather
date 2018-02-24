package model;

import java.util.ArrayList;
import java.util.List;

public class ExcelData {

    private List<ExcelRow> excelPressureRows;
    private List<ExcelRow> excelTemperatureRows;

    public ExcelData() {
    }

    public ExcelData(List<ExcelRow> excelPressureRows, List<ExcelRow> excelTemperatureRows) {
        this.excelPressureRows = excelPressureRows;
        this.excelTemperatureRows = excelTemperatureRows;
    }

    public List<ExcelRow> getExcelPressureRows() {
        return excelPressureRows;
    }

    public void setExcelPressureRows(List<ExcelRow> excelPressureRows) {
        this.excelPressureRows = excelPressureRows;
    }

    public List<ExcelRow> getExcelTemperatureRows() {
        return excelTemperatureRows;
    }

    public void setExcelTemperatureRows(List<ExcelRow> excelTemperatureRows) {
        this.excelTemperatureRows = excelTemperatureRows;
    }
}
