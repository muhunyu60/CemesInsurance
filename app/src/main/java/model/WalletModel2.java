package model;


/**
 * Created by wolfsoft4 on 1/10/18.
 */

public class WalletModel2 {

    Integer img1;
    String txtsavingacct,txtdiscount;

    public Integer getImg1() {
        return img1;
    }

    public void setImg1(Integer img1) {
        this.img1 = img1;
    }

    public String getTxtsavingacct() {
        return txtsavingacct;
    }

    public void setTxtsavingacct(String txtsavingacct) {
        this.txtsavingacct = txtsavingacct;
    }

    public String getTxtdiscount() {
        return txtdiscount;
    }

    public void setTxtdiscount(String txtdiscount) {
        this.txtdiscount = txtdiscount;
    }

    public WalletModel2(Integer img1, String txtsavingacct, String txtdiscount) {
        this.img1 = img1;
        this.txtsavingacct = txtsavingacct;
        this.txtdiscount = txtdiscount;
    }
}
