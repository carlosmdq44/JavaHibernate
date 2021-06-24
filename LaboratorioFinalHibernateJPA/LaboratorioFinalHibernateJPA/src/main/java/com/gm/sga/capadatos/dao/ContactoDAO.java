package com.gm.sga.capadatos.dao;

import static com.gm.sga.capadatos.dao.GenericDAO.em;
import java.util.List;
import javax.persistence.Query;
import com.gm.sga.capadatos.dto.Contacto;

public class ContactoDAO extends GenericDAO{
     public void listar() {
        // Consulta a ejecutar
        // No necesitamos crear una nueva transaccion
        String hql = "SELECT c FROM Contacto c";
        em = getEntityManager();
        Query query = em.createQuery(hql);
        List<Contacto> list = query.getResultList();
        for (Contacto c : list) {
            System.out.println(c);
        }
    }

    public void insertar(Contacto contacto) {
        try {
            em = getEntityManager();
            // Iniciamos una transaccion
            em.getTransaction().begin();
            // Insertamos nuevo objeto
            em.persist(contacto);
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

    public void actualizar(Contacto contacto) {
        try {
            em = getEntityManager();
            // Iniciamos una transaccion
            em.getTransaction().begin();
            // Actualizamos al objeto 
            em.merge(contacto);
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

    public void eliminar(Contacto contacto) {
        try {
            em = getEntityManager();
            // Iniciamos una transaccion
            em.getTransaction().begin();
            // Sincronizamos y eliminamos 
            em.remove(em.merge(contacto));
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

    public Object buscarPorId(Contacto contacto) {
        em = getEntityManager();
        return em.find(Contacto.class, contacto.getIdContacto());
    }
}
