package edu.jbussiere.bdeb.a10.persistence.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.spi.PersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;

public class EntityManagerProvider {

    private static EntityManagerProvider provider = null;
    private EntityManager em;
    private final EntityManagerFactory emf;

    private EntityManagerProvider() {
        PersistenceUnitInfo pui=new DRUGSTORE_PU();
        EntityManagerFactory emf= new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(pui, new HashMap<>());
        this.em=emf.createEntityManager();
        this.emf=emf;
    }

    public static EntityManagerProvider getProvider(){
        if(provider==null){
            provider=new EntityManagerProvider();
        }
        return provider;
    }

    public EntityManager getEntityManager(){
        return this.em;
    }

    public void closeEntityManager(){
        if (this.em!=null && this.em.isOpen()) {
            this.em.close();
        }
        if (this.emf!=null && this.emf.isOpen()) {
            this.emf.close();
        }
    }


}
