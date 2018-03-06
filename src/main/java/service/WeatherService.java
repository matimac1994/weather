package service;

import model.PatientsDate;
import model.StationData;

public interface WeatherService {
    void processDataAndSaveToExcel(StationData stationData, String excelName);
    void assignStationDataToPatientAndSave(PatientsDate patientsDate, String excelName);
}
