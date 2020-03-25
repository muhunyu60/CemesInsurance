package model.motorinsurancemodels;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import model.AvailableMotorInsuranceModel;

public class JubileeMotorModel implements AvailableMotorInsuranceModel {
    private double carPrice;
    private double insurancePrice;
    private double windScreenValue;
    private double radioValue;
    private int carManufactureYear;
    private String insuranceName;
    private String carClass;
    private String carUse;
    private Date insuranceStartDate;
    private Boolean includesExcessProtector;
    private Boolean includesPoliticalViolenceAndTerrorism;
    private Boolean includesLossOfUse;
    private Boolean includesRoadRescue;

    public static final int WINDSCREEN_MINIMUM_PRICE = 30000;
    public static final int RADIO_MINIMUM_PRICE = 30000;
    public static final int LOSS_OF_USE_PRICE = 3000;
    public static final int ROAD_RESCUE_PRICE = 7500;
    public static final int PVT_PRICE = 3750;
    public static final int EXCESS_PROTECTOR_PRICE = 3750;

    public JubileeMotorModel(double carPrice, int carManufactureYear, String carClass, String insuranceStartDate, String carUse) {
        this.insuranceName = "Jubilee";
        this.carPrice = carPrice;
        this.carManufactureYear = carManufactureYear;
        this.carClass = carClass;
        this.insuranceStartDate = new SimpleDateFormat("yyyy/MM/dd").parse(insuranceStartDate, new ParsePosition(0));
        this.carUse = carUse;
        this.carUse = "private";
        setExtras(
                0.0,
                0.0,
                false,
                false,
                false,
                false
        );
    }

    public void setCarUse(String carUse) {
        this.carUse = carUse;
    }

    @Override
    public double getPrice() {
        calculatePrice();
        return insurancePrice;
    }

    @Override
    public String getInsuranceName() {
        return insuranceName;
    }

    @Override
    public double getWindScreenValue() {
        return windScreenValue;
    }

    @Override
    public double getRadioValue() {
        return radioValue;
    }

    @Override
    public Boolean includesExcessProtector() {
        return includesExcessProtector;
    }

    @Override
    public Boolean includesPVT() {
        return includesPoliticalViolenceAndTerrorism;
    }

    @Override
    public Boolean includesLossOfUse() {
        return includesLossOfUse;
    }

    @Override
    public Boolean includesRoadRescue() {
        return includesRoadRescue;
    }

    @Override
    public void setExtras(double windScreenValue, double radioValue, Boolean includesExcessProtector, Boolean includesPVT, Boolean includeslossOfUse, Boolean includesRoadRescue) {
        this.windScreenValue = windScreenValue;
        this.radioValue = radioValue;
        this.includesExcessProtector = includesExcessProtector;
        this.includesPoliticalViolenceAndTerrorism = includesPVT;
        this.includesLossOfUse = includeslossOfUse;
        this.includesRoadRescue = includesRoadRescue;
        calculatePrice();
    }

    public double getWindScreenPrice() {
        if(windScreenValue > WINDSCREEN_MINIMUM_PRICE) {
            return (windScreenValue - WINDSCREEN_MINIMUM_PRICE) / 10;
        }
        return 0;
    }

    public double getRadioPrice() {
        if(radioValue > RADIO_MINIMUM_PRICE) {
            return (windScreenValue - RADIO_MINIMUM_PRICE) / 10;
        }
        return 0;
    }

    private void calculatePrice() {
        double rate;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(insuranceStartDate);
        int startYear = calendar.get(Calendar.YEAR);
        int period = startYear - carManufactureYear;

        if (carUse.toLowerCase().equalsIgnoreCase("private")) {
            // Set the car rate based on the age of the car before the start of the insurance date
            if(carPrice < 500000.00) {
                rate = 0.075;
            } else if(carPrice <= 800000) {
                if(period < 7) {
                    rate = 0.035;
                } else if(period < 10) {
                    rate = 0.0425;
                } else if(period < 12) {
                    rate = 0.045;
                } else if(period < 15) {
                    rate = 0.05;
                } else {
                    rate = 0.075;
                }
            } else if(carPrice <= 1000000) {
                if(period < 7) {
                    rate = 0.0325;
                } else if(period < 10) {
                    rate = 0.0375;
                } else if(period < 12) {
                    rate = 0.04;
                } else if(period < 15) {
                    rate = 0.045;
                } else {
                    rate = 0.075;
                }
            } else {
                if(period < 7) {
                    rate = 0.03;
                } else if(period < 10) {
                    rate = 0.0325;
                } else if(period < 12) {
                    rate = 0.035;
                } else if(period < 15) {
                    rate = 0.04;
                } else {
                    rate = 0.075;
                }
            }

            insurancePrice = rate * carPrice;

            // Minimum premium value is 20,000
            if(insurancePrice < 20000) {
                insurancePrice = 20000;
            }

            if(includesExcessProtector) {
                insurancePrice += EXCESS_PROTECTOR_PRICE;
            }

            if(includesPoliticalViolenceAndTerrorism) {
                insurancePrice += PVT_PRICE;
            }

            if(includesRoadRescue) {
                insurancePrice += ROAD_RESCUE_PRICE;
            }

            if(includesLossOfUse) {
                insurancePrice += LOSS_OF_USE_PRICE;
            }

            insurancePrice += getRadioPrice();
            insurancePrice += getWindScreenPrice();
        }
    }
}
