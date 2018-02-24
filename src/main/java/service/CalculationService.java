package service;

import model.ExcelData;
import model.StationData;

public interface CalculationService {
    ExcelData calculate(StationData stationData);
}
