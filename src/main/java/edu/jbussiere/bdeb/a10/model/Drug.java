package edu.jbussiere.bdeb.a10.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="drugs")
public class Drug {

    @Id
    @Column(name = "drug_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //attributs
    private int drugId;
    private String name;
    private double standard_dose_per_kg;

    //association recursive: drug avoir plusieurs interactions avec d'autres drugs
    @ElementCollection
    @CollectionTable(
            name="drug_interactions",
            joinColumns = @JoinColumn(name="drug_id")
    )
    private List<DrugInteraction> interactions;

    //constructeur
    public Drug() {
        this.interactions = new ArrayList<>();
    }

    public Drug (int id, String name, Double dose) {
        this();
        this.drugId = id;
        this.name = name;
        this.standard_dose_per_kg = dose;
    }

    //getters et setters

    public int getDrugId() {
        return drugId;
    }

    public void setDrugId(int drugId) {
        this.drugId = drugId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStandard_dose_per_kg() {
        return standard_dose_per_kg;
    }

    public void setStandard_dose_per_kg(double standard_dose) {
        this.standard_dose_per_kg = standard_dose;
    }

    public List<DrugInteraction>  getInteractions() {
        return interactions;
    }

    public void setInteractions(List<DrugInteraction> interactions) {
        this.interactions = interactions;
    }


}
