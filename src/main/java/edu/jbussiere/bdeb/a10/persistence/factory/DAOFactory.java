package edu.jbussiere.bdeb.a10.persistence.factory;

import edu.jbussiere.bdeb.a10.dao.IADR_DAO;
import edu.jbussiere.bdeb.a10.dao.IDrugDAO;
import edu.jbussiere.bdeb.a10.dao.IUserDAO;
import edu.jbussiere.bdeb.a10.persistence.jdbc.ConnectionProvider;
import edu.jbussiere.bdeb.a10.persistence.mock.MockDB;
import edu.jbussiere.bdeb.a10.persistence.jdbc.ADR_DAO_JDBC;
import edu.jbussiere.bdeb.a10.persistence.jdbc.UserDAO_JDBC;
import edu.jbussiere.bdeb.a10.persistence.jpa.ADR_DAO_JPA;
import edu.jbussiere.bdeb.a10.persistence.jpa.DrugDAO_JPA;
import edu.jbussiere.bdeb.a10.persistence.jpa.EntityManagerProvider;
import edu.jbussiere.bdeb.a10.persistence.jpa.UserDAO_JPA;
import edu.jbussiere.bdeb.a10.persistence.mock.ADR_DAO_MockDB;
import edu.jbussiere.bdeb.a10.persistence.jdbc.DrugDAO_JDBC;
import edu.jbussiere.bdeb.a10.persistence.mock.DrugDAO_MockDB;
import edu.jbussiere.bdeb.a10.persistence.mock.UserDAO_MockDB;
import jakarta.persistence.EntityManager;

public class DAOFactory {

    //change DATASOURCE to "jdbc", "jpa", or "mock"
    private static DataSourceType currentDataSource = DataSourceType.MOCK;  //mockDb is default

    //instance of mockDB
    private static final MockDB mockDB = MockDB.getInstance();
    private static final ConnectionProvider jdbcDB = ConnectionProvider.getInstance();
    private static final EntityManager jpaDB = EntityManagerProvider.getProvider().getEntityManager();

    //datasource methods
    public static void useJPA() {
        currentDataSource = DataSourceType.JPA;
    }

    public static void useJDBC() {
        currentDataSource = DataSourceType.JDBC;
    }

    public static void useMock() {
        currentDataSource = DataSourceType.MOCK;
    }

    public static DataSourceType getCurrentDataSource() {
        return currentDataSource;
    }

    //factory methods
    public static IDrugDAO createDrugDAO() {
        return switch (currentDataSource) {
            case MOCK -> new DrugDAO_MockDB(mockDB);
            case JDBC -> new DrugDAO_JDBC(jdbcDB);
            case JPA -> new DrugDAO_JPA(jpaDB);
        };
    }

    public static IADR_DAO createADR_DAO() {
        return switch (currentDataSource) {
            case MOCK -> new ADR_DAO_MockDB(mockDB);
            case JDBC -> new ADR_DAO_JDBC(jdbcDB);
            case JPA -> new ADR_DAO_JPA(jpaDB);
        };
    }

    public static IUserDAO createUserDAO() {
        return switch (currentDataSource) {
            case MOCK -> new UserDAO_MockDB(mockDB);
            case JDBC -> new UserDAO_JDBC(jdbcDB);
            case JPA -> new UserDAO_JPA(jpaDB);
        };

    }
}
