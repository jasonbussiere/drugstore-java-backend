package edu.jbussiere.bdeb.a10.service;

import edu.jbussiere.bdeb.a10.model.Patient;
import edu.jbussiere.bdeb.a10.model.Prescription;
import edu.jbussiere.bdeb.a10.dao.IUserDAO;

import java.util.List;

public class PatientService {

    private final IUserDAO userDAO;

    public PatientService(IUserDAO dao) {
        this.userDAO = dao;
    }

    public Patient findPatientData(int id) {
        return (Patient) userDAO.findPatientById(id);
    }

    public List<Prescription> retrievePrescriptionsByPatientId(int patientId) {
        return userDAO.findPrescriptionByPatientId(patientId);
    }
}
