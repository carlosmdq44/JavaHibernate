package com.gm.sga.capadatos.dao;

import static com.gm.sga.capadatos.dao.GenericDAO.em;
import java.util.List;
import javax.persistence.Query;
import com.gm.sga.capadatos.dto.Domicilio;

public class DomicilioDAO extends GenericDAO{
      public void listar() {
        // Consulta a ejecutar
        // No necesitamos crear una nueva transaccion
        String hql = "SELECT d FROM Domicilio d";
        em = getEntityManager();
        Query query = em.createQuery(hql);
        List<Domicilio> list = query.getResultList();
        for (Domicilio domicilio : list) {
            System.out.println(domicilio);
        }
    }

    public void insertar(Domicilio domicilio) {
        try {
            em = getEntityManager();
            // Iniciamos una transaccion
            em.getTransaction().begin();
            // Insertamos nuevo objeto
            em.persist(domicilio);
            // Terminamos la transaccion
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Error al insetar objeto:" + ex.getMessage());
            // ex.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void actualizar(Domicilio domicilio) {
        try {
            em = getEntityManager();
            // Iniciamos una transaccion
            em.getTransaction().begin();
            // Actualizamos al objeto 
            em.merge(domicilio);
            // Terminamos la transaccion
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Error al actualizar objeto:" + ex.getMessage());
            // ex.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void eliminar(Domicilio domicilio) {
        try {
            em = getEntityManager();
            // Iniciamos una transaccion
            em.getTransaction().begin();
            // Sincronizamos y eliminamos 
            em.remove(em.merge(domicilio));
            // Terminamos la transaccion
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Error al eliminar objeto:" + ex.getMessage());
            // ex.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Object buscarPorId(Domicilio domicilio) {
        em = getEntityManager();
        return em.find(Domicilio.class, domicilio.getIdDomicilio());
    }
}