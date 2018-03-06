package model;

public class ExcelRow {

    public enum ExcelRowType{
        TEMPERATURE, PRESSURE
    }

    private StationDayRow stationDayRow;

    private String max;
    private String min;
    private String differenceMinMax;
    private String differenceMinMaxScope;

    private String maxFunctionGrowUp;
    private String maxFunctionGrowUpStartHour;
    private String maxFunctionGrowUpEndHour;
    private String maxFunctionGrowUpDifferenceBetweenStartEndHour;

    private String maxFunctionDecrease;
    private String maxFunctionDecreaseStartHour;
    private String maxFunctionDecreaseEndHour;
    private String maxFunctionDecreaseDifferenceBetweenStartEndHour;

    public StationDayRow getStationDayRow() {
        return stationDayRow;
    }

    public void setStationDayRow(StationDayRow stationDayRow) {
        this.stationDayRow = stationDayRow;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getDifferenceMinMax() {
        return differenceMinMax;
    }

    public void setDifferenceMinMax(String differenceMinMax) {
        this.differenceMinMax = differenceMinMax;
    }

    public String getDifferenceMinMaxScope() {
        return differenceMinMaxScope;
    }

    public void setDifferenceMinMaxScope(String differenceMinMaxScope) {
        this.differenceMinMaxScope = differenceMinMaxScope;
    }

    public String getMaxFunctionGrowUp() {
        return maxFunctionGrowUp;
    }

    public void setMaxFunctionGrowUp(String maxFunctionGrowUp) {
        this.maxFunctionGrowUp = maxFunctionGrowUp;
    }

    public String getMaxFunctionGrowUpStartHour() {
        return maxFunctionGrowUpStartHour;
    }

    public void setMaxFunctionGrowUpStartHour(String maxFunctionGrowUpStartHour) {
        this.maxFunctionGrowUpStartHour = maxFunctionGrowUpStartHour;
    }

    public String getMaxFunctionGrowUpEndHour() {
        return maxFunctionGrowUpEndHour;
    }

    public void setMaxFunctionGrowUpEndHour(String maxFunctionGrowUpEndHour) {
        this.maxFunctionGrowUpEndHour = maxFunctionGrowUpEndHour;
    }

    public String getMaxFunctionGrowUpDifferenceBetweenStartEndHour() {
        return maxFunctionGrowUpDifferenceBetweenStartEndHour;
    }

    public void setMaxFunctionGrowUpDifferenceBetweenStartEndHour(String maxFunctionGrowUpDifferenceBetweenStartEndHour) {
        this.maxFunctionGrowUpDifferenceBetweenStartEndHour = maxFunctionGrowUpDifferenceBetweenStartEndHour;
    }

    public String getMaxFunctionDecrease() {
        return maxFunctionDecrease;
    }

    public void setMaxFunctionDecrease(String maxFunctionDecrease) {
        this.maxFunctionDecrease = maxFunctionDecrease;
    }

    public String getMaxFunctionDecreaseStartHour() {
        return maxFunctionDecreaseStartHour;
    }

    public void setMaxFunctionDecreaseStartHour(String maxFunctionDecreaseStartHour) {
        this.maxFunctionDecreaseStartHour = maxFunctionDecreaseStartHour;
    }

    public String getMaxFunctionDecreaseEndHour() {
        return maxFunctionDecreaseEndHour;
    }

    public void setMaxFunctionDecreaseEndHour(String maxFunctionDecreaseEndHour) {
        this.maxFunctionDecreaseEndHour = maxFunctionDecreaseEndHour;
    }

    public String getMaxFunctionDecreaseDifferenceBetweenStartEndHour() {
        return maxFunctionDecreaseDifferenceBetweenStartEndHour;
    }

    public void setMaxFunctionDecreaseDifferenceBetweenStartEndHour(String maxFunctionDecreaseDifferenceBetweenStartEndHour) {
        this.maxFunctionDecreaseDifferenceBetweenStartEndHour = maxFunctionDecreaseDifferenceBetweenStartEndHour;
    }

    @Override
    public String toString() {
        return stationDayRow.toString() +
                max + '\t' +
                min + '\t' +
                differenceMinMax + '\t' +
                differenceMinMaxScope + '\t' +
                maxFunctionGrowUp + '\t' +
                maxFunctionGrowUpStartHour + '\t' +
                maxFunctionGrowUpEndHour + '\t' +
                maxFunctionGrowUpDifferenceBetweenStartEndHour + '\t' +
                maxFunctionDecrease + '\t' +
                maxFunctionDecreaseStartHour + '\t' +
                maxFunctionDecreaseEndHour + '\t' +
                maxFunctionDecreaseDifferenceBetweenStartEndHour + '\t';
    }

    public String toStringWithoutStation() {
        return max + '\t' +
                min + '\t' +
                differenceMinMax + '\t' +
                differenceMinMaxScope + '\t' +
                maxFunctionGrowUp + '\t' +
                maxFunctionGrowUpStartHour + '\t' +
                maxFunctionGrowUpEndHour + '\t' +
                maxFunctionGrowUpDifferenceBetweenStartEndHour + '\t' +
                maxFunctionDecrease + '\t' +
                maxFunctionDecreaseStartHour + '\t' +
                maxFunctionDecreaseEndHour + '\t' +
                maxFunctionDecreaseDifferenceBetweenStartEndHour + '\t';
    }
}
