package libreria.Servicios;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import libreria.Entidad.Editorial;
import libreria.Entidad.N;

public class ServicioEditorial {

    EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    Editorial edit = new Editorial();
    
    N N = new N();
    
    public void crearEditorial() {
        
        try {
            System.out.println("Ingrese el Nombre de la Editorial");
            edit.setNombre(leer.next());
            edit.setAlta(true); 
            
            System.out.println("creando id");
            
            em.getTransaction().begin();
            em.persist(edit);
            em.getTransaction().commit();
            
        } catch (Exception e) {
        }
    }
    
    public Editorial buscarEditorial(long Id){
try{
       edit = em.find(Editorial.class, Id);
        System.out.println(""+edit.getNombre());
}catch(Exception ex){
    System.out.println("ID no encontrada");
}
        return edit;
    }
    
    public void modificarEditorial(long Id){
   try{ 
    edit = em.find(Editorial.class, Id);
    System.out.println("Ingrese el nuevo nombre para la editorial: "+edit.getNombre());
    edit.setNombre(leer.next());
    
            em.getTransaction().begin();
            em.persist(edit);
            em.getTransaction().commit();
    
    System.out.println("nombre cambiado");    
    }catch(Exception ex){
         System.out.println("Editorial no encontrada");
     }
    }
    
    public Editorial listPorNombre(String nombre){
        try{
        List<Editorial> edits = em.createQuery("SELECT n FROM Editorial n WHERE n.nombre LIKE :nombre")
                .setParameter("nombre", "%"+nombre+"%").getResultList();
    
            for(Editorial e:edits){
                if(e == null){
                edit = null; 
                N.setN(0);
                }else{
                edit = e;
                N.setN(1);
                break;
                }} 
        }catch(Exception ex){
            System.out.println("404 Editorial Not Found");
        }    
                if(N.getN() == 1){
                    return edit;   
                }else{
                    return null;                
                }
    }   
    
    public Editorial baja(Long id){
    try{    
        edit = em.find(Editorial.class, id);
        
        edit.setAlta(false);
        
            em.getTransaction().begin();
            em.persist(edit);
            em.getTransaction().commit();
        
        System.out.println("La editorial "+edit.getNombre()+" a sido dada de baja");
     }catch(Exception ex){
         System.out.println("Editorial no encontrada");
     }  
        return edit;
    }
    
    public Editorial borrarE(Long id){
     try{
     edit = em.find(Editorial.class, id);
      
            em.getTransaction().begin();            
            em.persist(edit);
            em.remove(edit);
            em.getTransaction().commit();
            
       System.out.println("La editorial "+edit.getNombre()+" a sido borrado");
     }catch(Exception ex){
         System.out.println("Editorial no encontrada");
     }
      return edit; 
       
   }
    
}
