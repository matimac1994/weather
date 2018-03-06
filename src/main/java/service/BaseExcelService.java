package service;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;

public class BaseExcelService {
    private static final String EXCEL_FOLDER_PATH = "src/main/resources/output/";
    private static final String EXCEL_EXTENTION = ".xlsx";

    protected File saveExcelFile(String excelFileName, SXSSFWorkbook workbook){
        String excelFilePath = EXCEL_FOLDER_PATH + excelFileName + EXCEL_EXTENTION;
        File file = new File(excelFilePath);
        try {
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }
}
