package com.example.cometa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Color {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;


    private Integer numberColor;
    private String coatingType;
    private String manufact;
    private double value;

    public Color(){

    }
    public Color(Integer numberColor, String coatingType, String manufact, double value) {
        this.numberColor = numberColor;
        this.coatingType = coatingType;
        this.manufact = manufact;
        this.value = value;
    }

    public Integer getNumberColor() {
        return numberColor;
    }

    public void setNumberColor(Integer numberColor) {
        this.numberColor = numberColor;
    }

    public String getCoatingType() {
        return coatingType;
    }

    public void setCoatingType(String coatingType) {
        this.coatingType = coatingType;
    }

    public String getManufact() {
        return manufact;
    }

    public void setManufact(String manufact) {
        this.manufact = manufact;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
