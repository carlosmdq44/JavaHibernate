package mx.com.gm.test.ciclovida;

import javax.persistence.*;
import mx.com.gm.domain.Contacto;

public class Estado2RecuperarObjectoPersistente {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HibernateJpaPU");
        EntityManager em = emf.createEntityManager();

        //definimos la variable
        Contacto contacto = null;

        em.getTransaction().begin();
        //recuperamos el objeto
        contacto = em.find(Contacto.class, 3);

        em.getTransaction().commit();

        //detached
        System.out.println("contacto = " + contacto);
    }

}
