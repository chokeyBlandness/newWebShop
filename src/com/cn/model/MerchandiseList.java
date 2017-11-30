package com.cn.model;

import java.util.ArrayList;

public class MerchandiseList {
    ArrayList<Merchandise> merchandiseList;
    public MerchandiseList(){
        merchandiseList=new ArrayList<Merchandise>();
    }
    public void addMerchandise(Merchandise newMerchandise){
        merchandiseList.add(newMerchandise);
    }
    public ArrayList<Merchandise> getMerchandiseList() {
        return merchandiseList;
    }
}
