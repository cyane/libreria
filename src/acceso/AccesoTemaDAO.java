package acceso;

import java.awt.HeadlessException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.sql.rowset.CachedRowSet;
import javax.swing.JOptionPane;

import modelo.Libro;
import modelo.Tema;

public class AccesoTemaDAO {

	public Vector cargarTemasLibro() {
		CachedRowSet cachedRowSet = new AccesoBBDD().executeQuery(UtilSQL.sqlObtener(Tema.class));
		Vector temas = new Vector<String>();
		try {
			do {
				temas.addElement(cachedRowSet.getString("nombre"));
			} while (cachedRowSet.next());
		} catch (SQLException e) {
			System.err.println("excepcion cargarTemasLibro" + this.getClass().getSimpleName());
		}
		return temas;
	}

	public void insertar(String nombre) {
		assert (nombre != null && !nombre.isEmpty());
		try {
			if (!new AccesoBBDD().executeUpdate(UtilSQL.sqlInsertar(new Tema(nombre)))) {
				JOptionPane.showMessageDialog(null, "El tema introducido ya existe", "Fallo al insertar", 2, null);
			}
		} catch (HeadlessException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.err.println("excepcion insertar" + this.getClass().getSimpleName());
		}
		cargarTemasLibro();
	}

	public boolean borrarTema(String nombre) {
		assert (nombre != null && !nombre.isEmpty());
		if (comprobarTemaNoUsado(nombre)) {
			new AccesoBBDD().executeUpdate(UtilSQL.sqlBorrar(Tema.class, nombre));
			cargarTemasLibro();
			return true;
		}
		return false;

	}

	private boolean comprobarTemaNoUsado(String nombre) {
		List<Libro> listaLibros = new AccesoLibroDAO().getListaLibros();
		for (Libro libro : listaLibros) {
			if (libro.getTema().equals(nombre)) {
				return false;
			}
		}
		return true;
	}

	public void updateTema(String nombreAntiguo, String nombreNuevo) {
		assert (nombreAntiguo != null && !nombreAntiguo.isEmpty() && nombreNuevo != null && !nombreNuevo.isEmpty());
		try {
			new AccesoBBDD().executeUpdate(UtilSQL.sqlModificar(nombreAntiguo, new Tema(nombreNuevo)));
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.err.println("excepcion update tema" + this.getClass().getSimpleName());
		}
		cargarTemasLibro();

	}
}
