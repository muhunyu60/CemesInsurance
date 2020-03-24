package model;

public interface AvailableMotorInsuranceModel {
       String getPrice();
       String getInsuranceName();
       String getWindScreenValue();
       String getRadioValue();
       Boolean getExcessProtectorValue();
       Boolean getPVTValue();
       Boolean getLossOfUseValue();
       Boolean getRoadRescueValue();

       void setExtras(
               String windScreenValue,
               String radioValue,
               Boolean excessProtectorValue,
               Boolean pvtValue,
               Boolean lossOfUseValue,
               Boolean roadRescueValue
       );

}
