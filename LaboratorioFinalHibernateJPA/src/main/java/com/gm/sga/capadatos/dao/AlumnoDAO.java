package com.gm.sga.capadatos.dao;

import java.util.*;
import javax.persistence.Query;
import com.gm.sga.capadatos.dto.*;
import org.apache.logging.log4j.*;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.sql.JoinType;

public class AlumnoDAO extends GenericDAO {

    Logger log = LogManager.getRootLogger();

    public void listar() {
        // Consulta a ejecutar
        // No necesitamos crear una nueva transaccion
        String hql = "SELECT a FROM Alumno a";
        em = getEntityManager();
        Query query = em.createQuery(hql);
        List<Alumno> list = query.getResultList();
        for (Alumno a : list) {
            System.out.println(a);
        }
    }

    public List<Alumno> listarAlumnos() {
        String hql = "SELECT a FROM Alumno a";
        em = getEntityManager();
        Query query = em.createQuery(hql);
        return query.getResultList();
    }

    public void insertar(Alumno alumno) {
        try {
            em = getEntityManager();
            // Iniciamos una transaccion
            em.getTransaction().begin();
            // Insertamos nuevo objeto
            em.persist(alumno);
            // Terminamos la transaccion
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Error al insetar objeto:" + ex.getMessage());
            // ex.printStackTrace();
        }
    }

    public void actualizar(Alumno alumno) {
        try {
            em = getEntityManager();
            // Iniciamos una transaccion
            em.getTransaction().begin();
            // Actualizamos al objeto 
            em.merge(alumno);
            // Terminamos la transaccion
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Error al actualizar objeto:" + ex.getMessage());
            // ex.printStackTrace();
        }
    }

    public void eliminar(Alumno alumno) {
        try {
            em = getEntityManager();
            // Iniciamos una transaccion
            em.getTransaction().begin();
            // Sincronizamos y eliminamos 
            em.remove(em.merge(alumno));
            // Terminamos la transaccion
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Error al eliminar objeto:" + ex.getMessage());
            // ex.printStackTrace();
        }
    }

    public Alumno buscarPorId(Alumno alumno) {
        em = getEntityManager();
        return em.find(Alumno.class, alumno.getIdAlumno());
    }

    public List buscarAlumnosPorCriterios(Map criterios) {
        log.debug("finding Alumno instances by different criteria");
        List lista = null;
        //1. Obtenemos los criterios uno por uno segÃºn la bÃºsqueda que programamos
        Alumno alumnoDTO = null;
        Contacto contactoDTO = null;
        Domicilio domicilioDTO = null;
        Curso cursoDTO = null;

        if (criterios != null) {
            alumnoDTO = (Alumno) criterios.get("alumno");
            contactoDTO = (Contacto) criterios.get("contacto");
            domicilioDTO = (Domicilio) criterios.get("domicilio");
            cursoDTO = (Curso) criterios.get("curso");
        }
        try {
            //2. Creamos el objeto criteria para ejecutar el query
            //El mÃ©todo API de Criteria de Hibernate ha sido depreciado en la version 5.2
            //Sin embargo JPA no ofrece de momento una opcion a QueryByExample
            //Asi que hasta que JPA no ofrezca una opcion para este tipo de queries pueden usar 
            //esta version, y en cuanto exista una opcion viable para estos queries
            //se actualizara este codigo
            Criteria criteria = em.unwrap(Session.class).getSession().createCriteria(Alumno.class);

            //3. Agregamos los criterios recibidos
            if (alumnoDTO != null) {

                //Utilizamos un DTO de Alumno y un QBE (Query By Example)
                //Lo envolvemos en un Example
                Example exampleAlumno = Example.create(alumnoDTO).enableLike(MatchMode.ANYWHERE);

                //agregamos el example al query de criteria
                criteria.add(exampleAlumno);
            }
            if (contactoDTO != null) {

                //Utilizamos un DTO de Contacto y un QBE
                //Lo envolvemos en un Example
                Example exampleContacto = Example.create(contactoDTO).enableLike(MatchMode.ANYWHERE);

                //Agregamos la restriccion del contacto
                criteria.createCriteria("contacto", JoinType.LEFT_OUTER_JOIN)
                        .setFetchMode("contacto", FetchMode.JOIN)
                        .add(exampleContacto);
            }
            if (domicilioDTO != null) {

                //Utilizamos un DTO de Domicilio y un QBE
                //Lo envolvemos en un Example
                Example exampleDomicilio = Example.create(domicilioDTO).enableLike(MatchMode.ANYWHERE);

                //Agregamos la restriccion del domicilio
                criteria.createCriteria("domicilio", JoinType.LEFT_OUTER_JOIN)
                        .setFetchMode("domicilio", FetchMode.JOIN)
                        .add(exampleDomicilio);
            }
            if (cursoDTO != null) {

                //Lo envolvemos en un Example
                Example exampleCurso = Example.create(cursoDTO).enableLike(MatchMode.ANYWHERE);

                //Agregamos la restriccion del curso, primero accediendo a asignaciones y luego a curso
                //agregando un criterio dentro de otro (agregar un criterio significa que se vuelve
                //la tabla pivote o root en ese momento del query)
                criteria.createCriteria("asignaciones", JoinType.LEFT_OUTER_JOIN)
                        .setFetchMode("asignaciones", FetchMode.JOIN)
                        .createCriteria("curso", JoinType.LEFT_OUTER_JOIN)
                        .setFetchMode("curso", FetchMode.JOIN)
                        .add(exampleCurso);
            }

            //Restringe a obtener solo los elementos distintos
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

            //4. Obtenemos la lista
            lista = criteria.list();
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
        return lista;
    }
}