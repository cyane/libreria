package acceso;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.CachedRowSet;

import modelo.Libro;

public class AccesoLibroDAO {

	private ArrayList<Libro> libros;

	public AccesoLibroDAO() {
		this.libros = new ArrayList<>();
	}

	public boolean insertar(Libro libro) {
		assert (libro != null);
		int estado = 0;
		if (libro.getEstado() > 0) {
			estado = 1;
		}
		libro.setEstado(estado);
		try {
			return new AccesoBBDD().executeUpdate(UtilSQL.sqlInsertar(libro));
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e){
			System.err.println("excepcion insertar libro"+this.getClass().getSimpleName());
		}
		return false;
	}

	public boolean borrarLibro(String isbn) {
		assert (isbn != null && !isbn.isEmpty());
		return new AccesoBBDD().executeUpdate(UtilSQL.sqlBorrar(Libro.class, isbn));
	}

	public Libro buscarLibro(String isbn) {
		assert (isbn != null && !isbn.isEmpty());
		Libro libro = null;
		Map<String, Object> datosLibro = null;
		try {
			datosLibro = new AccesoBBDD().executeQuerySearchVersionDos(UtilSQL.sqlBuscar(Libro.class, isbn));
		} catch (IllegalArgumentException  | SecurityException | SQLException e) {
			System.err.println("excepcion buscarLibro"+this.getClass().getSimpleName());
		}
		if (datosLibro != null) {
			libro = new Libro(datosLibro);
		}
		return libro;
	}

	public boolean libroRepetido(String isbn) {
		assert (isbn != null && !isbn.isEmpty());
		return this.buscarLibro(isbn) != null;
	}

	public void cambiarUnidades(String isbn, int unidades) {
		assert (isbn != null && !isbn.isEmpty());
		Libro libroTemp = this.buscarLibro(isbn);
		libroTemp.setUnidades(libroTemp.getUnidades()+unidades);
		try {
			new AccesoBBDD().executeUpdate(UtilSQL.sqlModificar(isbn, libroTemp));
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.err.println("excepcion cambiar unidades"+this.getClass().getSimpleName());
		}
	}

	public List<Libro> getListaLibros() {
		this.cargarListaLibros();
		return this.libros;
	}

	private void cargarListaLibros() {
		CachedRowSet cachedRowSet = new AccesoBBDD().executeQuery(UtilSQL.sqlObtener(Libro.class));	
		try {
			if (cachedRowSet.size() > 0) {
				do {
					this.libros.add(new Libro(new AccesoBBDD().crearLibroDesdeResultSetVersionDos(cachedRowSet)));
				} while (cachedRowSet.next());
			}
		} catch (SQLException | IllegalArgumentException | SecurityException e) {
			System.err.println("excepcion cargarListaLibros"+this.getClass().getSimpleName());
		}
	}

}
