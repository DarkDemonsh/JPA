package libreria.Servicios;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import libreria.Entidad.Autor;
import libreria.Entidad.N;


public class ServicioAutor {

    EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    Autor autor = new Autor();

    N N = new N();
    
    public void crearAutor() {
 
        try {
            System.out.println("Ingrese el Nombre del Autor");
            autor.setNombre(leer.next());
            autor.setAlta(true);
            
            System.out.println("creando id");
                        
            em.getTransaction().begin();
            em.persist(autor);
            em.getTransaction().commit();
            
        } catch (Exception e) {
        }
    }
    
    public Autor buscarAutor(long Id){
        try{  
        autor = em.find(Autor.class, Id);
        System.out.println(""+autor.getNombre());
            }catch(Exception ex){
        System.out.println("ID no encontrada");
    }
        return autor;
    }

    public void modificarAutor(long Id){ 
        try{
    autor = em.find(Autor.class, Id);

    System.out.println("Ingrese el nuevo nombre para el autor: "+autor.getNombre());
    autor.setNombre(leer.next());
        
            em.getTransaction().begin();
            em.persist(autor);
            em.getTransaction().commit();
        }catch(Exception ex){
        System.out.println("autor no encontrado");
    }
    
    }
    
    public Autor listPorNombre(String nombre){
        try{
        List<Autor> auto = em.createQuery("SELECT n FROM Autor n WHERE n.nombre LIKE :nombre")
                .setParameter("nombre", "%"+nombre+"%").getResultList();
            for(Autor a:auto){
                if(a == null){
                autor = null; 
                N.setN(0);
                }else{
                autor = a;
                N.setN(1);
                break;
                }} 
        }catch(Exception ex){
            System.out.println("404 Autor Not Found");
        }           
                if(N.getN() == 1){
                    return autor;   
                }else{
                    return null;                
                }
    }
    
    public Autor baja(Long id){
        try{
        autor = em.find(Autor.class, id);
        
        autor.setAlta(false);
        System.out.println("El autor "+autor.getNombre()+" a sido dada de baja");
        
            em.getTransaction().begin();
            em.persist(autor);
            em.getTransaction().commit();
            }catch(Exception ex){
        System.out.println("autor no encontrado");
    }
        return autor;
    }
        
    public Autor borrarA(Long id){
    try{ 
    autor = em.find(Autor.class, id);
     
            em.getTransaction().begin();            
            em.persist(autor);
            em.remove(autor);
            em.getTransaction().commit();
            
       System.out.println("El autor "+autor.getNombre()+" a sido borrado");
    }catch(Exception ex){
        System.out.println("autor no encontrado");
    }
      return autor; 
       
   }
    
}