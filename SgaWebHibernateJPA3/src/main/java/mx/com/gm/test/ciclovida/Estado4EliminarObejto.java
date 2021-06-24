package mx.com.gm.test.ciclovida;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.*;
import mx.com.gm.domain.Contacto;

public class Estado4EliminarObejto {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HibernateJpaPU");
        EntityManager em = emf.createEntityManager();///abrir conexion a la base de datos 
        /// para recuperar no es necesario llamar a toda la base no hay q iniciar transaccion 
        Contacto contacto = null;

        /// recuperamos un objeto tipo contacto de la base de datos
        /// Recuperamos el objeto
        contacto = em.find(Contacto.class, 3);

        em.getTransaction().begin();

        ///3 ejecutamos el metodo remove. api hibernate
        em.remove(em.merge(contacto));/// para sincronizar en la base de datos utilizo merge

        em.getTransaction().commit();

        /// transitivo el objeto eliminado no tiene entidad en la base de dato 
        /// al elimnar el objeto
        System.out.print("contacto = " + contacto);

    }

}
