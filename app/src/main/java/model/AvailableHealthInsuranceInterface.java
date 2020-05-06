package model;

import android.widget.ImageView;

public interface AvailableHealthInsuranceInterface {
    int getLogoId();
    int getPrice();
    String getInsuranceProviderName();
    void setExtras(
            Boolean maternity,
            Boolean dental,
            Boolean optical,
            Boolean personalAccident,
            Boolean outpatient
    );
}
