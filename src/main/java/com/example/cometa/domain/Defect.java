package com.example.cometa.domain;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Set;

@Entity
public class Defect {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne(mappedBy = "defect", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private DefectCorrection defectCorrection;

    @ElementCollection
    @CollectionTable(name="defect_img", joinColumns=@JoinColumn(name = "Defect_id"))
    @Column(name="image_address")
    private Set<String> defectImages;

    public Defect(){

    }
    public Defect(String description, Product product, Set<String> defectImages){
        this.product = product;
        this.description = description;
        this.defectImages = defectImages;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public DefectCorrection getDefectCorrection() {
        return defectCorrection;
    }

    public void setDefectCorrection(DefectCorrection defectCorrection) {
        this.defectCorrection = defectCorrection;
    }

    public Set<String> getDefectImages() {
        return defectImages;
    }

    public void setDefectImages(Set<String> defectImages) {
        this.defectImages = defectImages;
    }

    enum Status{
        CORRECTED, NOT_CORRECTED;
    }
}
