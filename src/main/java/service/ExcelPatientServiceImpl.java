package service;

import model.ExcelData;
import model.ExcelRow;
import model.PatientsDate;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ExcelPatientServiceImpl extends BaseExcelService implements ExcelPatientService {

    private PatientsDate patientsDate;
    private List<ExcelData> excelDataList;

    private Map<LocalDate, String> stationsMap;

    String[] headers = {
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

    public ExcelPatientServiceImpl(PatientsDate patientsDate, List<ExcelData> excelDataList) {
        this.patientsDate = patientsDate;
        this.excelDataList = excelDataList;
    }

    @Override
    public void saveToExcelWithPatientData(String excelName) {

        createStationsMap();

        SXSSFWorkbook workbook = new SXSSFWorkbook();
        SXSSFSheet sheet = workbook.createSheet(excelName);
        sheet.trackAllColumnsForAutoSizing();

        addHeadersToSheet(sheet);
        fillExcelFile(sheet);

        saveExcelFile(excelName, workbook);
    }

    private void fillExcelFile(SXSSFSheet sheet) {

        for (LocalDate localDate : patientsDate.getDates()){
            int rowNum = sheet.getLastRowNum();
            SXSSFRow row = sheet.createRow(++rowNum);
            createNewCellAndSetValue(row, localDate.toString());
            Optional<String> line = Optional.ofNullable(stationsMap.get(localDate));

            if (line.isPresent()){
                String[] stringCells = line.get().split("\t");

                for (String stringCell : stringCells) {
                    createNewCellAndSetValue(row, stringCell);
                }
            }
        }

        int lastRow = sheet.getLastRowNum();
        SXSSFRow row = sheet.getRow(lastRow);
        int columns = row.getLastCellNum();
        for (int i = 0; i < columns; i++){
            sheet.autoSizeColumn(i);
        }
    }

    private void createNewCellAndSetValue(SXSSFRow row, String value){
        int columnIndex = row.getLastCellNum();
        if (columnIndex < 0) columnIndex = 0;
        SXSSFCell cell = row.createCell(columnIndex);
        cell.setCellValue(value == null ? "" : value);
    }

    private void createStationsMap() {
        stationsMap = new TreeMap<>();
        String expectedPattern = "yyyyMMddHH";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(expectedPattern);
        ExcelData excelData = excelDataList.get(0);
        //Assume that every array list in pressure and temperature rows have the same size
        for (int i = 0; i < excelData.getExcelPressureRows().size(); i++){
            StringBuilder stringBuilder = new StringBuilder();
            for (ExcelData anExcelDataList : excelDataList) {
                ExcelRow pressureRow = anExcelDataList.getExcelPressureRows().get(i);
                ExcelRow temperatureRow = anExcelDataList.getExcelTemperatureRows().get(i);
                stringBuilder.append(pressureRow.toString()).append(temperatureRow.toStringWithoutStation());
            }

            String stationDateString = excelData.getExcelPressureRows().get(i).getStationDayRow().getDate().toString();
            LocalDate stationData = LocalDateTime.parse(stationDateString, formatter).toLocalDate();
            stationsMap.put(stationData, stringBuilder.toString());
        }
    }

    private void addHeadersToSheet(SXSSFSheet sheet) {
        SXSSFRow row = sheet.createRow(0);
        int column = 0;
        SXSSFCell firstCell = row.createCell(column++);
        firstCell.setCellValue("Data pacjenta");
        for (ExcelData e : excelDataList) {
            for (String header : headers) {
                SXSSFCell cell = row.createCell(column++);
                cell.setCellValue(header);
            }
        }
    }
}
