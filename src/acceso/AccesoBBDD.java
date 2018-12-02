package acceso;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.rowset.CachedRowSet;

import com.sun.rowset.CachedRowSetImpl;


public class AccesoBBDD {

	private static Connection conexion = null;

	public AccesoBBDD() {
		try {
			conexion = ConexionJDBC.getConexion();
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("fallo conexion accesoBBDD");
		}
	}

	public void iniciarTransaccion() throws SQLException {
		conexion.setAutoCommit(false);
	}

	public void aceptarTransaccion() throws SQLException {
		conexion.commit();
	}

	public void cancelarTransaccion() throws SQLException {
		conexion.rollback();
	}

	public boolean executeUpdate(String sql) {
		try {
			System.out.println("lanzando update");
			iniciarTransaccion();
			if (conexion.prepareStatement(sql).executeUpdate() > 0) {
				aceptarTransaccion();
				return true;
			}
			cancelarTransaccion();
			
		} catch (SQLException e) {
			System.err.println("fallo en el update");
		}
		return false;
	}


	public Map<String, Object> executeQuerySearchVersionDos(String sql)
			throws SQLException {
		CachedRowSet cachedRowSet = null;
		System.out.println("lanzando select");
		cachedRowSet = new CachedRowSetImpl();
		cachedRowSet.populate(AccesoBBDD.conexion.prepareStatement(sql).executeQuery());
		cachedRowSet.next();
		return crearLibroDesdeResultSetVersionDos(cachedRowSet);
	}

	public Map<String, Object> crearLibroDesdeResultSetVersionDos(ResultSet resultSet)
			throws SQLException {
		HashMap<String, Object> datosLibro = new HashMap<>();
		try {
			for (int i = 1; i < resultSet.getMetaData().getColumnCount() + 1; i++) {
				datosLibro.put(resultSet.getMetaData().getColumnName(i), resultSet.getObject(i));
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
		return datosLibro;
	}

	public CachedRowSet executeQuery(String sql) {
		try {
			System.out.println("lanzando sql");
			CachedRowSet cachedRowSet = new CachedRowSetImpl();
			cachedRowSet.populate(AccesoBBDD.conexion.prepareStatement(sql).executeQuery());
			cachedRowSet.next();
			return cachedRowSet;
		} catch (SQLException e) {
			System.err.println("fallo en la consulta sql");
		}
		return null;
	}


}
