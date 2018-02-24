package service;

import model.StationData;

public interface WeatherService {
    void processDataAndSaveToExcel(StationData stationData, String excelName);
}
