package com.gm.sga.capadatos.dao;

import java.util.List;
import javax.persistence.Query;
import com.gm.sga.capadatos.dto.Asignacion;

public class AsignacionDAO extends GenericDAO{
     public void listar() {
        // Consulta a ejecutar
        // No necesitamos crear una nueva transaccion
        String hql = "SELECT a FROM Asignacion a";
        em = getEntityManager();
        Query query = em.createQuery(hql);
        List<Asignacion> list = query.getResultList();
        for (Asignacion a : list) {
            System.out.println(a);
        }
    }

    public void insertar(Asignacion asignacion) {
        try {
            em = getEntityManager();
            // Iniciamos una transaccion
            em.getTransaction().begin();
            // Insertamos nuevo objeto
            em.persist(asignacion);
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

    public void actualizar(Asignacion asignacion) {
        try {
            em = getEntityManager();
            // Iniciamos una transaccion
            em.getTransaction().begin();
            // Actualizamos al objeto 
            em.merge(asignacion);
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

    public void eliminar(Asignacion asignacion) {
        try {
            em = getEntityManager();
            // Iniciamos una transaccion
            em.getTransaction().begin();
            // Sincronizamos y eliminamos 
            em.remove(em.merge(asignacion));
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

    public Object buscarPorId(Asignacion asignacion) {
        em = getEntityManager();
        return em.find(Asignacion.class, asignacion.getIdAsignacion());
    }
}