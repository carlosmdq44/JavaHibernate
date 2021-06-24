package mx.com.gm.test.cascade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mx.com.gm.domain.Alumno;
import mx.com.gm.domain.Contacto;
import mx.com.gm.domain.Domicilio;

public class PersistenciaCascadaTest {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HibernateJpaPU");
        EntityManager em = emf.createEntityManager();
        
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("Nogales");
        domicilio.setNoCalle("10");
        domicilio.setPais("Mexico");
        
        Contacto contacto = new Contacto();
        contacto.setEmail("clara@mail.com");
        contacto.setTelefono("44332211");

        Alumno alumno = new Alumno();
        alumno.setNombre("Carlos");
        alumno.setApellido("Lara");
        alumno.setDomicilio(domicilio);
        alumno.setContacto(contacto);
        
        em.getTransaction().begin();
        em.persist(alumno);
        em.getTransaction().commit();
        
        System.out.println("alumno = " + alumno);
    }
}
