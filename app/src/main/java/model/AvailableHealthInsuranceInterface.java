package model;

import java.util.HashMap;

public interface AvailableHealthInsuranceInterface {
    int getPrice();
    String getInsuranceProviderName();
    HashMap getBenefits();
    void addBenefit(String key, float Value);
    void removeBenefit(String key);
}
