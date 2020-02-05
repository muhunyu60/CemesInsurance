package model;

/**
 * Created by wolfsoft4 on 28/9/18.
 */

public class WalletModel {

    Integer oval;
    String txtshopping;

    public Integer getOval() {
        return oval;
    }

    public void setOval(Integer oval) {
        this.oval = oval;
    }

    public String getTxtshopping() {
        return txtshopping;
    }

    public void setTxtshopping(String txtshopping) {
        this.txtshopping = txtshopping;
    }

    public WalletModel(Integer oval, String txtshopping) {
        this.oval = oval;
        this.txtshopping = txtshopping;
    }
}
