package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StationData {

    private List<HourData> hourDataList = new ArrayList<>();

    public StationData(String path) {
        Path filePath = Paths.get(path);
        try (BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\t");
                hourDataList.add(new HourData(data[0],
                        Integer.parseInt(data[1]),
                        Double.parseDouble(data[2]),
                        Double.parseDouble(data[3])));
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

    }

    public List<HourData> getHourDataList() {
        return hourDataList;
    }

    public void setHourDataList(List<HourData> hourDataList) {
        this.hourDataList = hourDataList;
    }
}
