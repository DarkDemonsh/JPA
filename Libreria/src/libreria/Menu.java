package libreria;

import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.Servicios.ServicioAutor;
import libreria.Servicios.ServicioEditorial;
import libreria.Servicios.ServicioLibro;
import libreria.Entidad.Autor;
import libreria.Entidad.Libro;
import libreria.Entidad.Editorial;

public class Menu {
    
    Scanner leer = new Scanner(System.in);
        
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaPU");
    EntityManager em = emf.createEntityManager();
    
    Autor a = new Autor();
    Editorial edit = new Editorial();
    Libro l = new Libro();
    
    ServicioEditorial es = new ServicioEditorial();
    ServicioAutor as = new ServicioAutor();
    ServicioLibro ls = new ServicioLibro();
       
    boolean menu = true;
    
public void menuLibreria(){    
        do{
            System.out.println("\n");
            System.out.println("1. Agregar autor, editorial, o libro.");
            System.out.println("2. Dar de alta/baja o editar autor, editorial, o libro.");
            System.out.println("3. Modificar Autor, Editorial por id");
            System.out.println("4. Buscar Editorial, Autor por ID");
            System.out.println("5. Buscar libro por ISBN");
            System.out.println("6. Prestar libro");
            System.out.println("7. Borrar Autor/Editorial/Libro");

            System.out.println("9. Salir");

            int opcion = leer.nextInt();

            switch (opcion) {
///////////////////////////////////////////////||AGREGAR||//////////////////////////////////////////////////////
                case 1:
                    System.out.println("1. Autor");
                    System.out.println("2. Editorial");
                    System.out.println("3. Libro");
                    opcion = leer.nextInt();
                    switch (opcion) {
                        case 1:
                            as.crearAutor();
                            break;
                        case 2:
                            es.crearEditorial();
                            break;
                        case 3:
                            ls.crearLibro();
                            break;
                        default:
                            System.out.println("Opción no reconocida.");
                    }
                    break;
////////////////////////////////////////////||DAR DE BAJA||//////////////////////////////////////////////////////////
                case 2:
                    System.out.println("1. Autor");
                    System.out.println("2. Editorial");
                    System.out.println("3. Libro");
                    opcion = leer.nextInt();
                    switch (opcion) {
                        case 1:
                            System.out.println("ingrese la id correspondiente");
                            as.baja(leer.nextLong());
                            break;
                        case 2:
                            System.out.println("ingrese la id correspondiente");                            
                            es.baja(leer.nextLong());
                            break;
                        case 3:
                            System.out.println("Ingrese el ID del libro");
                            ls.bajaLibro(leer.nextLong());
                            break;
                        default:
                            System.out.println("Opción no reconocida.");
                    }
                    break;
/////////////////////////////////////////////||MODIFICAR||///////////////////////////////////////////////////////////////////
                case 3:
                    System.out.println("1.Modificar Autor");
                    System.out.println("2.Modificar Editorial");
                    opcion = leer.nextInt();
                    switch(opcion){        
                case 1: 
                    System.out.println("Ingrese la id del autor");
                    as.modificarAutor(leer.nextLong());
                    break;
                case 2:
                    System.out.println("Ingresar la id de la editorial");
                    es.modificarEditorial(leer.nextLong());
                default:
                    System.out.println("Opción no reconocida.");
                    }
                    break;
/////////////////////////////////////////////////||BUSCAR||////////////////////////////////////////////////////////////////
                case 4:
                    System.out.println("1. Buscar Autor");
                    System.out.println("2. Buscar Editorial");
                    opcion = leer.nextInt();
                switch(opcion){
                    case 1:
                    System.out.println("ingrese la id de la editorial");
                    as.buscarAutor(leer.nextLong());
                    break;
                    case 2:
                    System.out.println("ingrese la id del autor");
                    es.buscarEditorial(leer.nextLong());
                    break;
                default:
                    System.out.println("Opción no reconocida.");
                }
                break;
/////////////////////////////////////////////////////||BUSCAR||////////////////////////////////////////////////////////////
                case 5:
                    System.out.println("ingrese el isbn del libro");
                    ls.buscarLibro(leer.nextLong());
                    break;
//////////////////////////////////////////////////////||PRESTAR||///////////////////////////////////////////////////////////
                case 6:
                    System.out.println("ingrese el id del libro");
                    ls.prestar(leer.nextLong());
                    break;
////////////////////////////////////////////////////||BORRAR||////////////////////////////////////////////////////////////////
                case 7:
                    System.out.println("///RECUERDE QUE PARA BORRAR UN AUTOR O EDITORIAL DEBE BORRAR O CAMBIAR TODOS LOS LIBROS A LOS QUE ESTEN VINCULADOS EN LA BASE DE DATOS///");
                    System.out.println("1. borrar autor");
                    System.out.println("2. borrar editorial");
                    System.out.println("3. borrar libro");
                    opcion = leer.nextInt();
                    switch(opcion){
                        case 1: 
                            System.out.println("Ingrese la id del autor");
                            as.borrarA(leer.nextLong());
                            break;
                        case 2:
                            System.out.println("Ingrese la id de la editorial");
                            es.borrarE(leer.nextLong());
                            break;
                        case 3: 
                            System.out.println("Ingrese la id del libro");
                            ls.borrarLibro(leer.nextLong());
                            break;
                        default:
                            System.out.println("Opción no reconocida.");
                    }
                    break;
//////////////////////////////////////////////////////||SALIR||////////////////////////////////////////////////////////////////
                case 9:
                    menu = false;
                    System.out.println("Gracias por usar el sistema de creacion de libros!");
                    break;
                default:
                    System.out.println("Opción no reconocida. Por favor, elige una opción del menú.");
            }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
        }while (menu);
}
    }
