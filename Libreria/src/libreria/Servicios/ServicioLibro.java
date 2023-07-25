package libreria.Servicios;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import libreria.Entidad.Autor;
import libreria.Entidad.Editorial;
import libreria.Entidad.Libro;

public class ServicioLibro {
    
    Scanner leer = new Scanner(System.in);
    EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
    
    public void crearLibro(){ 
        Libro libro = new Libro();
        ServicioAutor as = new ServicioAutor();
        Autor a = new Autor();
        Editorial ed = new Editorial();
        ServicioEditorial es = new ServicioEditorial();
        try {
            System.out.println("Ingrese ISBN");
            Long isbn = leer.nextLong();

            if (isbn == null) {
                throw new Exception("Debe indicar el isbn");
            }
            if (buscarLibro(isbn) != null) {
                throw new Exception("Ya existe un libro con ese isbn " + isbn);
            }
            libro.setIsbn(isbn);

            System.out.println("Ingrese titulo del libro: ");
            String titulo = leer.next();
            if (titulo == null) {
                throw new Exception("Debe indicar el titulo");
            }
            libro.setTitulo(titulo);

            System.out.println("Ingrese año de fabricacion: ");
            Integer anio = null;
            try {
                anio = leer.nextInt();
            } catch (InputMismatchException e) {
                throw new Exception("Debe ingresar un valor numérico para el año");
            }
            if (anio == null) {
                throw new Exception("Debe indicar el año");
            }
            libro.setAnio(anio);

            System.out.println("Ingrese el numero de ejemplares: ");
            Integer ejemplares = null;
            try {
                ejemplares = leer.nextInt();
            } catch (InputMismatchException e) {
                throw new Exception("Debe ingresar un valor numérico para el numero de ejemplares");
            }
            if (ejemplares == null) {
                throw new Exception("Debe indicar el numero de ejemplares");
            }
            libro.setEjemplar(ejemplares);
            libro.setEjprestado(0);
            libro.setEjrestantes(ejemplares);
            libro.setAlta(true);

            System.out.println("Ingrese nombre del autor");
            String nombreAutor = leer.next();
            
            if (nombreAutor == null) {
                throw new Exception("Debe indicar el nombre del autor");
            }
            if (as.listPorNombre(nombreAutor) == null) {
                System.out.println("autor inexistente, debe Crear Autor en la base de datos...");
                as.crearAutor();
            }else{
                System.out.println("Autor "+as.listPorNombre(nombreAutor)+" encontrado");
            }    
            
            libro.setAutor(as.listPorNombre(nombreAutor));

            System.out.println("Ingrese nombre de la Editorial");
            String nombreEditorial = leer.next();
            if (nombreEditorial == null) {
                throw new Exception("Debe indicar el nombre de la Editorial");
            }
            
            if (es.listPorNombre(nombreEditorial) == null) {
                System.out.println("editorial inexsitente debe Crear Editorial en la base de datos...");
                es.crearEditorial();
                
            }else{
                System.out.println("Editorial "+es.listPorNombre(nombreEditorial)+" encontrada");
            }
               libro.setEd(es.listPorNombre(nombreEditorial)); 


            em.getTransaction().begin();
            em.persist(libro);
            em.getTransaction().commit();
            System.out.println("Libro creado correctamente! ");
        } catch (Exception e) {
            // Mostrar el mensaje de error en la consola
            System.err.println("Error al crear el libro: " + e.getMessage());
        }
    
    }
   
    public Libro bajaLibro(Long id){
     
     Libro libro = em.find(Libro.class, id);
     try{ 
       libro.setAlta(false);
            em.getTransaction().begin();
            em.persist(libro);
            em.getTransaction().commit();
       System.out.println("El libro "+libro.getTitulo()+" a sido dado de baja");
       
                 }catch(Exception ex){
         System.out.println("Libro no encontrado");
     } 
      
      return libro;  
   }
    
    public Libro borrarLibro(Long id){
     
     Libro libro = em.find(Libro.class, id);
      try{
            em.getTransaction().begin();            
            em.persist(libro);
            em.remove(libro);
            em.getTransaction().commit();
            
       System.out.println("El libro "+libro.getTitulo()+" a sido borrado");
                 }catch(Exception ex){
         System.out.println("Libro no encontrado");
     } 
      return libro; 
       
   }

    public Libro buscarLibro(Long isbn) {

       Libro libro = em.find(Libro.class, isbn);

        return libro;
    }

    public List listaNombres(String titulo) {

        List<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :titulo")
                .setParameter("titulo", titulo).getResultList();

        return libros;
    }
    
    public Libro prestar(Long id){
        
      Libro libro = em.find(Libro.class, id); 
       try{ 
       libro.setEjprestado((libro.getEjprestado()+1));
       libro.setEjrestantes((libro.getEjrestantes()-1));
       
            em.getTransaction().begin();
            em.persist(libro);
            em.getTransaction().commit();
       
        System.out.println("El libro "+libro.getTitulo()+" fue prestado");
           }catch(Exception ex){
         System.out.println("Libro no encontrado");
     } 
      return libro;  
    }
}