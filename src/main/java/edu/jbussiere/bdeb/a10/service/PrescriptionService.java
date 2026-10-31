package edu.jbussiere.bdeb.a10.service;

import edu.jbussiere.bdeb.a10.model.*;
import edu.jbussiere.bdeb.a10.dao.IADR_DAO;
import edu.jbussiere.bdeb.a10.dao.IDrugDAO;

import java.util.List;

public class PrescriptionService {

    //attributs
    private final IDrugDAO drugDAO;
    private final IADR_DAO adrDAO;
    private final PatientService patientService;

    public PrescriptionService(IDrugDAO drugDAO, IADR_DAO adrDAO, PatientService patientService) {
        this.drugDAO = drugDAO;
        this.adrDAO = adrDAO;
        this.patientService = patientService;
    }

    //methodes
    public DoseResult calculateAdjustedDose(int patientId, String prescribed, String concomitant) {

        Patient patient = patientService.findPatientData(patientId);

        Drug drug = drugDAO.findDrugByName(prescribed.toLowerCase());


        double std_dose_by_weight = drug.getStandard_dose_per_kg() * patient.getWeight();
        double factor = getInteractionFactor(drug, concomitant);
        double adjusted = std_dose_by_weight * factor;

        return new DoseResult(adjusted, factor);
    }

    public Drug retrieveDrug(String drugName) {
        return drugDAO.findDrugByName(drugName.toLowerCase().trim());
    }

    public double getInteractionFactor(Drug prescribed, String concomitant) {
        for (DrugInteraction di : prescribed.getInteractions()) {
            if (di.matches(concomitant)) {
                return di.getInteraction_factor();
            }
        }
        return 1.0; //baseline: no interaction
    }

    public Patient loadPatientWithPrescription(int patientId) {
        Patient patient = patientService.findPatientData(patientId);
        List<Prescription> prescriptions =
                patientService.retrievePrescriptionsByPatientId(patientId);
        for (Prescription p : prescriptions) {
            p.setDrugs(drugDAO.findByPrescriptionId(p.getId()));
            p.setEffects(adrDAO.findByPrescriptionId(p.getId()));
        }
        patient.setPrescriptions(prescriptions);
        return patient;
    }


    public Prescription getPrescriptionforDrug(int patientId, String drugName) {
        Patient patient = loadPatientWithPrescription(patientId);
        String normalized = drugName.trim().toLowerCase();
        for (Prescription rx : patient.getPrescriptions()) {
            for (Drug d : rx.getDrugs()) {
                if (d.getName().toLowerCase().equalsIgnoreCase(normalized)) {
                    return rx;
                }
            }
        }
        return null;
    }
}
