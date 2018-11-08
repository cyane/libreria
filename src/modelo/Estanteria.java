package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.rowset.CachedRowSet;

import acceso.AccesoBBDD;

public class Estanteria {

	private ArrayList<Libro> libros;

	public Estanteria() {
		this.libros = new ArrayList<>();
	}

	public boolean insertarLibro(Libro libro) {
		assert (libro != null);
		int formato = 0;
		for (int i = 0; i < 4; i++) {
			if (libro.getFormato(i) == true) {
				formato = i;
				System.out.println(formato);
			}
		}
		int estado = 0;
		if (libro.getEstado()) {
			estado = 1;
		}
		String sql = "INSERT INTO `libreria`.`libro` (`isbn`, `titulo`, `autor`, `editorial`, `numPaginas`,`tema`,`unidades`,`estado`,`formato`) "
				+ "VALUES ('" + libro.getISBN() + "', '" + libro.getTitulo() + "', '" + libro.getAutor() + "', '"
				+ libro.getEditorial() + "', '" + libro.getNumPaginas() + "'," + " '" + libro.getTema() + "', '"
				+ libro.getUnidades() + "', '" + estado + "', '" + formato + "');";
		return new AccesoBBDD().executeUpdate(sql);
	}

	public boolean borrarLibro(String isbn) {
		assert (isbn != null && !isbn.isEmpty());
		String sql = "DELETE FROM `libreria`.`libro` WHERE  `isbn`='" + isbn + "';";
		return new AccesoBBDD().executeUpdate(sql);
	}

	public Libro buscarLibro(String isbn) {
		assert (isbn != null && !isbn.isEmpty());
		String sql = "SELECT `isbn`, `titulo`, `autor`, `editorial`, `numPaginas`, `tema`, `unidades`, `estado`, `formato` FROM `libreria`.`libro` WHERE  `isbn`='"
				+ isbn + "';";
		CachedRowSet cachedRowSet = new AccesoBBDD().executeQuery(sql);
		Libro libro = null;
		try {// TODO para cuando el isbn no exista
			if (cachedRowSet!=null) {
				libro = new Libro();
				libro.setISBN(cachedRowSet.getString("isbn"));
				libro.setTitulo(cachedRowSet.getString("titulo"));
				libro.setAutor(cachedRowSet.getString("autor"));
				libro.setEditorial(cachedRowSet.getString("editorial"));
				libro.setNumPaginas(cachedRowSet.getInt("numPaginas"));
				libro.setTema(cachedRowSet.getInt("tema"));
				libro.setUnidades(cachedRowSet.getInt("unidades"));
			}
		} catch (SQLException e) {
			libro = null;
		}
		System.out.println(libro);
		return libro;
	}

	public boolean libroRepetido(String isbn) {
		assert (isbn != null && !isbn.isEmpty());
		System.out.println("buscando");
		return this.buscarLibro(isbn) != null;
	}

	public void aumentarUnidades(String isbn, int unidades) {
		assert (isbn != null && !isbn.isEmpty());
		String sql = "UPDATE `libreria`.`libro` SET `unidades`=  `unidades` + '" + unidades + "' WHERE  `isbn`='" + isbn
				+ "'";
		new AccesoBBDD().executeUpdate(sql);
	}

	public void decrementarEjemplares(String isbn, int unidades) {
		assert (isbn != null && !isbn.isEmpty());
		this.aumentarUnidades(isbn, -unidades); // TODO comprobar si esto sigue
												// tirando
	}

	public ArrayList<Libro> getListaLibros() {
		this.cargarListaLibros();
		return this.libros;
	}

	private void cargarListaLibros() {
		String sql = "select isbn from libro where 1";
		CachedRowSet cachedRowSet = new AccesoBBDD().executeQuery(sql);
		try {
			do {
				this.libros.add(this.buscarLibro(cachedRowSet.getString(1)));
				System.out.println("libros" + this.libros);
			} while (cachedRowSet.next());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO rellena this.libros de los libros que hay en la bbdd
	}

}
