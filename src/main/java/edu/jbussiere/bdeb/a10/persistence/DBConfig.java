package edu.jbussiere.bdeb.a10.persistence;

import java.io.InputStream;
import java.util.Properties;

public class DBConfig {

    private static final Properties properties = new Properties();

    static {
        try (InputStream in = DBConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (in == null) throw new RuntimeException("config.properties resource introuvable");
            properties.load(in);
        } catch (Exception e) {
                throw new RuntimeException("Erreur chargement du fichier de configuration");
        }
    }

    public static String driver() {
        return properties.getProperty("db.driver");
    }
    public static String dialect() {
        return properties.getProperty("db.dialect");
    }
    public static String host() {
        return properties.getProperty("db.host");
    }
    public static String name() {
        return properties.getProperty("db.name");
    }
    public static String port() {return properties.getProperty("db.port");}
    public static String username() {return properties.getProperty("db.username");}
    public static String password() {return properties.getProperty("db.password");}
    public static String url() {return "jdbc:mariadb://" + host() + ":" + port() + "/" + name();}
}
