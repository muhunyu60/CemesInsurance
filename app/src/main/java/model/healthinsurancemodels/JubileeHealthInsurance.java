package model.healthinsurancemodels;

import android.widget.ImageView;

import com.cemesinsurance.cemes_customer.R;

import java.util.Calendar;
import java.util.HashMap;

import model.AvailableHealthInsuranceInterface;

public class JubileeHealthInsurance implements AvailableHealthInsuranceInterface {
    private int numberOfChildren;
    private final String insuranceProviderName  = "Jubilee";
    private String coverLimit;
    private String applicantDOB;
    private String spouseDOB;
    private Boolean isFamilyInsured = false;
    private Boolean isSpouseInsured = false;
    private Boolean hasPreexistingCondition;
    private Boolean includesOutpatient = false;
    private Boolean includesMaternity = false;
    private Boolean includesDental = false;
    private Boolean includesOptical = false;
    private Boolean includesLastExpense = false;
    private Boolean includesPersonalAccident = false;

    // Applicant Only
    public JubileeHealthInsurance(String coverLimit, String applicantDOB, Boolean hasPreexistingCondition) {
        this.coverLimit = coverLimit;
        this.applicantDOB = applicantDOB;
        this.hasPreexistingCondition = hasPreexistingCondition;
    }

    // Applicant and Spouse
    public JubileeHealthInsurance(String coverLimit, String applicantDOB, String spouseDOB, Boolean hasPreexistingCondition) {
        this.coverLimit = coverLimit;
        this.applicantDOB = applicantDOB;
        this.spouseDOB = spouseDOB;
        isSpouseInsured = true;

        //If any family member has a preexisting condition
        this.hasPreexistingCondition = hasPreexistingCondition;
    }

    // Applicant and Kids
    public JubileeHealthInsurance(String coverLimit, String applicantDOB, int numberOfChildren, Boolean hasPreexistingCondition) {
        this.coverLimit = coverLimit;
        this.applicantDOB = applicantDOB;
        this.numberOfChildren = numberOfChildren;
        isFamilyInsured = true;

        //If any family member has a preexisting condition
        this.hasPreexistingCondition = hasPreexistingCondition;
    }

    // Applicant, Spouse and Kids
    public JubileeHealthInsurance(String coverLimit, String applicantDOB, String spouseDOB, int numberOfChildren, Boolean hasPreexistingCondition) {
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
    public int getLogoId() {
        return R.drawable.jubilee;
    }

    @Override
    public int getPrice() {
        int applicantAge = getAge(applicantDOB);
        int applicantPremium = 0;

        switch (coverLimit) {
            //TODO: Add Outpatient calculations for each

            case "Up to 500,000":
                if(applicantAge <= 30) {
                    applicantPremium = 17000;
                } else if(applicantAge <= 40) {
                    applicantPremium = 18000;
                } else if(applicantAge <= 50) {
                    applicantPremium = 18515;
                } else if(applicantAge <= 59) {
                    applicantPremium = 24715;
                } else {
                    applicantPremium = 30894;
                }

                if(includesDental) {
                    applicantPremium += 2300;
                }

                if(includesOptical) {
                    applicantPremium += 2300;
                }

                if(includesLastExpense) {
                    applicantPremium += 900;
                }

                if(includesPersonalAccident) {
                    applicantPremium += 500;
                }

                //If spouse is insured, add the calculations
                if(isSpouseInsured) {
                    int spouseAge = getAge(spouseDOB);
                    if(spouseAge<=30) {
                        applicantPremium += 12500;
                    } else if(spouseAge <= 40) {
                        applicantPremium += 15000;
                    } else if(spouseAge <= 50) {
                        applicantPremium += 15515;
                    } else if(spouseAge <= 59) {
                        applicantPremium += 20760;
                    } else {
                        applicantPremium += 25960;
                    }

                    if(includesDental) {
                        applicantPremium += 2300;
                    }

                    if(includesOptical) {
                        applicantPremium += 2300;
                    }

                    if(includesLastExpense) {
                        applicantPremium += 900;
                    }

                    if(includesPersonalAccident) {
                        applicantPremium += 500;
                    }
                }

                // If children are insured, multiply the rate per child by the number of children
                // Then add it to the premium
                if(isFamilyInsured) {
                    applicantPremium += numberOfChildren*8200;

                    if(includesDental) {
                        applicantPremium += numberOfChildren*2300;
                    }

                    if(includesOptical) {
                        applicantPremium += numberOfChildren*2300;
                    }

                    if(includesLastExpense) {
                        applicantPremium += numberOfChildren*900;
                    }
                }

                if(includesMaternity) {
                    applicantPremium += 25700;
                }

                break;
            case "Up to 1,000,000":
                if(applicantAge <= 30) {
                    applicantPremium = 21200;
                } else if(applicantAge <= 40) {
                    applicantPremium = 22100;
                } else if(applicantAge <= 50) {
                    applicantPremium = 26500;
                } else if(applicantAge <= 59) {
                    applicantPremium = 32490;
                } else {
                    applicantPremium = 40613;
                }

                if(includesDental) {
                    applicantPremium += 3000;
                }

                if(includesOptical) {
                    applicantPremium += 3000;
                }

                if(includesLastExpense) {
                    applicantPremium += 900;
                }

                if(includesPersonalAccident) {
                    applicantPremium += 500;
                }

                //If spouse is insured, add the calculations
                if(isSpouseInsured) {
                    int spouseAge = getAge(spouseDOB);
                    if(spouseAge<=30) {
                        applicantPremium += 17800;
                    } else if(spouseAge <= 40) {
                        applicantPremium += 18600;
                    } else if(spouseAge <= 50) {
                        applicantPremium += 21500;
                    } else if(spouseAge <= 59) {
                        applicantPremium += 27270;
                    } else {
                        applicantPremium += 34088;
                    }

                    if(includesDental) {
                        applicantPremium += 3000;
                    }

                    if(includesOptical) {
                        applicantPremium += 3000;
                    }

                    if(includesLastExpense) {
                        applicantPremium += 900;
                    }

                    if(includesPersonalAccident) {
                        applicantPremium += 500;
                    }
                }

                // If children are insured, multiply the rate per child by the number of children
                // Then add it to the premium
                if(isFamilyInsured) {
                    applicantPremium += numberOfChildren*11500;

                    if(includesDental) {
                        applicantPremium += numberOfChildren*3000;
                    }

                    if(includesOptical) {
                        applicantPremium += numberOfChildren*3000;
                    }

                    if(includesLastExpense) {
                        applicantPremium += numberOfChildren*900;
                    }
                }

                if(includesMaternity) {
                    applicantPremium += 27600;
                }
                break;
            case "Up to 2,000,000":
                if(applicantAge <= 30) {
                    applicantPremium = 25000;
                } else if(applicantAge <= 40) {
                    applicantPremium = 26500;
                } else if(applicantAge <= 50) {
                    applicantPremium = 33000;
                } else if(applicantAge <= 59) {
                    applicantPremium = 39870;
                } else {
                    applicantPremium = 49838;
                }

                if(includesDental) {
                    applicantPremium += 6100;
                }

                if(includesOptical) {
                    applicantPremium += 6100;
                }

                if(includesLastExpense) {
                    applicantPremium += 1400;
                }

                if(includesPersonalAccident) {
                    applicantPremium += 500;
                }

                //If spouse is insured, add the calculations
                if(isSpouseInsured) {
                    int spouseAge = getAge(spouseDOB);
                    if(spouseAge<=30) {
                        applicantPremium += 21000;
                    } else if(spouseAge <= 40) {
                        applicantPremium += 22500;
                    } else if(spouseAge <= 50) {
                        applicantPremium += 27000;
                    } else if(spouseAge <= 59) {
                        applicantPremium += 33480;
                    } else {
                        applicantPremium += 41850;
                    }

                    if(includesDental) {
                        applicantPremium += 6100;
                    }

                    if(includesOptical) {
                        applicantPremium += 6100;
                    }

                    if(includesLastExpense) {
                        applicantPremium += 1400;
                    }

                    if(includesPersonalAccident) {
                        applicantPremium += 500;
                    }
                }

                // If children are insured, multiply the rate per child by the number of children
                // Then add it to the premium
                if(isFamilyInsured) {
                    applicantPremium += numberOfChildren*14000;

                    if(includesDental) {
                        applicantPremium += numberOfChildren*6100;
                    }

                    if(includesOptical) {
                        applicantPremium += numberOfChildren*6100;
                    }

                    if(includesLastExpense) {
                        applicantPremium += numberOfChildren*1400;
                    }
                }

                if(includesMaternity) {
                    applicantPremium += 29700;
                }
                break;
            case "Up to 3,000,000":
                if(applicantAge <= 30) {
                    applicantPremium = 30000;
                } else if(applicantAge <= 40) {
                    applicantPremium = 32800;
                } else if(applicantAge <= 50) {
                    applicantPremium = 38000;
                } else if(applicantAge <= 59) {
                    applicantPremium = 49770;
                } else {
                    applicantPremium = 54747;
                }

                if(includesDental) {
                    applicantPremium += 9100;
                }

                if(includesOptical) {
                    applicantPremium += 9100;
                }

                if(includesLastExpense) {
                    applicantPremium += 1800;
                }

                if(includesPersonalAccident) {
                    applicantPremium += 500;
                }

                //If spouse is insured, add the calculations
                if(isSpouseInsured) {
                    int spouseAge = getAge(spouseDOB);
                    if(spouseAge<=30) {
                        applicantPremium += 26000;
                    } else if(spouseAge <= 40) {
                        applicantPremium += 28500;
                    } else if(spouseAge <= 50) {
                        applicantPremium += 34000;
                    } else if(spouseAge <= 59) {
                        applicantPremium += 41760;
                    } else {
                        applicantPremium += 45936;
                    }

                    if(includesDental) {
                        applicantPremium += 9100;
                    }

                    if(includesOptical) {
                        applicantPremium += 9100;
                    }

                    if(includesLastExpense) {
                        applicantPremium += 1800;
                    }

                    if(includesPersonalAccident) {
                        applicantPremium += 500;
                    }
                }

                // If children are insured, multiply the rate per child by the number of children
                // Then add it to the premium
                if(isFamilyInsured) {
                    applicantPremium += numberOfChildren*17300;

                    if(includesDental) {
                        applicantPremium += numberOfChildren*9100;
                    }

                    if(includesOptical) {
                        applicantPremium += numberOfChildren*9100;
                    }

                    if(includesLastExpense) {
                        applicantPremium += numberOfChildren*1800;
                    }
                }

                if(includesMaternity) {
                    applicantPremium += 29700;
                }

                break;
            default:
                // Royal Plan
                if(applicantAge <= 30) {
                    applicantPremium = 35000;
                } else if(applicantAge <= 40) {
                    applicantPremium = 37000;
                } else if(applicantAge <= 50) {
                    applicantPremium = 45410;
                } else if(applicantAge <= 59) {
                    applicantPremium = 57420;
                } else {
                    applicantPremium = 63162;
                }

                if(includesDental) {
                    applicantPremium += 15200;
                }

                if(includesOptical) {
                    applicantPremium += 15200;
                }

                if(includesLastExpense) {
                    applicantPremium += 1800;
                }

                if(includesPersonalAccident) {
                    applicantPremium += 500;
                }
                //If spouse is insured, add the calculations
                if(isSpouseInsured) {
                    int spouseAge = getAge(spouseDOB);
                    if(spouseAge<=30) {
                        applicantPremium += 31000;
                    } else if(spouseAge <= 40) {
                        applicantPremium += 32000;
                    } else if(spouseAge <= 50) {
                        applicantPremium += 38095;
                    } else if(spouseAge <= 59) {
                        applicantPremium += 48240;
                    } else {
                        applicantPremium += 53064;
                    }

                    if(includesDental) {
                        applicantPremium += 15200;
                    }

                    if(includesOptical) {
                        applicantPremium += 15200;
                    }

                    if(includesLastExpense) {
                        applicantPremium += 1800;
                    }

                    if(includesPersonalAccident) {
                        applicantPremium += 500;
                    }
                }

                // If children are insured, multiply the rate per child by the number of children
                // Then add it to the premium
                if(isFamilyInsured) {
                    applicantPremium += numberOfChildren*20000;

                    if(includesDental) {
                        applicantPremium += numberOfChildren*15200;
                    }

                    if(includesOptical) {
                        applicantPremium += numberOfChildren*15200;
                    }

                    if(includesLastExpense) {
                        applicantPremium += numberOfChildren*1800;
                    }
                }

                if(includesMaternity) {
                    applicantPremium += 35500;
                }
        }

        return applicantPremium;
    }

    private int getAge(String dateOfBirth) {
        int age;
        int yearOfBirth = Integer.parseInt(dateOfBirth.substring(0, dateOfBirth.indexOf('/')));
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        age = currentYear - yearOfBirth;
        return age;
    }

    @Override
    public void setExtras(Boolean maternity, Boolean dental, Boolean optical, Boolean personalAccident, Boolean outpatient) {
        this.includesMaternity = maternity;
        this.includesDental = dental;
        this.includesOptical = optical;
        this.includesPersonalAccident = personalAccident;
        this.includesOutpatient = outpatient;
    }
}
