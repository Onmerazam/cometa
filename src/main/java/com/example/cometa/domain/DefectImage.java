package com.example.cometa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DefectImage {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer defectid;
    private String address;

    public DefectImage(){

    }
    public DefectImage(Integer defect_id, String address){
        this.defectid = defect_id;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getDefect_id() {
        return defectid;
    }

    public void setDefect_id(Integer defect_id) {
        this.defectid = defect_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
