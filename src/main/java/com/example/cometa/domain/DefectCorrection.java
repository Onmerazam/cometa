package com.example.cometa.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class DefectCorrection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String message;
    private String culprit;
    private CorrectStatus status;
    @OneToOne
    @JoinColumn(name = "defect_id")
    private Defect defect;

    @ElementCollection
    @CollectionTable(name="correct_img", joinColumns=@JoinColumn(name = "DefectCorrection_id"))
    @Column(name="image_address")
    private Set<String> imageCorrections;

    public DefectCorrection(){

    }
    public DefectCorrection (String message, String culprit, Defect defect, Set<String> imageCorrections){
        this.defect = defect;
        this.message = message;
        this.imageCorrections = imageCorrections;
        this.culprit = culprit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Defect getDefect() {
        return defect;
    }

    public void setDefect(Defect defect) {
        this.defect = defect;
    }

    public String getCulprit() {
        return culprit;
    }

    public void setCulprit(String culprit) {
        this.culprit = culprit;
    }



    public Set<String> getImageCorrections() {
        return imageCorrections;
    }

    public void setImageCorrections(Set<String> imageCorrections) {
        this.imageCorrections = imageCorrections;
    }

    public CorrectStatus getStatus() {
        return status;
    }

    public void setStatus(CorrectStatus status) {
        this.status = status;
    }
}
