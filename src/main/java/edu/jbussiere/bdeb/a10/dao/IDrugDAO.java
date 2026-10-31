package edu.jbussiere.bdeb.a10.dao;

import edu.jbussiere.bdeb.a10.model.Drug;

import java.util.List;

public interface IDrugDAO {

    Drug findDrugByName(String name);
    Drug findById(int id);
    List<Drug> findAll();
    List<Drug> findByPrescriptionId(int prescriptionId);
}
