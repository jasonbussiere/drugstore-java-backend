package edu.jbussiere.bdeb.a10.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("patient")
public class Patient extends User {

    //attributs
    private int weight;
    private String allergies;
    private int age;

    //composition
    @OneToMany(mappedBy = "patient")
    private List<Prescription> prescriptions;


    //constructeur
    public Patient() {
        super();
    }

    public Patient(int id, String name, String tel, int age, int weight, String allergies) {
        super(id, name, tel);
        this.age=age;
        this.weight=weight;
        this.allergies=allergies;
        this.prescriptions = new ArrayList<>();

    }

    //getters et setters
    public double getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }
}
