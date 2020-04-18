package model.healthinsurancemodels;

import java.util.HashMap;

import model.AvailableHealthInsuranceInterface;

public class JubileeHealthInsurance implements AvailableHealthInsuranceInterface {
    private int annualPremium = 0;
    private int numberOfChildren;
    private final String insuranceProviderName  = "Jubilee";
    private String coverLimit;
    private String applicantDOB;
    private String spouseDOB;
    private Boolean isFamilyInsured = false;
    private Boolean isSpouseInsured = false;
    private Boolean hasPreexistingCondition;

    JubileeHealthInsurance(String coverLimit, String applicantDOB, Boolean hasPreexistingCondition) {
        this.coverLimit = coverLimit;
        this.applicantDOB = applicantDOB;
        this.hasPreexistingCondition = hasPreexistingCondition;
    }

    JubileeHealthInsurance(String coverLimit, String applicantDOB, String spouseDOB, Boolean hasPreexistingCondition) {
        this.coverLimit = coverLimit;
        this.applicantDOB = applicantDOB;
        this.spouseDOB = spouseDOB;
        isSpouseInsured = true;

        //If any family member has a preexisting condition
        this.hasPreexistingCondition = hasPreexistingCondition;
    }

    JubileeHealthInsurance(String coverLimit, String applicantDOB, String spouseDOB, int numberOfChildren, Boolean hasPreexistingCondition) {
        this.coverLimit = coverLimit;
        this.applicantDOB = applicantDOB;
        this.spouseDOB = spouseDOB;
        this.numberOfChildren = numberOfChildren;
        isSpouseInsured = true;
        isFamilyInsured = true;

        //If any family member has a preexisting condition
        this.hasPreexistingCondition = hasPreexistingCondition;
    }

    @Override
    public String getInsuranceProviderName() {
        return insuranceProviderName;
    }

    @Override
    public int getPrice() {
        switch (coverLimit) {
            case "up to 500,000":
                // TODO: Update Calculation
                break;
            case "up to 1,000,000":
                // TODO: Update Calculation
                break;
            case "up to 2,000,000":
                // TODO: Update Calculation
                break;
            case "up to 5,000,000":
                // TODO: Update Calculation
                break;
            case "up to 10,000,000":
                // TODO: Update Calculation
                break;
            default:
                // TODO: Update Calculation
                break;
        }
        return annualPremium;
    }

    @Override
    public HashMap getBenefits() {
        return null;
    }

    @Override
    public void addBenefit(String key, float Value) {

    }

    @Override
    public void removeBenefit(String key) {

    }
}
