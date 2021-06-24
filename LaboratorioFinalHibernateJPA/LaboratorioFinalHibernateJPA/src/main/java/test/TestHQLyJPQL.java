package test;

import com.gm.sga.capadatos.dto.Alumno;
import com.gm.sga.capadatos.dto.Contacto;
import com.gm.sga.capadatos.dto.Domicilio;
import java.util.*;
import javax.persistence.*;

public class TestHQLyJPQL {

    public static void main(String[] args) {
        /*Utilizamos la Unidad de Persistencia de JPA*/
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("HibernateJpaPU");
        EntityManager em = fabrica.createEntityManager();

        //Variables auxiliares para ejecutar los queries
        String consulta = null;
        Query q = null;
        List<Alumno> alumnos = null;
        Alumno alumno = null;
        Iterator iter = null;
        Object[] tupla = null;

        //Query 1
        System.out.println("\nQuery 1");
        consulta = "from Alumno a";
        q = em.createQuery(consulta);
        alumnos = q.getResultList();
        //Imprimimos a todos los objetos de tipo Alumno
        for (Alumno a : alumnos) {
            System.out.println(a);
        }

        //Query2
        System.out.println("\nQuery 2");
        consulta = "from Alumno a where a.idAlumno = 2";
        q = em.createQuery(consulta);
        alumno = (Alumno) q.getSingleResult();
        System.out.println(alumno);

        //Query 3
        System.out.println("\nQuery 3");
        consulta = "from Alumno a where a.nombre = 'Martha'";
        q = em.createQuery(consulta);
        alumnos = q.getResultList();
        for (Alumno a : alumnos) {
            System.out.println(a);
        }

        //Query 4
        //Cada tupla se regresa como un arreglo de objetos
        System.out.println("\nQuery 4");
        consulta = "select a.nombre, a.apellidoPaterno, a.apellidoMaterno from Alumno a";
        q = em.createQuery(consulta);
        iter = q.getResultList().iterator();
        while (iter.hasNext()) {
            tupla = (Object[]) iter.next();
            String nombre = (String) tupla[0];
            String apePat = (String) tupla[1];
            String apeMat = (String) tupla[2];
            System.out.println(nombre + " " + apePat + " " + apeMat);
        }

        //Query 5
        //cada tupla se regresa como un arreglo de objetos
        System.out.println("\nQuery 5");
        consulta = "select a, a.idAlumno from Alumno a";
        q = em.createQuery(consulta);
        iter = q.getResultList().iterator();
        while (iter.hasNext()) {
            tupla = (Object[]) iter.next();
            alumno = (Alumno) tupla[0];
            Integer idAlumno = (Integer) tupla[1];
            System.out.println("idAlumno:" + idAlumno);
            System.out.println("Objeto Alumno:" + alumno);
        }

        //Query 6
        System.out.println("\nQuery 6");
        consulta = "select new Alumno(a.idAlumno) from Alumno a";
        q = em.createQuery(consulta);
        alumnos = q.getResultList();
        for (Alumno a : alumnos) {
            System.out.println(a);
        }

        //Query 7
        //Regresa el valor minimo y maxico del idAlumno (scalar result)
        System.out.println("\nQuery 7");
        consulta = "select min(a.idAlumno), max(a.idAlumno), count(a) from Alumno a";
        q = em.createQuery(consulta);
        iter = q.getResultList().iterator();
        while (iter.hasNext()) {
            tupla = (Object[]) iter.next();
            Integer idMin = (Integer) tupla[0];
            Integer idMax = (Integer) tupla[1];
            Long count = (Long) tupla[2];
            System.out.println("idMin:" + idMin + ", idMax:" + idMax + ", count:" + count);
        }

        //Query 11
        //Obtiene el objeto alumno con id igual al parametro
        System.out.println("\nQuery 11");
        consulta = "from Alumno a where a.idAlumno = :id";
        q = em.createQuery(consulta);
        q.setParameter("id", 1);
        alumno = (Alumno) q.getSingleResult();
        System.out.println(alumno);

        //Query 12
        //Obtiene los alumnos que contenga una letra a,
        //sin importar masuculas o minusculas
        System.out.println("\nQuery 12");
        String cadena = "%m%"; //se usa en el like como caracteres especiales
        consulta = "from Alumno a where upper(a.nombre) like upper(:param1)";
        q = em.createQuery(consulta);
        q.setParameter("param1", cadena);
        alumnos = q.getResultList();
        for (Alumno a : alumnos) {
            System.out.println(a);
        }

        //Query 19
        System.out.println("\nQuery 19");
        consulta = "from Alumno a join a.domicilio d join a.contacto c";
        q = em.createQuery(consulta);
        iter = q.getResultList().iterator();
        while (iter.hasNext()) {
            tupla = (Object[]) iter.next();
            alumno = (Alumno) tupla[0];
            Domicilio domicilio = (Domicilio) tupla[1];
            Contacto contacto = (Contacto) tupla[2];
            System.out.println();
            System.out.println("Alumno:" + alumno);
            System.out.println("Domicilio:" + domicilio);
            System.out.println("Contacto:" + contacto);
        }

        //Query 20
        System.out.println("\nQuery 20");
        consulta = "from Alumno a join fetch a.domicilio d join fetch a.contacto c";
        q = em.createQuery(consulta);
        alumnos = q.getResultList();
        for (Alumno a : alumnos) {
            System.out.println();
            System.out.println("Alumno:" + a);
            System.out.println("Domicilio:" + a.getDomicilio());
            System.out.println("Contacto:" + a.getContacto());
        }

    }//Fin del main

}