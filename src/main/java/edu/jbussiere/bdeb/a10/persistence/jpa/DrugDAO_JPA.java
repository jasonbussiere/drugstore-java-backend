package edu.jbussiere.bdeb.a10.persistence.jpa;

import edu.jbussiere.bdeb.a10.model.Drug;
import edu.jbussiere.bdeb.a10.dao.IDrugDAO;
import edu.jbussiere.bdeb.a10.persistence.sql.QueryBox;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class DrugDAO_JPA implements IDrugDAO {

    private final EntityManager em;

    public DrugDAO_JPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public Drug findDrugByName(String name) {
        TypedQuery<Drug> query = em.createQuery(
                "SELECT d FROM Drug d WHERE d.name = :dname",
                Drug.class
        );
        query.setParameter("dname", name);
        return query.getSingleResult();
    }

    @Override
    public Drug findById(int id) {
        return em.find(Drug.class, id);
    }

    @Override
    public List<Drug> findAll() {
        TypedQuery<Drug> query = em.createQuery(
                QueryBox.JPA_FIND_ALL_DRUGS,
                Drug.class
        );
        return query.getResultList();
    }

    @Override
    public List<Drug> findByPrescriptionId(int prescriptionId) {
        TypedQuery<Drug> query = em.createQuery(
                QueryBox.JPA_FIND_DRUGS_BY_PRESCRIPTION_ID,
                Drug.class
        );
        query.setParameter("rxid", prescriptionId);
        return query.getResultList();
    }
}
