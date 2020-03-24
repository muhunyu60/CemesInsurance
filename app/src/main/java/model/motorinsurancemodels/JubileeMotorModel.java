package model.motorinsurancemodels;

import model.AvailableMotorInsuranceModel;

public class JubileeMotorModel implements AvailableMotorInsuranceModel {
    String carPrice;
    String carManufactureYear;
    String insuranceName;
    String windScreenValue;
    String radioValue;
    String carClass;
    String carUse;
    String insurancePrice;
    Boolean excessProtectorValue;
    Boolean politicalViolenceAndTerrorismValue;
    Boolean lossOfUseValue;
    Boolean roadRescueValue;

    JubileeMotorModel(String insuranceName, String carPrice, String carManufactureYear, String carClass) {
        this.carPrice = carPrice;
        this.insuranceName = insuranceName;
        this.carManufactureYear = carManufactureYear;
        this.carClass = carClass;
    }

    public void setCarUse(String carUse) {
        this.carUse = carUse;
    }

    @Override
    public String getPrice() {
        // TODO: Calculation of Price
        return insurancePrice;
    }

    @Override
    public String getInsuranceName() {
        return insuranceName;
    }

    @Override
    public String getWindScreenValue() {
        return windScreenValue;
    }

    @Override
    public String getRadioValue() {
        return radioValue;
    }

    @Override
    public Boolean getExcessProtectorValue() {
        return excessProtectorValue;
    }

    @Override
    public Boolean getPVTValue() {
        return politicalViolenceAndTerrorismValue;
    }

    @Override
    public Boolean getLossOfUseValue() {
        return lossOfUseValue;
    }

    @Override
    public Boolean getRoadRescueValue() {
        return roadRescueValue;
    }

    @Override
    public void setExtras(String windScreenValue, String radioValue, Boolean excessProtectorValue, Boolean pvtValue, Boolean lossOfUseValue, Boolean roadRescueValue) {
        this.windScreenValue = windScreenValue;
        this.radioValue = radioValue;
        this.excessProtectorValue = excessProtectorValue;
        this.politicalViolenceAndTerrorismValue = pvtValue;
        this.lossOfUseValue = lossOfUseValue;
        this.roadRescueValue = roadRescueValue;
    }
}
