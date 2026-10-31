package edu.jbussiere.bdeb.a10.persistence.mock;

import edu.jbussiere.bdeb.a10.model.Drug;
import edu.jbussiere.bdeb.a10.model.Prescription;
import edu.jbussiere.bdeb.a10.dao.IDrugDAO;

import java.util.ArrayList;
import java.util.List;

public class DrugDAO_MockDB implements IDrugDAO {

    private final MockDB mockDB;

    public DrugDAO_MockDB(MockDB mockDB) {
        this.mockDB = mockDB;

    }

    @Override
    public Drug findDrugByName(String name) {
        for (Drug d : mockDB.getDrugs()) {
            if (d.getName().trim().equalsIgnoreCase(name.trim())) {
                return d;
            }
        }
        return null;
    }

    @Override
    public Drug findById(int id) {
        for (Drug d : mockDB.getDrugs()) {
            if(d.getDrugId() == id){
                return d;
            }
        }
        return null;
    }

    @Override
    public List<Drug> findAll() {
        return new ArrayList<>(mockDB.getDrugs());
    }

    @Override
    public List<Drug> findByPrescriptionId(int prescriptionId) {
        for (Prescription pr : mockDB.getPrescriptions()) {
            if(pr.getId() == prescriptionId){
                return pr.getDrugs();
            }
        }
        return null;
    }

}
