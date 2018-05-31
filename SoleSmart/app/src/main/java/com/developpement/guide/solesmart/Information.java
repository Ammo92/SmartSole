package com.developpement.guide.solesmart;

/**
 * Created by KGULJIN on 31/05/2018.
 */

public class Information {
    private int id;
    private int calorie;
    private int pas;


    public Information(){}

    public Information(int calorie, int pas) {
        this.calorie = calorie;
        this.pas = pas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public int getPas() {
        return pas;
    }

    public void setPas(int pas) {
        this.pas = pas;
    }
}
