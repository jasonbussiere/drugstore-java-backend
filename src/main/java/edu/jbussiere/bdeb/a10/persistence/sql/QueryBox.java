package edu.jbussiere.bdeb.a10.persistence.sql;

public final class QueryBox {

    private QueryBox() {}

    //JDBC queries
    //drug
    public static final String FIND_DRUG_BY_ID =
            "SELECT * FROM drugs WHERE drug_id = ?";

    public static final String FIND_ALL =
            "SELECT * FROM drugs";

    public static final String FIND_BY_NAME =
            "SELECT * FROM drugs WHERE LOWER(name) = ?";

    //user
    public static final String FIND_PATIENT_BY_ID =
            "SELECT * FROM users WHERE user_id = ? AND type = 'patient'";

    public static final String FIND_DOCTOR_BY_ID =
            "SELECT * FROM users WHERE user_id = ? AND type = 'doctor'";

    //prescription
    public static final String FIND_ADR_BY_ID =
            "SELECT * FROM adverse_drug_reactions WHERE id = ?";

    public static final String FIND_ALL_ADR =
            "SELECT * FROM adverse_drug_reactions";

    public static final String FIND_PRESCRIPTION_BY_PATIENT_ID =
            "SELECT * FROM prescriptions WHERE patient_id = ?";

    public static final String FIND_DRUGS_BY_PRESCRIPTION_ID =
            "SELECT d.* FROM drugs d " +
                    "JOIN prescriptions_drugs pd ON d.drug_id = pd.drug_id " +
                    "WHERE pd.prescription_id = ?";

    public static final String FIND_ADRS_BY_PRESCRIPTION_ID =
            "SELECT adr.* FROM adverse_drug_reactions adr " +
                    "JOIN adrs_prescriptions ap ON adr.adr_id = ap.adr_id " +
                    "WHERE ap.prescription_id = ?";

    //JPA queries
    //adr
    public static final String JPA_FIND_ADRS_BY_PRESCRIPTION_ID =
            "SELECT a FROM Prescription rx JOIN rx.effects a WHERE rx.id = :rxid";

    public static final String JPA_FIND_ALL_ADRS =
            "SELECT a FROM AdverseDrugReaction a";

    //drug
    public static final String JPA_FIND_ALL_DRUGS =
            "SELECT d FROM Drug d";

    public static final String JPA_FIND_DRUGS_BY_PRESCRIPTION_ID =
            "SELECT d FROM Prescription rx JOIN rx.drugs d WHERE rx.id = :rxid";

    //user
    public static final String JPA_FIND_PRESCRIPTION_BY_PATIENT_ID=
            "SELECT rx FROM Prescription rx WHERE rx.patient.userId = :pid";
}