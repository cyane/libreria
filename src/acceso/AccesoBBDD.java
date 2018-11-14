package acceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import javax.sql.rowset.CachedRowSet;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.sun.rowset.CachedRowSetImpl;

public class AccesoBBDD {

	private String usuario = "root";
	private String password = "";
	private String nombreDB = "jdbc:mysql://localhost/libreria";
	private String driver = "com.mysql.jdbc.Driver";

	private static Connection conexion = null;

	public Connection abrirConexion() {
		if (conexion == null) {
			try {
				Runtime.getRuntime().addShutdownHook(new CerrarConexion());
				Class.forName(driver);
				conexion = DriverManager.getConnection(nombreDB, usuario, password);
				conexion.setAutoCommit(false);
				System.out.println("bbdd conectada");
			} catch (Exception e) {
				System.out.println("fallo al conectar");
				e.printStackTrace();
			}
		}
		return conexion;
	}

	public AccesoBBDD() {
		this.abrirConexion();
	}

	public boolean executeUpdate(String sql) {
		abrirConexion();
		int resultado = 0;
		try {
			System.out.println("lanzando update sql");
			resultado = AccesoBBDD.conexion.prepareStatement(sql).executeUpdate();
		} catch (SQLException e) {
			System.out.println("fallo en el update");
			e.printStackTrace();
		}

		return commit(resultado);
	}
	
	public HashMap<String, Object> executeQuerySearchVersionDos(String sql)
			throws IllegalArgumentException, IllegalAccessException, SecurityException, SQLException {
		abrirConexion();
		CachedRowSet cachedRowSet = null;
		System.out.println("lanzando sql");
		cachedRowSet = new CachedRowSetImpl();
		cachedRowSet.populate(AccesoBBDD.conexion.prepareStatement(sql).executeQuery());
		cachedRowSet.next();
		return crearLibroDesdeResultSetVersionDos(cachedRowSet);
	}
	
	private HashMap<String, Object> crearLibroDesdeResultSetVersionDos(ResultSet resultSet)
			throws SQLException, IllegalArgumentException, IllegalAccessException, SecurityException {
		abrirConexion();
		HashMap<String, Object> datosLibro = new HashMap<>();
		System.out.println("Leyendo de la bbdd");
		System.out.println("DATOS DEL OBJETO");
		try {
			for (int i = 1; i < resultSet.getMetaData().getColumnCount() + 1; i++) {
				System.out.println("Posicion: " + i + " | Columna: "
						+ resultSet.getMetaData().getColumnName(i).toString() + " | Dato: " + resultSet.getObject(i));
				datosLibro.put(resultSet.getMetaData().getColumnName(i).toString(), resultSet.getObject(i));
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
		return datosLibro;
	}

	public CachedRowSet executeQuery(String sql) {
		abrirConexion();
		try {
			System.out.println("lanzando sql");
			CachedRowSet cachedRowSet = new CachedRowSetImpl();
			cachedRowSet.populate(AccesoBBDD.conexion.prepareStatement(sql).executeQuery());
			cachedRowSet.next();
			return cachedRowSet;
		} catch (SQLException e) {
			System.out.println("fallo en la consulta sql");
			e.printStackTrace();
		}
		return null;
	}

	private boolean commit(int resultado) {
		if (resultado > 0) {
			try {
				conexion.commit();
			} catch (SQLException e) {
				System.out.println("fallo en el commit");
				e.printStackTrace();
			}
			return true;
		} else {
			try {
				conexion.rollback();
			} catch (SQLException e) {
				System.out.println("fallo en el rollback");
				e.printStackTrace();
			}
			return false;
		}
	}

	class CerrarConexion extends Thread {

		@Override
		public void run() {
			try {
				abrirConexion().close();
				System.out.println("... cerrando conexion");
			} catch (Exception e) {
				System.out.println("error al cerrar");
			}
		}
	}
}
