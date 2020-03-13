package model;

public class AvailableMotorInsuranceModel {
    private Double price;
    private Double windScreenPrice;
    private Double radioPrice;
    private Double excessProtectorPrice;
    private Double PVTPrice;
    private Double lossOfUseValue;
    private Double carValue;
    private String insuranceCompany;

    public AvailableMotorInsuranceModel(String insuranceCompany, Double carValue) {
        this.insuranceCompany = insuranceCompany;
        this.carValue = carValue;
        this.price = 0.0;
        this.radioPrice = 0.0;
        this.excessProtectorPrice = 0.0;
        this.PVTPrice = 0.0;
        this.windScreenPrice = 0.0;
        this.lossOfUseValue = 0.0;
    }

    public Double getPrice() {
        return null;
    }
}
