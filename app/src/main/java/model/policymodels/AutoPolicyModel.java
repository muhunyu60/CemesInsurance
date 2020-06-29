package model.policymodels;

import model.PolicyInterface;

public class AutoPolicyModel implements PolicyInterface {
    String name;
    double insuranceCost;

    public AutoPolicyModel(String name, double insuranceCost) {
        this.name = name;
        this.insuranceCost = insuranceCost;
    }

    @Override
    public String getUnderwriterName() {
        return name;
    }

    @Override
    public double getInsuranceCost() {
        return insuranceCost;
    }

    @Override
    public String getInsuranceType() {
        return "auto";
    }
}
