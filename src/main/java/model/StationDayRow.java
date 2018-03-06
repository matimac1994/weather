package model;

public class StationDayRow {
    private String stationName;
    private Integer date;

    public StationDayRow() {
    }

    public StationDayRow(String stationName, Integer date) {
        this.stationName = stationName;
        this.date = date;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return stationName + '\t' +
                date + '\t';
    }
}
