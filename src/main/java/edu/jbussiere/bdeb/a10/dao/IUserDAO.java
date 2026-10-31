package edu.jbussiere.bdeb.a10.dao;

import edu.jbussiere.bdeb.a10.model.Prescription;
import edu.jbussiere.bdeb.a10.model.User;

import java.util.List;

public interface IUserDAO {

    User findPatientById(int id);
    User findDoctorById(int id);
    List<Prescription> findPrescriptionByPatientId(int id);

}
