package service;

import model.ExcelData;
import model.PatientsDate;
import model.StationData;

import java.util.ArrayList;
import java.util.List;

public class WeatherServiceImpl implements WeatherService {

    private CalculationService calculationService;
    private ExcelService excelService;
    private ExcelPatientService excelPatientService;

    private List<ExcelData> stations = new ArrayList<>();

    @Override
    public void processDataAndSaveToExcel(StationData stationData, String excelName) {
        calculationService = new CalculationServiceImpl();
        excelService = new ExcelServiceImpl();

        ExcelData excelData = calculationService.calculate(stationData);
        stations.add(excelData);
        excelService.saveToExcelFile(excelData, excelName);

    }

    @Override
    public void assignStationDataToPatientAndSave(PatientsDate patientsDate, String excelName) {
        excelPatientService = new ExcelPatientServiceImpl(patientsDate, stations);
        excelPatientService.saveToExcelWithPatientData(excelName);
    }

}
