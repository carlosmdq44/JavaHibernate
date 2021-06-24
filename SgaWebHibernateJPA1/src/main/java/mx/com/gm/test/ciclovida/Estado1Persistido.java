
package mx.com.gm.test.ciclovida;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mx.com.gm.domain.Contacto;

public class Estado1Persistido {
    public static void main(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("HibernateJpaPU");
        EntityManager em= emf.createEntityManager();///abrir conexion a la base de datos 
        
        ///1 estado transitivo
        Contacto contacto = new Contacto();
        contacto.setEmail("clara");
        contacto.setTelefono("11223344");
        
        /// 2 persistimos el objeto
        em.getTransaction().begin();
        
        em.persist(contacto);
        
        em.getTransaction().commit();///
        
        ///3. detached (separaod)
        System.out.print("contacto = "+ contacto);
        
        
    }
    
}
