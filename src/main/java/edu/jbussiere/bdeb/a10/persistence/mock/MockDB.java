package edu.jbussiere.bdeb.a10.persistence.mock;

import java.util.ArrayList;
import java.util.List;

import edu.jbussiere.bdeb.a10.model.*;

public final class MockDB {

    private static MockDB instance;
    List<User> users;
    List<Drug> drugs;
    List<AdverseDrugReaction> adrs;
    List<Prescription> prescriptions;


    private MockDB() {
        this.users = new ArrayList<>();
        this.drugs = new ArrayList<>();
        this.adrs = new ArrayList<>();
        this.prescriptions = new ArrayList<>();

        populateMockData();
    }

    public static MockDB getInstance() {
        if (instance == null) {
            instance = new MockDB();
        }
        return instance;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Drug> getDrugs() {
        return drugs;
    }

    public List<AdverseDrugReaction> getAdrs() {
        return adrs;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    private void populateMockData() {
        //doctors
        Doctor house = new Doctor(1,
                "Dr. Gregory House",
                "514-555-0199",
                "Diagnostics & Infectious Diseases"
        );

        Doctor strange = new Doctor(2,
                "Dr. Stephen Strange",
                "450-555-0123",
                "Neurosurgery"
        );

        users.add(house);
        users.add(strange);

        //patients
        Patient p1 = new Patient(3,
                "John Doe",
                "514-555-4321",
                45,
                82,//kg
                "Penicillin, Sulfa"
        );

        Patient p2 = new Patient(4,
                "Jane Smith",
                "450-555-8765",
                72,
                55,
                "None"
        );

        Patient p3 = new Patient(5,
                "Mary Jones",
                "514-555-9988",
                8,
                25,
                "Aspirin"
        );

        users.add(p1);
        users.add(p2);
        users.add(p3);


        //drugs
        Drug acetaminophen = new Drug(1,
                "Acetaminophen",
                10.0
        );

        Drug ibuprofen = new Drug(2,
                "Ibuprofen",
                4.0
        );

        Drug warfarin = new Drug(3,
                "Warfarin",
                2.0  //eclairciceur de sang, dosage sensible
        );

        Drug aspirin = new Drug(4,
                "Aspirin",
                3.0
        );

        Drug amoxicillin = new Drug(5,
                "Amoxicillin",
                4.0
        );

        aspirin.getInteractions().add(new DrugInteraction("Ibuprofen", 0.7));
        warfarin.getInteractions().add(new DrugInteraction("Amoxicillin", 1.2));
        ibuprofen.getInteractions().add(new DrugInteraction("Aspirin", 0.7));
        amoxicillin.getInteractions().add(new DrugInteraction("Warfarin", 1.2));


        //Adverse drug reaction
        AdverseDrugReaction liverToxicity = new AdverseDrugReaction(
                1, "Severe liver damage or acute hepatic failure upon overdose.",
                "Hepatotoxicity"
        );

        AdverseDrugReaction bleedingRisk = new AdverseDrugReaction(
                2,
                "Increased risk of internal, gastrointestinal, or cranial bleeding.",
                "Hemorrhage"
        );

        adrs.add(liverToxicity);
        adrs.add(bleedingRisk);

        //prescriptions

        //par docteur house
        Prescription rx1 = new Prescription(1, 2.0, house, p1);
        rx1.getDrugs().add(acetaminophen);
        rx1.getEffects().add(liverToxicity);
        p1.getPrescriptions().add(rx1);

        //par docteur strange
        Prescription rx2 = new Prescription(2, 1.0, strange, p2);
        rx2.getDrugs().add(warfarin);
        rx2.getDrugs().add(aspirin);
        rx2.getEffects().add(bleedingRisk);
        p2.getPrescriptions().add(rx2);

        prescriptions.add(rx1);
        prescriptions.add(rx2);


        //ajouter drug a la collection principal
        drugs.add(acetaminophen);
        drugs.add(warfarin);
        drugs.add(aspirin);
        drugs.add(ibuprofen);
        drugs.add(amoxicillin);
    }
}
