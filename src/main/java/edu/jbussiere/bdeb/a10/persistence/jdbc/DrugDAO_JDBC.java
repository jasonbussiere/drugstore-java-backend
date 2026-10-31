package edu.jbussiere.bdeb.a10.persistence.jdbc;

import edu.jbussiere.bdeb.a10.model.Drug;
import edu.jbussiere.bdeb.a10.dao.IDrugDAO;
import edu.jbussiere.bdeb.a10.persistence.sql.QueryBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrugDAO_JDBC  implements IDrugDAO {
    private final ConnectionProvider db;

    public DrugDAO_JDBC(ConnectionProvider db) {
        this.db = db;
    }

    @Override
    public Drug findDrugByName(String name) {
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(QueryBox.FIND_BY_NAME)) {

            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Drug(
                            rs.getInt("drug_id"),
                            rs.getString("name"),
                            rs.getDouble("standard_dose_per_kg")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur avec findDrugByName", e);
        }
        return null;
    }

    @Override
    public Drug findById(int id) {
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(QueryBox.FIND_BY_NAME)) {

            ps.setString(1, "id");

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Drug(
                            rs.getInt("drug_id"),
                            rs.getString("name"),
                            rs.getDouble("standard_dose_per_kg")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur avec findDrugByName", e);
        }
        return null;
    }

    @Override
    public List<Drug> findAll() {
        List<Drug> drugs = new ArrayList<>();
        try(Connection conn = db.getConnection();
        PreparedStatement ps = conn.prepareStatement(QueryBox.FIND_ALL)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    drugs.add(
                            new Drug(
                                    rs.getInt("drug_id"),
                                    rs.getString("name"),
                                    rs.getDouble("standard_dose_per_kg")
                            )
                    );
                }
            }
        }catch(SQLException e){
            throw new RuntimeException("Erreur avec findAll", e);
        }
        return drugs;
    }

    @Override
    public List<Drug> findByPrescriptionId(int prescriptionId) {
        List<Drug> drugs = new ArrayList<>();
        try(Connection conn = db.getConnection();
        PreparedStatement ps = conn.prepareStatement(QueryBox.FIND_DRUGS_BY_PRESCRIPTION_ID)) {

            ps.setInt(1, prescriptionId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    drugs.add(
                            new Drug(
                                    rs.getInt("drug_id"),
                                    rs.getString("name"),
                                    rs.getDouble("standard_dose_per_kg")
                            )
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur avec findByPrescriptionId", e);
        }
        return drugs;
    }
}


