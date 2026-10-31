package edu.jbussiere.bdeb.a10.control;

import edu.jbussiere.bdeb.a10.model.*;
import edu.jbussiere.bdeb.a10.service.PatientService;
import edu.jbussiere.bdeb.a10.service.PrescriptionService;
import edu.jbussiere.bdeb.a10.persistence.factory.DAOFactory;

public class DoctorController {
    private final PatientService patientService;
    private final PrescriptionService prescriptionService;


    public DoctorController() {
        this.patientService = new PatientService(DAOFactory.createUserDAO());
        this.prescriptionService = new PrescriptionService(
                DAOFactory.createDrugDAO(),
                DAOFactory.createADR_DAO(),
                this.patientService
        );
    }

    public Patient displayPatientData(int id) {
        return prescriptionService.loadPatientWithPrescription(id);
    }

    public DoseResult getCalculateDose(int id, String drugName, String concomitant) {
        return prescriptionService.calculateAdjustedDose(id, drugName, concomitant);
    }

    public double getStandardDose(String drugName) {
        return prescriptionService.retrieveDrug(drugName).getStandard_dose_per_kg();
    }

    public Drug getDrugByName(String drugName) {
        return prescriptionService.retrieveDrug(drugName.toLowerCase());
    }

    public Prescription getPrescriptionForDrug(int patientId, String prescribed) {
        return prescriptionService.getPrescriptionforDrug(patientId, prescribed);
    }

}
