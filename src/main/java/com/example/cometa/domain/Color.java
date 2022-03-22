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
    private float value;

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

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
