package edu.jbussiere.bdeb.a10.persistence.jpa;

import edu.jbussiere.bdeb.a10.model.Doctor;
import edu.jbussiere.bdeb.a10.model.Patient;
import edu.jbussiere.bdeb.a10.model.Prescription;
import edu.jbussiere.bdeb.a10.model.User;
import edu.jbussiere.bdeb.a10.dao.IUserDAO;
import edu.jbussiere.bdeb.a10.persistence.sql.QueryBox;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UserDAO_JPA implements IUserDAO {

    private final EntityManager em;

    public UserDAO_JPA(EntityManager em) {
        this.em=em;
    }

    @Override
    public User findPatientById(int id) {
        return em.find(Patient.class, id);
    }

    @Override
    public User findDoctorById(int id) {
        return em.find(Doctor.class, id);
    }

    @Override
    public List<Prescription> findPrescriptionByPatientId(int id) {
        TypedQuery<Prescription> query = em.createQuery(
                QueryBox.JPA_FIND_PRESCRIPTION_BY_PATIENT_ID,
                Prescription.class
        );
        query.setParameter("pid", id);
        return query.getResultList();
    }
}
