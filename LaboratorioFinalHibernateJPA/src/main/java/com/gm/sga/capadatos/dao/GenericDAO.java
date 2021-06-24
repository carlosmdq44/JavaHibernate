package com.gm.sga.capadatos.dao;

import javax.persistence.*;

public abstract class GenericDAO {

    protected static EntityManager em;
    private static EntityManagerFactory emf;
    private static final String PERSISTENCE_UNIT_NAME = "HibernateJpaPU";

    public GenericDAO() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
    }

    protected EntityManager getEntityManager() {
        if( em == null){
            em = emf.createEntityManager();
        }
        return em; 
    }
}