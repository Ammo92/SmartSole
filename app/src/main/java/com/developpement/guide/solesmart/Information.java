package com.developpement.guide.solesmart;

import java.sql.Date;

/**
 * Created by KGULJIN on 31/05/2018.
 */

public class Information {
    private int id;
    private double calorie;
    private float pas;
    private String date;


    public Information(){}

    public Information(double calorie, float pas,  String date) {
        this.calorie = calorie;
        this.pas = pas;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCalorie() {
        return calorie;
    }

    public void setCalorie(double calorie) {
        this.calorie = calorie;
    }

    public float getPas() {
        return pas;
    }

    public void setPas(float pas) {
        this.pas = pas;
    }

    public String getDate() { return date;}

    public void setDate(String date) { this.date = date; }
}
