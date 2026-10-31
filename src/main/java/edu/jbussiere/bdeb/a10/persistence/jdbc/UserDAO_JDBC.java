package edu.jbussiere.bdeb.a10.persistence.jdbc;

import edu.jbussiere.bdeb.a10.model.*;
import edu.jbussiere.bdeb.a10.dao.IUserDAO;
import edu.jbussiere.bdeb.a10.persistence.sql.QueryBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO_JDBC  implements IUserDAO {
    private final ConnectionProvider db;

    public UserDAO_JDBC(ConnectionProvider db) {
        this.db = db;
    }

    @Override
    public Patient findPatientById(int id) {
        try(Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(QueryBox.FIND_PATIENT_BY_ID)) {

            ps.setInt(1, id);

            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Patient(
                            rs.getInt("user_id"),
                            rs.getString("name"),
                            rs.getString("tel"),
                            rs.getInt("age"),
                            rs.getInt("weight"),
                            rs.getString("allergies")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur avec findPatientById",e);
        }
        return null;
    }

    @Override
    public Doctor findDoctorById(int id) {
        try(Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(QueryBox.FIND_DOCTOR_BY_ID)) {

            ps.setInt(1, id);

            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Doctor(
                            rs.getInt("user_id"),
                            rs.getString("name"),
                            rs.getString("tel"),
                            rs.getString("skill")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur avec findPatientById",e);
        }
        return null;

    }

    @Override
    public List<Prescription> findPrescriptionByPatientId(int id) {
        List<Prescription> prescriptions = new ArrayList<>();

        try (Connection conn = db.getConnection();
        PreparedStatement ps = conn.prepareStatement(QueryBox.FIND_PRESCRIPTION_BY_PATIENT_ID)) {

            ps.setInt(1, id);

            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    //load doctor and patient and prescription id
                    int prescriptionId = rs.getInt("prescription_id");
                    Doctor doctor = findDoctorById(rs.getInt("doctor_id"));
                    Patient patient = findPatientById(rs.getInt("patient_id"));

                    //create presciption
                    Prescription rx= new Prescription(
                            prescriptionId,
                            rs.getDouble("pdd"),
                            doctor,
                            patient
                    );
                    prescriptions.add(rx);

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur avec findPrescriptionByPatientId",e);
        }
        return prescriptions;
    }
}
