package com.katomaran.robotics.rengas;

/**
 * Created by Sandeep on 25-08-2018.
 */

public class CartProduct {
    private String Name;
    private String Type;
    private String Siz;
    private String Qut;
    private String Cost;
    private String count;
    private int Pro_Img;
    private boolean isVisible;

    public CartProduct(String name, String type, String siz, String qut, String cost, String count, int pro_Img) {
        Name = name;
        Type = type;
        Siz = siz;
        Qut = qut;
        Cost = cost;
        this.count = count;
        Pro_Img = pro_Img;
    }

    public CartProduct(String name, String type, String siz, String qut, String cost, String count, int pro_Img, boolean isVisible) {
        Name = name;
        Type = type;
        Siz = siz;
        Qut = qut;
        Cost = cost;
        this.count = count;
        Pro_Img = pro_Img;
        this.isVisible = isVisible;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public String getName() {
        return Name;
    }

    public String getType() {
        return Type;
    }

    public String getSiz() {
        return Siz;
    }

    public String getQut() {
        return Qut;
    }

    public String getCost() {
        return Cost;
    }

    public String getCount() {
        return count;
    }

    public int getPro_Img() {
        return Pro_Img;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setSiz(String siz) {
        Siz = siz;
    }

    public void setQut(String qut) {
        Qut = qut;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setPro_Img(int pro_Img) {
        Pro_Img = pro_Img;
    }
}
