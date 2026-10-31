package edu.jbussiere.bdeb.a10.persistence.jdbc;

import edu.jbussiere.bdeb.a10.persistence.DBConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

    private static ConnectionProvider instance;


    private final String username=DBConfig.username();
    private final String password=DBConfig.password();
    private final String url=DBConfig.url();

    public static ConnectionProvider getInstance() {
        if (instance == null) {
            instance = new ConnectionProvider();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(
                url, username, password
            );
        } catch (SQLException e) {
            throw new RuntimeException("Erreur de connection", e);
        }
    }

}
