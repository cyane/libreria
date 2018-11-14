package modelo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.sql.rowset.CachedRowSet;
import javax.swing.JOptionPane;

import acceso.AccesoBBDD;

public class Estanteria {

	private ArrayList<Libro> libros;

	public Estanteria() {
		this.libros = new ArrayList<>();
	}

	public boolean insertar(Libro libro) {
		assert (libro != null);
		int estado = 0;
		if (libro.getEstado() > 0) {
			estado = 1;
		}
		return new AccesoBBDD().executeUpdate(getSQLinsertarLibro(libro, estado));
	}

	public String getSQLinsertarLibro(Libro libro, int estado) {
		return "INSERT INTO `libreria`.`libro` (`isbn`, `titulo`, `autor`, `editorial`, `numPaginas`,`tema`,`unidades`,`estado`,`formato`) "
				+ "VALUES ('" + libro.getISBN() + "', '" + libro.getTitulo() + "', '" + libro.getAutor() + "', '"
				+ libro.getEditorial() + "', '" + libro.getNumPaginas() + "'," + " '" + libro.getTema() + "', '"
				+ libro.getUnidades() + "', '" + estado + "', '" + libro.getFormato() + "');";
	}

	public boolean borrarLibro(String isbn) {
		assert (isbn != null && !isbn.isEmpty());
		String sql = "DELETE FROM `libreria`.`libro` WHERE  `isbn`='" + isbn + "';";
		return new AccesoBBDD().executeUpdate(sql);
	}

	public Libro buscarLibro(String isbn) {
		assert (isbn != null && !isbn.isEmpty());
		Libro libro = null;
		HashMap<String, Object> datosLibro = null;
		try {
			datosLibro = new AccesoBBDD().executeQuerySearchVersionDos(getSQLbuscarLibro(isbn));
		} catch (IllegalArgumentException | IllegalAccessException | SecurityException | SQLException e) {
			e.printStackTrace();
		}
		if (datosLibro != null) {
			libro = new Libro(datosLibro);
		}
		return libro;
	}

	public String getSQLbuscarLibro(String isbn) {
		return "SELECT `isbn`, `titulo`, `autor`, `editorial`, `numPaginas`, `tema`, `unidades`, `estado`, `formato` FROM `libreria`.`libro` WHERE  `isbn`='"
				+ isbn + "';";
	}


	public boolean libroRepetido(String isbn) {
		assert (isbn != null && !isbn.isEmpty());
		return this.buscarLibro(isbn) != null;
	}

	public void aumentarUnidades(String isbn, int unidades) {
		assert (isbn != null && !isbn.isEmpty());
		new AccesoBBDD().executeUpdate(getSQLaumentarUnidades(isbn, unidades));
	}

	public String getSQLaumentarUnidades(String isbn, int unidades) {
		return "UPDATE `libreria`.`libro` SET `unidades`=  `unidades` + '" + unidades + "' WHERE  `isbn`='" + isbn
				+ "'";
	}

	public void decrementarEjemplares(String isbn, int unidades) {
		assert (isbn != null && !isbn.isEmpty());
		this.aumentarUnidades(isbn, -unidades);
	}

	public ArrayList<Libro> getListaLibros() {
		this.cargarListaLibros();
		return this.libros;
	}

	private void cargarListaLibros() {
		CachedRowSet cachedRowSet = new AccesoBBDD().executeQuery(getSQLlistaLibros());
		try {
			do {
				this.libros.add(this.buscarLibro(cachedRowSet.getString("isbn")));
			} while (cachedRowSet.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getSQLlistaLibros() {
		return "select isbn from libro where 1";
	}

	public Vector cargarTemasLibro() {
		CachedRowSet cachedRowSet = new AccesoBBDD().executeQuery(getSQLlistaTemas());
		Vector temas = new Vector<String>();
		try {
			do {
				temas.addElement(cachedRowSet.getString("nombre"));
			} while (cachedRowSet.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temas;
	}

	public String getSQLlistaTemas() {
		return "select nombre from temas where 1";
	}

	public void insertar(String nombre) {
		assert (nombre != null && !nombre.isEmpty());
		if(!new AccesoBBDD().executeUpdate(getSQLinsertarTema(nombre))){
			JOptionPane.showMessageDialog(null, "El tema introducido ya existe", "Fallo al insertar", 2, null);
		}
		cargarTemasLibro();
	}

	public String getSQLinsertarTema(String nombre) {
		return "INSERT INTO `libreria`.`temas` (`nombre`) VALUES ('" + nombre + "');";
	}

	public void borrarTema(String nombre) {
		assert (nombre != null && !nombre.isEmpty());
		new AccesoBBDD().executeUpdate(getSQLborrarTema(nombre));
		cargarTemasLibro();

	}

	public String getSQLborrarTema(String nombre) {
		return "DELETE FROM `libreria`.`temas` WHERE  `nombre`='" + nombre + "';";
	}

}
