package service;

import model.ExcelData;
import model.ExcelRow;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class ExcelServiceImpl extends BaseExcelService implements ExcelService {

    private static final String EXCEL_FOLDER_PATH = "src/main/resources/output/";
    private static final String EXCEL_EXTENTION = ".xlsx";

    private String[] headers = {
            "STACJA",
            "DATA",
            "Max ciśnienie",
            "Min ciśnienie",
            "Max różnica ciśnień",
            "Czas w jakim nastąpiła",
            "Maksymalna plus (funkcja rośnie)",
            "Start(godzina)",
            "Koniec(godzina)",
            "Różnica(koniec-start)",
            "Maksymalna minus (funkcja maleje)",
            "Start(godzina)",
            "Koniec(godzina)",
            "Różnica(koniec-start)",
            "Max temp",
            "Min temp",
            "Max różnica temp",
            "Czas w jakim nastąpiła",
            "Maksymalna plus (funkcja rośnie)",
            "Start(godzina)",
            "Koniec(godzina)",
            "Różnica(koniec-start)",
            "Maksymalna minus (funkcja maleje)",
            "Start(godzina)",
            "Koniec(godzina)",
            "Różnica(koniec-start)"
    };

    @Override
    public void saveToExcelFile(ExcelData excelData, String excelFileName) {

        SXSSFWorkbook workbook = new SXSSFWorkbook();
        SXSSFSheet sheet = workbook.createSheet(excelFileName);
        sheet.trackAllColumnsForAutoSizing();
        addHeadersToSheet(sheet);

        fillDataForSheet(sheet, excelData);

        saveExcelFile(excelFileName, workbook);
    }

    private void fillDataForSheet(SXSSFSheet sheet, ExcelData excelData) {
        sheet.setRandomAccessWindowSize(400);
        int rowNum = sheet.getLastRowNum();
        int rowNumForIteration = rowNum;
        for (ExcelRow excelRow : excelData.getExcelPressureRows()){
            SXSSFRow row = sheet.createRow(++rowNumForIteration);
            createNewCellAndSetValue(row, excelRow.getStationDayRow().getStationName());
            createNewCellAndSetValue(row, String.valueOf(excelRow.getStationDayRow().getDate()));
            createCellsForRow(row, excelRow);
        }
        rowNumForIteration = rowNum;
        for (ExcelRow excelRow : excelData.getExcelTemperatureRows()){
            SXSSFRow row = sheet.getRow(++rowNumForIteration);
            createCellsForRow(row, excelRow);
        }

        int lastRow = sheet.getLastRowNum();
        SXSSFRow row = sheet.getRow(lastRow);
        int columns = row.getLastCellNum();
        for (int i = 0; i < columns; i++){
            sheet.autoSizeColumn(i);
        }
    }

    private void createCellsForRow(SXSSFRow row, ExcelRow excelRow){
        createNewCellAndSetValue(row, excelRow.getMax());
        createNewCellAndSetValue(row, excelRow.getMin());
        createNewCellAndSetValue(row, excelRow.getDifferenceMinMax());
        createNewCellAndSetValue(row, excelRow.getDifferenceMinMaxScope());
        createNewCellAndSetValue(row, excelRow.getMaxFunctionGrowUp());
        createNewCellAndSetValue(row, excelRow.getMaxFunctionGrowUpStartHour());
        createNewCellAndSetValue(row, excelRow.getMaxFunctionGrowUpEndHour());
        createNewCellAndSetValue(row, excelRow.getMaxFunctionGrowUpDifferenceBetweenStartEndHour());
        createNewCellAndSetValue(row, excelRow.getMaxFunctionDecrease());
        createNewCellAndSetValue(row, excelRow.getMaxFunctionDecreaseStartHour());
        createNewCellAndSetValue(row, excelRow.getMaxFunctionDecreaseEndHour());
        createNewCellAndSetValue(row, excelRow.getMaxFunctionDecreaseDifferenceBetweenStartEndHour());
    }

    private void createNewCellAndSetValue(SXSSFRow row, String value){
        int columnIndex = row.getLastCellNum();
        if (columnIndex < 0) columnIndex = 0;
        SXSSFCell cell = row.createCell(columnIndex);
        cell.setCellValue(value == null ? "" : value);
    }

    private void addHeadersToSheet(SXSSFSheet sheet) {
        SXSSFRow row = sheet.createRow(0);
        int column = 0;
        for (String header : headers){
            SXSSFCell cell = row.createCell(column++);
            cell.setCellValue(header);
        }
    }
}
