package edu.jbussiere.bdeb.a10.persistence.mock;

import edu.jbussiere.bdeb.a10.model.*;
import edu.jbussiere.bdeb.a10.dao.IUserDAO;

import java.util.ArrayList;
import java.util.List;

public class UserDAO_MockDB implements IUserDAO {

    private final MockDB mockDB;

    public UserDAO_MockDB(MockDB mockDB) {
        this.mockDB=mockDB;
    }



    @Override
    public User findPatientById(int id) {

        for (User u : mockDB.getUsers()) {
            if (u.getUserId() == id && u instanceof Patient) {
                return u;
            }
        }
        return null;
    }

    @Override
    public User findDoctorById(int id) {
        for (User u : mockDB.getUsers()) {
            if (u.getUserId() == id && u instanceof Doctor) {
                return u;
            }
        }
        return null;
    }

    @Override
    public List<Prescription> findPrescriptionByPatientId(int id) {
        for (User u : mockDB.getUsers()) {
            if (u.getUserId() == id && u instanceof Patient) {
                Patient p = (Patient)u;
                return new ArrayList<>(p.getPrescriptions());
            }
        }
        return new ArrayList<>();
    }


}
