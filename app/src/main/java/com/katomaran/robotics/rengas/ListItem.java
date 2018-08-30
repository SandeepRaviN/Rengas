package com.katomaran.robotics.rengas;

/**
 * Created by Sandeep on 20-08-2018.
 */

public class ListItem {
    private String Name;
    private String Type;
    private String Siz;
    private String Qut;
    private String Cost;
    private int Pro_Img;
    private boolean isVisible;


    public ListItem(String name, String type, String siz, String qut, String cost, int pro_Img) {
        Name = name;
        Type = type;
        Siz = siz;
        Qut = qut;
        Cost = cost;
        Pro_Img = pro_Img;


    }

    public ListItem(String name, String type, String siz, String qut, String cost, int pro_Img, boolean isVisible) {
        Name = name;
        Type = type;
        Siz = siz;
        Qut = qut;
        Cost = cost;
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

    public int getPro_Img() {
        return Pro_Img;
    }
}
