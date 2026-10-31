package edu.jbussiere.bdeb.a10.persistence.mock;

import edu.jbussiere.bdeb.a10.model.AdverseDrugReaction;
import edu.jbussiere.bdeb.a10.model.Prescription;
import edu.jbussiere.bdeb.a10.dao.IADR_DAO;

import java.util.ArrayList;
import java.util.List;

public class ADR_DAO_MockDB implements IADR_DAO {
    private final MockDB mockDB;

    public ADR_DAO_MockDB(MockDB mockDB) {
        this.mockDB = mockDB;
    }


    @Override
    public AdverseDrugReaction findADRById(int id) {
        for(AdverseDrugReaction a : mockDB.getAdrs() ){
            if(a.getId() == id){
                return a;
            }
        }
        return null;
    }

    @Override
    public List<AdverseDrugReaction> findAll() {
        return new ArrayList<>(mockDB.getAdrs());
    }

    @Override
    public List<AdverseDrugReaction> findByPrescriptionId(int prescriptionId) {
        for (Prescription pr : mockDB.getPrescriptions()) {
            if(pr.getId() == prescriptionId){
                return pr.getEffects();
            }
        }
        return null;
    }
}

