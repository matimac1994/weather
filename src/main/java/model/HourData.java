package model;

public class HourData {
    private String stationName;
    private Integer date;
    private Double pressure;
    private Double temperature;

    public HourData() {
    }

    public HourData(String stationName, Integer date,Double pressure, Double temperature) {
        this.stationName = stationName;
        this.date = date;
        this.pressure = pressure;
        this.temperature = temperature;
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

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
}
