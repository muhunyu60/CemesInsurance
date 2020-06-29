package model.policymodels;

import model.PolicyInterface;

public class HealthPolicyModel implements PolicyInterface {
    String name;
    double insuranceCost;

    public HealthPolicyModel(String name, double insuranceCost) {
        this.name = name;
        this.insuranceCost = insuranceCost;
    }

    @Override
    public String getUnderwriterName() {
        return name;
    }

    @Override
    public String getInsuranceType() {
        return "health";
    }

    @Override
    public double getInsuranceCost() {
        return insuranceCost;
    }
}
