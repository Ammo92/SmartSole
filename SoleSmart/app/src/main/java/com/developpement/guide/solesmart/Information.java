package com.developpement.guide.solesmart;

import java.sql.Date;

/**
 * Created by KGULJIN on 31/05/2018.
 */

public class Information {
    private int id;
    private int calorie;
    private int pas;
    private Date date;


    public Information(){}

    public Information(int calorie, int pas,  Date date) {
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

    public Date getDate() { return date;}

    public void setDate(Date date) { this.date = date; }
}
