package mx.com.gm.test.ciclovida;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.*;
import mx.com.gm.domain.Contacto;

public class Estado2RecuperarObjetoPersistente {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HibernateJpaPU");
        EntityManager em = emf.createEntityManager();///abrir conexion a la base de datos 
        /// para recuperar no es necesario llamar a toda la base no hay q iniciar transaccion 

        /// recuperamos un objeto tipo contacto de la base de datos
        /// DEFINIMOS LA VARIABLE
        Contacto contacto = null;
        /// Recuperamos el objeto
        contacto = em.find(Contacto.class, 3);

        /// detached
        System.out.print("contacto = " + contacto);

    }

}
