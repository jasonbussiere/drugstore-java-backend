package edu.jbussiere.bdeb.a10.model;

import jakarta.persistence.*;

@Entity
@Table(name="adverse_drug_reactions")
public class AdverseDrugReaction {

    @Id
    @Column(name = "adr_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //attributs
    private int id;
    private String description;
    private String category;

    //constructeur
    public AdverseDrugReaction() {}

    public AdverseDrugReaction(int id, String desc, String cat) {
        this.description=desc;
        this.category=cat;
        this.id = id;
    }

    //getters et setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
