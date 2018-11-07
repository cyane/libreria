package modelo;

import java.util.ArrayList;

import acceso.AccesoBBDD;

public class Estanteria {

	private ArrayList<Libro> libros;

	public Estanteria() {
		this.libros = new ArrayList<>();
	}

	public boolean insertarLibro(Libro libro) {
		assert (libro != null);
		String sql = "INSERT INTO `prueba`.`asd` (`asd`) VALUES ('8248')";
		return new AccesoBBDD().executeUpdate(sql);
	}

	public boolean borrarLibro(String isbn) {
		assert (isbn != null && !isbn.isEmpty());
		// TODO
		return true;
	}

	public Libro buscarLibro(String isbn) {
		assert (isbn != null && !isbn.isEmpty());
		// TODO
		return null;
	}

	public boolean libroRepetido(String isbn) {
		assert (isbn != null && !isbn.isEmpty());
		return this.buscarLibro(isbn) != null;
	}

	public void aumentarUnidades(String isbn, int unidades) {
		assert (isbn != null && !isbn.isEmpty());
		// TODO aumentar unidades
	}

	public void decrementarEjemplares(String isbn, int unidades) {
		assert (isbn != null && !isbn.isEmpty());
		this.aumentarUnidades(isbn, -unidades); // TODO comprobar si esto sigue tirando
	}

	public ArrayList<Libro> getListaLibros() {
		this.cargarListaLibros();
		return this.libros;
	}

	private void cargarListaLibros() {
		// TODO rellena this.libros de los libros que hay en la bbdd
	}

}
