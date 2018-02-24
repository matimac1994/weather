package service;

import model.ExcelData;
import model.StationData;

public class WeatherServiceImpl implements WeatherService {

    private CalculationService calculationService;
    private ExcelService excelService;

    @Override
    public void processDataAndSaveToExcel(StationData stationData, String excelName) {
        calculationService = new CalculationServiceImpl();
        excelService = new ExcelServiceImpl();

        ExcelData excelData = calculationService.calculate(stationData);
        excelService.saveToExcelFile(excelData, excelName);

    }
}
