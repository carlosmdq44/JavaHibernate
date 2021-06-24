package com.gm.sga.capadatos.dao;

import static com.gm.sga.capadatos.dao.GenericDAO.em;
import java.util.List;
import javax.persistence.Query;
import com.gm.sga.capadatos.dto.Usuario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

public class UsuarioDAO extends GenericDAO {

    Logger log = LogManager.getRootLogger();

    public void listar() {
        // Consulta a ejecutar
        // No necesitamos crear una nueva transaccion
        String hql = "SELECT s FROM Usuario s";
        em = getEntityManager();
        Query query = em.createQuery(hql);
        List<Usuario> list = query.getResultList();
        for (Usuario usuario : list) {
            System.out.println(usuario);
        }
    }

    public void insertar(Usuario usuario) {
        try {
            em = getEntityManager();
            // Iniciamos una transaccion
            em.getTransaction().begin();
            // Insertamos nuevo objeto
            em.persist(usuario);
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

    public void actualizar(Usuario usuario) {
        try {
            em = getEntityManager();
            // Iniciamos una transaccion
            em.getTransaction().begin();
            // Actualizamos al objeto 
            em.merge(usuario);
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

    public void eliminar(Usuario usuario) {
        try {
            em = getEntityManager();
            // Iniciamos una transaccion
            em.getTransaction().begin();
            // Sincronizamos y eliminamos 
            em.remove(em.merge(usuario));
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

    public Usuario buscarPorId(Usuario usuario) {
        em = getEntityManager();
        return em.find(Usuario.class, usuario.getIdUsuario());
    }

    public List<Usuario> listarUsuarios() {
        // Consulta a ejecutar
        // No necesitamos crear una nueva transaccion
        String hql = "SELECT s FROM Usuario s";
        em = getEntityManager();
        Query query = em.createQuery(hql);
        return query.getResultList();
    }

    public List findByExample(Usuario usuarioDTO) {
        log.debug("find Usuario by Example");
        em = getEntityManager();
        try {
            Session s = em.unwrap(Session.class).getSession();
            Criteria c = s.createCriteria("com.gm.sga.capadatos.dto.Usuario").add(
                    Example.create(usuarioDTO));
            List results = c.list();
            log.debug("find by example successful, result size: "
                    + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
}