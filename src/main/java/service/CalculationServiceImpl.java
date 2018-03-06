package service;

import model.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CalculationServiceImpl implements CalculationService{

    private List<ExcelRow> excelPressureRows;
    private List<ExcelRow> excelTemperatureRows;

    @Override
    public ExcelData calculate(StationData stationData) {
        List<HourData> only2002 = getDataFromYear2002(stationData);

        excelPressureRows = calculateDataFor2002(only2002, ExcelRow.ExcelRowType.PRESSURE);
        excelTemperatureRows = calculateDataFor2002(only2002, ExcelRow.ExcelRowType.TEMPERATURE);

        ExcelData excelData = new ExcelData();
        excelData.setExcelPressureRows(excelPressureRows);
        excelData.setExcelTemperatureRows(excelTemperatureRows);
        return excelData;
    }

    //Very big and ugly method :(
    private List<ExcelRow> calculateDataFor2002(List<HourData> hourData2002, ExcelRow.ExcelRowType excelRowType) {

        List<ExcelRow> excelRows = new ArrayList<>();
        int hourInYear = 0;
        double sumPlus, sumMinus, functionGrowUp, functionDecrease;
        double max, min, maxMinDifference, maxMinDifferenceTime;
        int hourPlusStart, hourPlusEnd, hourMinusStart, hourMinusEnd, howManyHoursPlus, howManyHoursMinus;
        int maxHourValue, minHourValue;

        //set max and min number of digits after comma
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);

        // day increment on every 24hours loop
        for (int day = 0; day < hourData2002.size(); day++){

            StationDayRow stationDayRow = new StationDayRow(hourData2002.get(hourInYear).getStationName(), hourData2002.get(hourInYear).getDate());
            ExcelRow excelRow = new ExcelRow();
            excelRow.setStationDayRow(stationDayRow);

            //Clear values
            sumPlus = sumMinus = functionGrowUp = functionDecrease = 0.0;
            max = min = maxMinDifference = maxMinDifferenceTime = 0.0;
            hourPlusStart = hourPlusEnd = hourMinusStart = hourMinusEnd = howManyHoursPlus = howManyHoursMinus = 0;
            maxHourValue = minHourValue = 0;

            Double value = getValueForProperlyExcelRowType(hourData2002.get(hourInYear), excelRowType);
            max = min = value;

            for (int hour = 0; hour < 24; hour++){

                //SECTION calculate min & max
                Double currentValue = getValueForProperlyExcelRowType(hourData2002.get(hourInYear), excelRowType);
                if (max <= currentValue){
                    max = currentValue;
                    maxHourValue = hour;
                }

                if (min >= currentValue){
                    min = currentValue;
                    minHourValue = hour;
                }//END SECTION

                //SECTION calculate temporary values
                if (hour < 23){
                    Double leftValue = getValueForProperlyExcelRowType(hourData2002.get(hourInYear), excelRowType);
                    Double rightValue = getValueForProperlyExcelRowType(hourData2002.get(hourInYear+1), excelRowType);

                    if (leftValue <= rightValue){

                        howManyHoursPlus++;
                        sumPlus += rightValue - leftValue;

                        if (functionDecrease < sumMinus){
                            functionDecrease = sumMinus;
                            hourMinusEnd = hour;
                            hourMinusStart = hour - howManyHoursMinus;
                        }
                        sumMinus = 0;
                        howManyHoursMinus=0;

                    } else {

                        howManyHoursMinus++;
                        sumMinus += leftValue - rightValue;

                        if (functionGrowUp < sumPlus){
                            functionGrowUp = sumPlus;
                            hourPlusEnd = hour;
                            hourPlusStart = hour - howManyHoursPlus;
                        }

                        sumPlus = 0;
                        howManyHoursPlus = 0;
                    }
                }//END SECTION
                hourInYear++;
            }

            maxMinDifferenceTime = Math.abs(maxHourValue-minHourValue);
            maxMinDifference = Math.abs(max-min);
            String maxMinDifferenceString;
            if (maxHourValue < minHourValue){
                //Requirement to show minus or plus values
                maxMinDifference = maxMinDifference * (-1);
                maxMinDifferenceString = String.valueOf(df.format(maxMinDifference));
            } else {
                maxMinDifferenceString = "+" + String.valueOf(df.format(maxMinDifference));
            }

            excelRow.setMax(String.valueOf(df.format(max)));
            excelRow.setMin(String.valueOf(df.format(min)));
            excelRow.setDifferenceMinMax(String.valueOf(maxMinDifferenceString));
            excelRow.setDifferenceMinMaxScope(String.valueOf(df.format(maxMinDifferenceTime)));
            excelRow.setMaxFunctionGrowUp(String.valueOf(df.format(functionGrowUp)));
            excelRow.setMaxFunctionDecrease(String.valueOf(df.format(functionDecrease)));
            excelRow.setMaxFunctionGrowUpStartHour(String.valueOf(df.format(hourPlusStart)));
            excelRow.setMaxFunctionGrowUpEndHour(String.valueOf(df.format(hourPlusEnd)));
            excelRow.setMaxFunctionDecreaseStartHour(String.valueOf(df.format(hourMinusStart)));
            excelRow.setMaxFunctionDecreaseEndHour(String.valueOf(df.format(hourMinusEnd)));
            excelRow.setMaxFunctionGrowUpDifferenceBetweenStartEndHour(String.valueOf(df.format(hourPlusEnd-hourPlusStart)));
            excelRow.setMaxFunctionDecreaseDifferenceBetweenStartEndHour(String.valueOf(df.format(hourMinusEnd-hourMinusStart)));

            excelRows.add(excelRow);

            day = hourInYear;
        }

        return excelRows;
    }

    private Double getValueForProperlyExcelRowType(HourData hourData, ExcelRow.ExcelRowType excelRowType){
        Double value;
        if (excelRowType == ExcelRow.ExcelRowType.PRESSURE){
            value = hourData.getPressure();
        } else {
            value = hourData.getTemperature();
        }
        return value;
    }

    private List<HourData> getDataFromYear2002(StationData stationData){
        List<HourData> hourDataList = new ArrayList<>();
        for (HourData hourData : stationData.getHourDataList()){
            if (hourData.getDate() < 2003000000){
                hourDataList.add(hourData);
            }
        }

        return hourDataList;
    }


}
