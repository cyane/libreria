package modelo;

import java.io.File;
import java.util.ArrayList;
import controlador.AlmacenIndice;
import utiles.Utiles;

public class Estanteria implements Estanteriable {

	private ArrayList<Libro> libros;

	public Estanteria() {
		this.iniciarFicheros();
		this.libros = new ArrayList<>();
	}

	private void iniciarFicheros() {
		if (!Utiles.comprobarExiste(Utiles.PATH_LIBROS_DATOS)) {
			new File(Utiles.PATH_LIBROS_DATOS);
		}
		if (!Utiles.comprobarExiste(Utiles.PATH_LIBROS_INDICE)) {
			new File(Utiles.PATH_LIBROS_INDICE);
		}
	}

	public boolean insertarLibro(Libro libro) {
		return new AlmacenIndice<>(Utiles.PATH_LIBROS_INDICE, Utiles.PATH_LIBROS_DATOS).grabar(libro, libro.getISBN());
	}

	public boolean borrarLibro(String isbn) {
		return new AlmacenIndice<>(Utiles.PATH_LIBROS_INDICE, Utiles.PATH_LIBROS_DATOS).borrar(isbn);
	}

	public Libro buscarLibro(String isbn) {
		return (Libro) new AlmacenIndice<>(Utiles.PATH_LIBROS_INDICE, Utiles.PATH_LIBROS_DATOS).obtener(isbn);
	}

	public boolean libroRepetido(String isbn) {
		return this.buscarLibro(isbn) != null;
	}

	public void aumentarUnidades(String isbn, int unidades) {
		Libro libro = buscarLibro(isbn);
		this.borrarLibro(isbn);
		libro.aumentarUnidades(unidades);
		this.insertarLibro(libro);
	}

	public void decrementarEjemplares(String isbn, int unidades) {
		this.aumentarUnidades(isbn, -unidades);
	}

	public ArrayList<Libro> getListaLibros() {
		this.cargarListaLibros();
		return this.libros;
	}

	private void cargarListaLibros() {
		ArrayList<Libro> coleccionIsbn = new AlmacenIndice(Utiles.PATH_LIBROS_INDICE, Utiles.PATH_LIBROS_DATOS).getKeyList();
		for (Object object : coleccionIsbn) {
			this.libros.add(this.buscarLibro(object.toString()));
		}
	}

}
