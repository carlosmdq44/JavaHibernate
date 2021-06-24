
package mx.com.gm.test.ciclovida;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mx.com.gm.domain.Contacto;

public class Estado3ModificarObjetoPersistente {
     public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HibernateJpaPU");
        EntityManager em = emf.createEntityManager();///abrir conexion a la base de datos 
        /// para recuperar no es necesario llamar a toda la base no hay q iniciar transaccion 
        Contacto contacto = null;

        /// recuperamos un objeto tipo contacto de la base de datos
        /// Recuperamos el objeto
        
        contacto = em.find(Contacto.class, 3);

        /// modificamos el objeto
        
        contacto.setEmail("clara@mail.com");
        em.getTransaction().begin();
        /// 2- perisstente hacemos el punto 2 pq si solo hacemios el uno modificamos pero no guardamos en base de datos
        em.persist(contacto);
        em.getTransaction().commit();
        
        System.out.print("contacto = " + contacto);

    }

}
