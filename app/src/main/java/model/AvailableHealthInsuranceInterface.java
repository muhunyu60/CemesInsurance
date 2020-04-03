package model;

import java.util.HashMap;

public interface AvailableHealthInsuranceInterface {
    String getInsuranceProviderName();
    String getPrice();
    HashMap getBenefits();
    void addBenefit(String key, float Value);
    void removeBenefit(String key);
}
