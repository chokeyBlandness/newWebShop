package com.cn.model;

import java.util.ArrayList;

public class Trolley {
   private ArrayList<PurchasingMerchandise> merchandises;
   private double summaryCost;
    public Trolley(){
        merchandises=new ArrayList<PurchasingMerchandise>();
    }
    public void addPurchaseMechandise(PurchasingMerchandise newPurchasingMerchandise){
        merchandises.add(newPurchasingMerchandise);
    }
    public void calculateSummaryCost(){
        double summary=0;
        for (PurchasingMerchandise merchandise:merchandises){
            summary+=merchandise.getMerchandiesNumber()*merchandise.getMerchandise().getPrice();
        }
        this.summaryCost=summary;
    }
    public boolean changePurchasingMerchandiseNumber(String merchandiseId,int num){
        for (PurchasingMerchandise merchandise:this.merchandises){
            if (merchandise.getMerchandise().isEqualById(merchandiseId)){
                merchandise.addPurchasingNumber(num);
                return true;
            }
        }
        return false;
    }
    public ArrayList<PurchasingMerchandise> getMerchandises() {
        return merchandises;
    }

    public double getSummaryCost() {
        return summaryCost;
    }
}
