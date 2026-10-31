package edu.jbussiere.bdeb.a10.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class DrugInteraction {


    //attributs
    private String interactingDrug;
    private double interaction_factor;

    //constructeur
    public DrugInteraction() {}

    public DrugInteraction(String interactingDrug,double interaction_factor) {
        this.interactingDrug = interactingDrug;
        this.interaction_factor=interaction_factor;
    }

    public boolean matches(String drugName) {
        return interactingDrug.equalsIgnoreCase(drugName);
    }

    //getters et setters
    public double getInteraction_factor() {
        return interaction_factor;
    }

    public void setInteraction_factor(double interaction_factor) {
        this.interaction_factor = interaction_factor;
    }

    public String getInteractingDrug() {
        return interactingDrug;
    }

    public void setInteractingDrug(String interactingDrug) {
        this.interactingDrug = interactingDrug;
    }
    }
