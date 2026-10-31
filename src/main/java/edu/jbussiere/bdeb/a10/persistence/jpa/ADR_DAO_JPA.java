package edu.jbussiere.bdeb.a10.persistence.jpa;

import edu.jbussiere.bdeb.a10.model.AdverseDrugReaction;
import edu.jbussiere.bdeb.a10.dao.IADR_DAO;
import edu.jbussiere.bdeb.a10.persistence.sql.QueryBox;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ADR_DAO_JPA implements IADR_DAO {

    private final EntityManager em;

    public ADR_DAO_JPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public AdverseDrugReaction findADRById(int id) {
        return em.find(AdverseDrugReaction.class, id);
    }

    @Override
    public List<AdverseDrugReaction> findAll() {
        TypedQuery<AdverseDrugReaction> query = em.createQuery(
                QueryBox.JPA_FIND_ALL_ADRS,
                AdverseDrugReaction.class
        );
        return query.getResultList();
    }

    @Override
    public List<AdverseDrugReaction> findByPrescriptionId(int prescriptionId) {
        TypedQuery<AdverseDrugReaction> query = em.createQuery(
                QueryBox.JPA_FIND_ADRS_BY_PRESCRIPTION_ID,
                AdverseDrugReaction.class
        );
        query.setParameter("rxid", prescriptionId);
        return query.getResultList();
    }
}
