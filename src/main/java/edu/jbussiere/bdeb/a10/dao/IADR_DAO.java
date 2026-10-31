package edu.jbussiere.bdeb.a10.dao;

import edu.jbussiere.bdeb.a10.model.AdverseDrugReaction;

import java.util.List;

public interface IADR_DAO {

    AdverseDrugReaction findADRById(int id);
    List<AdverseDrugReaction> findAll();
    List<AdverseDrugReaction> findByPrescriptionId(int prescriptionId);
}
