package edu.jbussiere.bdeb.a10.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("doctor")
public class Doctor extends User {

    public Doctor() {}

    //attributs
    private String skill;

    @OneToMany(mappedBy = "doctor")
    private List<Prescription> prescriptions = new ArrayList<Prescription>();

    //constructeur
    public Doctor (int id, String name, String tel, String skill) {
        super(id, name, tel);
        this.skill=skill;
    }

    //getters et setters
    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

}
