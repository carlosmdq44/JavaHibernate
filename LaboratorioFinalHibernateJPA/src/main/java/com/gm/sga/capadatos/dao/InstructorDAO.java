package com.gm.sga.capadatos.dao;

import static com.gm.sga.capadatos.dao.GenericDAO.em;
import java.util.List;
import javax.persistence.Query;
import com.gm.sga.capadatos.dto.Instructor;

public class InstructorDAO extends GenericDAO{
       public void listar() {
        // Consulta a ejecutar
        // No necesitamos crear una nueva transaccion
        String hql = "SELECT i FROM Instructor i";
        em = getEntityManager();
        Query query = em.createQuery(hql);
        List<Instructor> list = query.getResultList();
        for (Instructor instructor : list) {
            System.out.println(instructor);
        }
    }

    public void insertar(Instructor instructor) {
        try {
            em = getEntityManager();
            // Iniciamos una transaccion
            em.getTransaction().begin();
            // Insertamos nuevo objeto
            em.persist(instructor);
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

    public void actualizar(Instructor instructor) {
        try {
            em = getEntityManager();
            // Iniciamos una transaccion
            em.getTransaction().begin();
            // Actualizamos al objeto 
            em.merge(instructor);
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

    public void eliminar(Instructor instructor) {
        try {
            em = getEntityManager();
            // Iniciamos una transaccion
            em.getTransaction().begin();
            // Sincronizamos y eliminamos 
            em.remove(em.merge(instructor));
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

    public Object buscarPorId(Instructor instructor) {
        em = getEntityManager();
        return em.find(Instructor.class, instructor.getIdInstructor());
    }
}