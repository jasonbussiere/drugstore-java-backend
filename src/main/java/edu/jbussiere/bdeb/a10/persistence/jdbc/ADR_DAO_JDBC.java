package edu.jbussiere.bdeb.a10.persistence.jdbc;

import edu.jbussiere.bdeb.a10.model.AdverseDrugReaction;
import edu.jbussiere.bdeb.a10.dao.IADR_DAO;
import edu.jbussiere.bdeb.a10.persistence.sql.QueryBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ADR_DAO_JDBC  implements IADR_DAO {
    private final ConnectionProvider db;

    public ADR_DAO_JDBC(ConnectionProvider db) {
        this.db = db;
    }


    @Override
    public AdverseDrugReaction findADRById(int id) {
        try (Connection conn=db.getConnection();
             PreparedStatement ps = conn.prepareStatement(QueryBox.FIND_ADR_BY_ID)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new AdverseDrugReaction(
                            rs.getInt("adr_id"),
                            rs.getString("category"),
                            rs.getString("description")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur avec findADRById", e);
        }
        return null;
    }

    @Override
    public List<AdverseDrugReaction> findAll() {
        List<AdverseDrugReaction> adrs = new ArrayList<>();
        try (Connection conn = db.getConnection();
        PreparedStatement ps = conn.prepareStatement(QueryBox.FIND_ALL_ADR)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    adrs.add(
                            new AdverseDrugReaction(
                                    rs.getInt("adr_id"),
                                    rs.getString("description"),
                                    rs.getString("category")
                            )
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur avec findAll", e);
        }
        return adrs;
    }

    @Override
    public List<AdverseDrugReaction> findByPrescriptionId(int prescriptionId) {
        List<AdverseDrugReaction> adrs = new ArrayList<>();
        try(Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(QueryBox.FIND_ADRS_BY_PRESCRIPTION_ID)) {

            ps.setInt(1, prescriptionId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    adrs.add(
                            new AdverseDrugReaction(
                                    rs.getInt("adr_id"),
                                    rs.getString("description"),
                                    rs.getString("category")
                            )
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur avec findByPrescriptionId", e);
        }
        return adrs;
    }
}
