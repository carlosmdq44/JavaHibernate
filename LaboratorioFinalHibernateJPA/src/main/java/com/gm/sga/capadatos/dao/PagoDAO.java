package com.gm.sga.capadatos.dao;

import static com.gm.sga.capadatos.dao.GenericDAO.em;
import java.util.List;
import javax.persistence.Query;
import com.gm.sga.capadatos.dto.Pago;

public class PagoDAO extends GenericDAO{

    public void listar() {
        // Consulta a ejecutar
        // No necesitamos crear una nueva transaccion
        String hql = "SELECT p FROM Pago p";
        em = getEntityManager();
        Query query = em.createQuery(hql);
        List<Pago> list = query.getResultList();
        for (Pago pago : list) {
            System.out.println(pago);
        }
    }

    public void insertar(Pago pago) {
        try {
            em = getEntityManager();
            // Iniciamos una transaccion
            em.getTransaction().begin();
            // Insertamos nuevo objeto
            em.persist(pago);
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

    public void actualizar(Pago pago) {
        try {
            em = getEntityManager();
            // Iniciamos una transaccion
            em.getTransaction().begin();
            // Actualizamos al objeto 
            em.merge(pago);
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

    public void eliminar(Pago pago) {
        try {
            em = getEntityManager();
            // Iniciamos una transaccion
            em.getTransaction().begin();
            // Sincronizamos y eliminamos 
            em.remove(em.merge(pago));
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

    public Object buscarPorId(Pago pago) {
        em = getEntityManager();
        return em.find(Pago.class, pago.getIdPago());
    }
}