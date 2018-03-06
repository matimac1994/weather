import model.PatientsDate;
import model.StationData;
import org.apache.commons.io.FilenameUtils;
import service.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class WeatherApplication {

    private static final String PATIENT_STATION_EXCEL_NAME = "Pacjenci_Stacje";

    public static void main(String[] args){

        File importFiles = new File("src/main/resources/import_files/stations");

        WeatherService weatherService = new WeatherServiceImpl();

        Arrays.stream(importFiles.listFiles()).forEach(stationFile -> {
            StationData stationData = new StationData(stationFile.getPath());
            weatherService.processDataAndSaveToExcel(stationData, FilenameUtils.getBaseName(stationFile.getName()));
        });

        File patients_date = new File("src/main/resources/import_files/patients/patients_date.txt");

        PatientsDate patientsDate = new PatientsDate(patients_date.getPath());

        weatherService.assignStationDataToPatientAndSave(patientsDate, PATIENT_STATION_EXCEL_NAME);
    }
}
