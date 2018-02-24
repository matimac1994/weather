import model.StationData;
import org.apache.commons.io.FilenameUtils;
import service.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class WeatherApplication {
    public static void main(String[] args){

        File importFiles = new File("src/main/resources/import_files");

        Arrays.stream(importFiles.listFiles()).forEach(stationFile -> {
            StationData stationData = new StationData(stationFile.getPath());
            WeatherService weatherService = new WeatherServiceImpl();
            weatherService.processDataAndSaveToExcel(stationData, FilenameUtils.getBaseName(stationFile.getName()));
        });
    }
}
