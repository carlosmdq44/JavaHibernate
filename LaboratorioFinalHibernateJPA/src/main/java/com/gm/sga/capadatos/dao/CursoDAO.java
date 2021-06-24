package com.gm.sga.capadatos.dao;

import static com.gm.sga.capadatos.dao.GenericDAO.em;
import java.util.List;
import javax.persistence.Query;
import com.gm.sga.capadatos.dto.Curso;

public class CursoDAO extends GenericDAO{
      public void listar() {
        // Consulta a ejecutar
        // No necesitamos crear una nueva transaccion
        String hql = "SELECT c FROM Curso c";
        em = getEntityManager();
        Query query = em.createQuery(hql);
        List<Curso> list = query.getResultList();
        for (Curso c : list) {
            System.out.println(c);
        }
    }

    public void insertar(Curso curso) {
        try {
            em = getEntityManager();
            // Iniciamos una transaccion
            em.getTransaction().begin();
            // Insertamos nuevo objeto
            em.persist(curso);
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

    public void actualizar(Curso curso) {
        try {
            em = getEntityManager();
            // Iniciamos una transaccion
            em.getTransaction().begin();
            // Actualizamos al objeto 
            em.merge(curso);
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

    public void eliminar(Curso curso) {
        try {
            em = getEntityManager();
            // Iniciamos una transaccion
            em.getTransaction().begin();
            // Sincronizamos y eliminamos 
            em.remove(em.merge(curso));
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

    public Object buscarPorId(Curso curso) {
        em = getEntityManager();
        return em.find(Curso.class, curso.getIdCurso());
    }
}