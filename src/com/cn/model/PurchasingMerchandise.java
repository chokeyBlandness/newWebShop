package com.cn.model;

public class PurchasingMerchandise {
    private Merchandise merchandise;
    private int merchandiesNumber;
    public PurchasingMerchandise(){}
    public void addPurchasingNumber(int num){
        this.merchandiesNumber+=num;
    }
    public void setMerchandise(Merchandise merchandise) {
        this.merchandise = merchandise;
    }
    public void setMerchandiesNumber(int merchandiesNumber) {
        this.merchandiesNumber = merchandiesNumber;
    }
    public Merchandise getMerchandise() {
        return merchandise;
    }
    public int getMerchandiesNumber() {
        return merchandiesNumber;
    }
}
