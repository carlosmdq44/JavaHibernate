package test;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.gm.sga.capadatos.dto.Alumno;
import com.gm.sga.capadatos.dto.Contacto;
import com.gm.sga.capadatos.dto.Domicilio;

public class TestApiCriteriaLazyEagerLoading {

    public static void main(String[] args) {
        /*Utilizamos la Unidad de Persistencia de JPA*/
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("HibernateJpaPU");
        EntityManager em = fabrica.createEntityManager();

        //Variables de ayuda 
        CriteriaBuilder cb = em.getCriteriaBuilder();
        List<Alumno> alumnos = null;

        //Por default los queries son tipo lazy
        //es decir no levantan las relaciones
        //Left Join con API Criteria
        //Query 1
        System.out.println("\nQuery 1");
        CriteriaQuery<Alumno> qb1 = cb.createQuery(Alumno.class);
        Root<Alumno> c1 = qb1.from(Alumno.class);
        c1.join("domicilio", JoinType.LEFT);
        c1.join("contacto", JoinType.LEFT);
        
        alumnos = em.createQuery(qb1).getResultList();
        imprimirAlumnos(alumnos);

        //Query 2
        System.out.println("\nQuery 2");
        //Definimos el query 
        CriteriaQuery<Alumno> qb2 = cb.createQuery(Alumno.class);
        //Definimos la raiz del query
        Root<Alumno> c2 = qb2.from(Alumno.class);
        //Especificamos el join
        Join<Alumno, Domicilio> dom = c2.join("domicilio");
        //De manera opcional agregamos la restriccion usando el join 
        qb2.where(cb.equal(dom.<Integer>get("idDomicilio"), 1));
        //Definimos un Entity Graph para especificar el Fetch join
        EntityGraph<Alumno> fetchGraph = em.createEntityGraph(Alumno.class);
        //Especificamos la relaciÃ³n a levantar de manera eager (anticipada)
        fetchGraph.addSubgraph("domicilio");
        //loadgraph agrega lo definimo mÃ¡s lo que ya se especificÃ³ en la clase de entidad
        //fetchgraph ignora lo definido en la clase de entidad y agrega solo lo nuevo
        em.createQuery(qb2).setHint("javax.persistence.loadgraph", fetchGraph);

        TypedQuery<Alumno> q2 = em.createQuery(qb2);
        alumnos = q2.getResultList();
        imprimirAlumnos(alumnos);

        // Query 3
        System.out.println("\nQuery 3");
        CriteriaQuery<Alumno> qb3 = cb.createQuery(Alumno.class);
        Root<Alumno> c3 = qb3.from(Alumno.class);
        Join<Alumno, Domicilio> domicilio = c3.join("domicilio");
        Join<Alumno, Contacto> contacto = c3.join("contacto");
        List<Predicate> conditions = new ArrayList();
        Integer idAlumno = 1;
        conditions.add(cb.equal(c3.get("idAlumno"), idAlumno));
        conditions.add(cb.isNotNull(domicilio.get("calle")));
        conditions.add(cb.isNotNull(contacto.get("telefono")));

        TypedQuery<Alumno> q3 = em.createQuery(
                qb3
                .select(c3)
                .where(conditions.toArray(new Predicate[]{}))
                .orderBy(cb.asc(c3.get("idAlumno")))
                .distinct(true)
        );

        imprimirAlumnos(q3.getResultList());
    }

    private static void imprimirAlumnos(List<Alumno> alumnos) {
        for (Alumno a : alumnos) {
            System.out.println(a);
        }
    }
}