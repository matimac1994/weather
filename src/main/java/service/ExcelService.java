package service;

import model.ExcelData;

public interface ExcelService {
    void saveToExcelFile(ExcelData excelData, String excelFileName);
}
