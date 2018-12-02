package acceso;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import modelo.Libro;

public class Fachada {

	
	AccesoLibroDAO libroDAO = new AccesoLibroDAO();
	AccesoTemaDAO temaDAO = new AccesoTemaDAO();

	public boolean insertar(Libro libro) {
		return this.libroDAO.insertar(libro);
	}

	public boolean borrarLibro(String isbn) {
		return libroDAO.borrarLibro(isbn);
	}

	public Libro buscarLibro(String isbn) {
		return libroDAO.buscarLibro(isbn);
	}

	public List<Libro> getListaLibros() {
		return libroDAO.getListaLibros();
	}

	public Vector cargarTemasLibro() {
		return temaDAO.cargarTemasLibro();
	}

	public boolean libroRepetido(String isbn) {
		return libroDAO.libroRepetido(isbn);
	}

	public void aumentarUnidades(String isbn, int unidades) {
		libroDAO.cambiarUnidades(isbn, unidades);
	}

	public void decrementarEjemplares(String isbn, int unidades) {
		libroDAO.cambiarUnidades(isbn, -unidades);
	}

	public void insertar(String nombre) {
		temaDAO.insertar(nombre);
	}

	public boolean borrarTema(String nombre) {
		return temaDAO.borrarTema(nombre);
	}

	public void updateTema(String nombreAntiguo, String nombreNuevo) {
		temaDAO.updateTema(nombreAntiguo, nombreNuevo);
	}
	
	
	
}
