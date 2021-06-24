package test;

import java.util.*;
import javax.persistence.*;
import javax.persistence.criteria.*;
import com.gm.sga.capadatos.dto.Alumno;

public class TestApiCriteriaHibernateJPA {

    public static void main(String[] args) {
        /*Utilizamos la Unidad de Persistencia de JPA*/
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("HibernateJpaPU");
        EntityManager em = fabrica.createEntityManager();

        //Variables de ayuda 
        CriteriaBuilder cb = em.getCriteriaBuilder();
        List<Alumno> alumnos = null;
        Alumno alumno = null;

        // Query 1
        // Consulta de todos los Alumnos
        //JPQL equivalente:SELECT a FROM Alumno a
        System.out.println("\nQuery 1");
        //Se crea el objeto criteria query
        CriteriaQuery<Alumno> q1 = cb.createQuery(Alumno.class);
        //Establece el root del query
        q1.from(Alumno.class);
        //Se ejecuta el query
        alumnos = em.createQuery(q1).getResultList();
        //Imprimimos los alumnos
        imprimirAlumnos(alumnos);
        
        // Query2
        // Consulta del Alumno con id = 7
        System.out.println("\nQuery 2");
        CriteriaQuery<Alumno> q2 = cb.createQuery(Alumno.class);
        Root<Alumno> c2 = q2.from(Alumno.class);
        ParameterExpression<Integer> pId = cb.parameter(Integer.class);
        q2.select(c2).where(cb.equal(c2.get("idAlumno"), pId));
        //Ejecutamos el query
        TypedQuery<Alumno> query = em.createQuery(q2);
        //Establecemos el valor del parÃ¡metro
        query.setParameter(pId, 7);
        alumno = query.getSingleResult();
        System.out.println(alumno);
        
        // Query 3
        // Consulta del alumno con nombre
        System.out.println("\nQuery 3");
        CriteriaQuery<Alumno> q3 = cb.createQuery(Alumno.class);
        Root<Alumno> c3 = q3.from(Alumno.class);
        ParameterExpression<String> pNombre = cb.parameter(String.class);
        q3.select(c3).where(cb.equal(c3.get("nombre"), pNombre));
        //Ejecutamos el query
        TypedQuery<Alumno> query3 = em.createQuery(q3);
        //Establecemos el valor del parÃ¡metro
        query3.setParameter(pNombre, "Martha");
        alumnos = query3.getResultList();
        imprimirAlumnos(alumnos);
        // Query 4
        // Consulta alumnos restringiendo por el idAlumno
        System.out.println("\nQuery 4");
        CriteriaQuery<Alumno> qb4 = cb.createQuery(Alumno.class);
        Root<Alumno> c4 = qb4.from(Alumno.class);
        qb4.where(c4.get("idAlumno").in(cb.parameter(Collection.class)));

        TypedQuery<Alumno> q4 = em.createQuery(qb4);
        Integer[] idAlumnos = {7,8};//colocar id validos
        for (ParameterExpression parameter : qb4.getParameters()) {
            q4.setParameter(parameter, Arrays.asList( idAlumnos ));
        }
        alumnos = q4.getResultList();
        imprimirAlumnos(alumnos);
        
        // Query 5
        // Obtiene los alumnos cuyo apeMaterno es no nulo
        System.out.println("\nQuery 5");
        CriteriaQuery<Alumno> qb5 = cb.createQuery(Alumno.class);
        Root<Alumno> c5 = qb5.from(Alumno.class);
        qb5.select(c5).where(cb.isNotNull(c5.get("apellidoMaterno")));
        //Ejecutamos el query
        TypedQuery<Alumno> q5 = em.createQuery(qb5);
        alumnos = q5.getResultList();
        imprimirAlumnos(alumnos);
        
        //Query 6
        System.out.println("\nQuery 6");
        //Obtiene los alumnos cuyo nombre comience con una "m"
        CriteriaQuery<Alumno> qb6 = cb.createQuery(Alumno.class);
        Root<Alumno> c6 = qb6.from(Alumno.class);
        Expression<String> path = c6.get("nombre");
        Expression<String> mayusculas = cb.upper(path);
        String cadenaBuscar = "" + "m".toUpperCase() +"%";
        Predicate predicado = cb.like(mayusculas, cadenaBuscar);
        qb6.where(cb.and(predicado));
        alumnos = em.createQuery(qb6.select(c6)).getResultList();
        imprimirAlumnos(alumnos);
        
        //Query 7
        System.out.println("\nQuery 7");
        //Mismo query que el 6, pero en JPA no existe MatchMode.START
        //por lo que el query queda igual
        
        //Query 8
        //Obtiene los alumnos cuyo nombre contenga "s" con ignoreCase
        System.out.println("\nQuery 8");
        CriteriaQuery<Alumno> qb8 = cb.createQuery(Alumno.class);
        Root<Alumno> c8 = qb8.from(Alumno.class);
        String cadenaBuscar2 = "%" + "s".toUpperCase() + "%";
        qb8.where(cb.like(cb.upper(c8.get("nombre")), cadenaBuscar2));  
        alumnos = em.createQuery(qb8).getResultList();
        imprimirAlumnos(alumnos);
        
        //Query 9
        //Obtiene los alumnos agregando varias restricciones
        //se agregan con 'and' por default
        System.out.println("\nQuery 9");
        CriteriaQuery<Alumno> qb9 = cb.createQuery(Alumno.class);
        Root<Alumno> c9 = qb9.from(Alumno.class);
        //Creamos las restricciones
        Predicate[] restrictions = new Predicate[]{
            cb.equal(c9.get("nombre"), "Carlos"),
            cb.isNotNull(c9.get("apellidoMaterno"))
        };
        //Agregamos las restricciones
        qb9.where(cb.and(restrictions));
        //Ejecutamos el query
        alumnos = em.createQuery(qb9).getResultList();
        imprimirAlumnos(alumnos);
        
        //Query 10
        //Obtiene los alumnos cuyo nombre empieza con M
        //o el apellidoMaterno es no nulo (usando 'or')
        System.out.println("\nQuery 10");
        CriteriaQuery<Alumno> qb10 = cb.createQuery(Alumno.class);
        Root<Alumno> c10 = qb10.from(Alumno.class);
        //Creamos las restricciones
        Predicate[] restrictions2 = new Predicate[]{
            cb.equal(c10.get("nombre"), "Carlos"),
            cb.isNotNull(c10.get("apellidoMaterno"))
        };
        //Agregamos las restricciones
        qb10.where(cb.or(restrictions2));
        //Ejecutamos el query
        alumnos = em.createQuery(qb10).getResultList();
        imprimirAlumnos(alumnos);

        //Query 11
        //Obtiene los alumnos cuyo apeMaterno es no nulo
        //agregando ordenamiento por nombre asc y apellidoPaterno desc
        System.out.println("\nQuery 11");
        CriteriaQuery<Alumno> qb11 = cb.createQuery(Alumno.class);
        Root<Alumno> c11 = qb11.from(Alumno.class);
        //Creamos las restricciones
        Predicate[] restrictions3 = new Predicate[]{
            cb.equal(c11.get("nombre"), "Carlos"),
            cb.isNotNull(c11.get("apellidoMaterno"))
        };
        //Agregamos las restricciones
        qb11.where(cb.or(restrictions3));
        //Agregamos ordenamiento
        qb11.orderBy(cb.asc(c11.get("nombre")), cb.desc(c11.get("apellidoPaterno")));
        //Ejecutamos el query
        alumnos = em.createQuery(qb11).getResultList();
        imprimirAlumnos(alumnos);

    }

    private static void imprimirAlumnos(List<Alumno> alumnos) {
        for (Alumno a : alumnos) {
            System.out.println(a);
        }
    }
}