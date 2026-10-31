package edu.jbussiere.bdeb.a10.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="prescriptions")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //attributs
    private int prescription_id;

    private double PDD;  //Prescribed Daily Dose

    //simple association
    //1 to 1 mappings vers le doctor qui écrit la prescription
    @ManyToOne
    @JoinColumn(name="doctor_id", referencedColumnName = "user_id")
    private User doctor;

    //composition
    //1 to 1 mapping vers le patient qui reçoit la prescription
    @ManyToOne
    @JoinColumn(name="patient_id", referencedColumnName = "user_id")
    private User patient;

    //many to many aggregation
    //reference des reactions/effects sur le patient pour cette prescription
    @ManyToMany
    @JoinTable(
            name = "adrs_prescriptions",
            joinColumns = @JoinColumn(name = "prescription_id"),
            inverseJoinColumns = @JoinColumn(name="adr_id")
    )
    private List<AdverseDrugReaction> effects;


    //references les medicaments que le patient a pour cette prescription
    @ManyToMany
    @JoinTable(
            name = "prescriptions_drugs",
            joinColumns = @JoinColumn(name = "prescription_id"),
            inverseJoinColumns = @JoinColumn(name="drug_id")
    )
    private List<Drug> drugs;

    // constructeur
    public Prescription() {
        this.effects = new ArrayList<>();
        this.drugs = new ArrayList<>();
    }

    public Prescription(int id, double PDD, Doctor doctor, Patient patient) {
        this();
        this.prescription_id = id;
        this.PDD=PDD;
        this.doctor=doctor;
        this.patient=patient;

    }

    //getters et setters
    public double getPDD() {
        return PDD;
    }

    public void setPDD(double pDD) {
        PDD = pDD;
    }

    public List<AdverseDrugReaction> getEffects() {
        return effects;
    }

    public void setEffects(List<AdverseDrugReaction> effects) {
        this.effects = effects;
    }

    public List<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<Drug> drugs) {
        this.drugs = drugs;
    }

    public int getId() {
        return prescription_id;
    }

    public void setId(int id) {
        this.prescription_id = id;
    }

    public Doctor getDoctor() {
        return (Doctor) doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return (Patient) patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}

