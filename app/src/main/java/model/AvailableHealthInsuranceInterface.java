package model;

import android.widget.ImageView;

public interface AvailableHealthInsuranceInterface {
    int getPrice();
    String getInsuranceProviderName();
    void setExtras(
            Boolean maternity,
            Boolean dental,
            Boolean optical,
            Boolean personalAccident,
            Boolean outpatient
    );
    void setLogo(ImageView image);
}
