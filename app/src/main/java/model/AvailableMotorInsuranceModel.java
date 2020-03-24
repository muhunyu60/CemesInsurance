package model;

public interface AvailableMotorInsuranceModel {
       double getPrice();
       double getRadioValue();
       double getWindScreenValue();

       String getInsuranceName();

       Boolean includesExcessProtector();
       Boolean includesLossOfUse();
       Boolean includesPVT();
       Boolean includesRoadRescue();

       void setExtras(
               double windScreenValue,
               double radioValue,
               Boolean includesExcessProtector,
               Boolean includesPVT,
               Boolean includeslossOfUse,
               Boolean includesRoadRescue
       );

}
