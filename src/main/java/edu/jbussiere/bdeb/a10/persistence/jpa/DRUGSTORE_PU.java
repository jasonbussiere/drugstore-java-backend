package edu.jbussiere.bdeb.a10.persistence.jpa;

import edu.jbussiere.bdeb.a10.persistence.DBConfig;
import jakarta.persistence.SharedCacheMode;
import jakarta.persistence.ValidationMode;
import jakarta.persistence.spi.ClassTransformer;
import jakarta.persistence.spi.PersistenceUnitInfo;
import jakarta.persistence.spi.PersistenceUnitTransactionType;

import javax.sql.DataSource;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DRUGSTORE_PU implements PersistenceUnitInfo {

    @Override
    public String getPersistenceUnitName() {
        return "DRUGSTORE_PU";
    }

    @Override
    public String getPersistenceProviderClassName() {
        return "org.hibernate.jpa.HibernatePersistenceProvider";
    }

    @Override
    public PersistenceUnitTransactionType getTransactionType() {
        return null;
    }

    @Override
    public DataSource getJtaDataSource() {
        return null;
    }

    @Override
    public DataSource getNonJtaDataSource() {
        return null;
    }

    @Override
    public List<String> getMappingFileNames() {
        return List.of();
    }

    @Override
    public List<URL> getJarFileUrls() {
        return List.of();
    }

    @Override
    public URL getPersistenceUnitRootUrl() {
        return null;
    }

    @Override
    public List<String> getManagedClassNames() {
        List<String> persistedClasses = new ArrayList<>();
        persistedClasses.add("edu.jbussiere.bdeb.a10.model.User");
        persistedClasses.add("edu.jbussiere.bdeb.a10.model.Drug");
        persistedClasses.add("edu.jbussiere.bdeb.a10.model.AdverseDrugReaction");
        persistedClasses.add("edu.jbussiere.bdeb.a10.model.Prescription");
        persistedClasses.add("edu.jbussiere.bdeb.a10.model.Patient");
        persistedClasses.add("edu.jbussiere.bdeb.a10.model.Doctor");
        return persistedClasses;
    }

    @Override
    public boolean excludeUnlistedClasses() {
        return false;
    }

    @Override
    public SharedCacheMode getSharedCacheMode() {
        return null;
    }

    @Override
    public ValidationMode getValidationMode() {
        return null;
    }

    @Override
    public Properties getProperties() {
        Properties props = new Properties();
        props.setProperty("hibernate.connection.driver_class", DBConfig.driver());
        props.setProperty("hibernate.connection.url", DBConfig.url());
        props.setProperty("hibernate.connection.username", DBConfig.username());
        props.setProperty("hibernate.connection.password", DBConfig.password());
        props.setProperty("hibernate.formay_sql", "false");
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.dialect", DBConfig.dialect());
        props.setProperty("hibernate.hbm2ddl.auto", "update");
        return props;
    }

    @Override
    public String getPersistenceXMLSchemaVersion() {
        return "3.0";
    }

    @Override
    public ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    @Override
    public void addTransformer(ClassTransformer transformer) {

    }

    @Override
    public ClassLoader getNewTempClassLoader() {
        return null;
    }


}
