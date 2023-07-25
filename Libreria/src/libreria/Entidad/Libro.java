package libreria.Entidad;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Libro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Libro)) {
            return false;
        }
        Libro other = (Libro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "libreria.Entidad.Libro[ id=" + id + " ]";
    }
    
    private String titulo;
    private boolean alta;
    private int anio;
    private int ejemplar;
    private int ejprestado;
    private int ejrestantes;
    private Editorial ed;
    private Autor autor;
    private long isbn;

    public Libro() {
    }

    public Libro(Long id, String titulo, boolean alta, int anio, int ejemplar, int ejprestado, int ejrestantes, Editorial ed, Autor autor, long isbn) {
        this.id = id;
        this.titulo = titulo;
        this.alta = alta;
        this.anio = anio;
        this.ejemplar = ejemplar;
        this.ejprestado = ejprestado;
        this.ejrestantes = ejrestantes;
        this.ed = ed;
        this.autor = autor;
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isAlta() {
        return alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getEjemplar() {
        return ejemplar;
    }

    public void setEjemplar(int ejemplar) {
        this.ejemplar = ejemplar;
    }

    public int getEjprestado() {
        return ejprestado;
    }

    public void setEjprestado(int ejprestado) {
        this.ejprestado = ejprestado;
    }

    public int getEjrestantes() {
        return ejrestantes;
    }

    public void setEjrestantes(int ejrestantes) {
        this.ejrestantes = ejrestantes;
    }

    public Editorial getEd() {
        return ed;
    }

    public void setEd(Editorial ed) {
        this.ed = ed;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }
    
    
    
}
