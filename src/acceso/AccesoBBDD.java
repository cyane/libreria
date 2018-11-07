package acceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AccesoBBDD {

	private String usuario = "root";
	private String password = "";
	private String nombreDB = "jdbc:mysql://localhost/prueba";
	private String driver = "com.mysql.jdbc.Driver";

	private static Connection conexion = null;
	private Statement statement = null;

	public Connection abrirConexion() {
		if (conexion == null) {
			try {
				// Runtime.getRuntime().addShutdownHook(new CerrarConexion());
				// Class.forName(driver);
				conexion = DriverManager.getConnection(nombreDB, usuario, password);
				this.statement = conexion.createStatement();
				// conexion.setAutoCommit(false);
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
			System.out.println("lanzando sql");
			resultado = this.statement.executeUpdate(sql);
			// conexion.commit();
		} catch (SQLException e) {
			System.out.println("fallo en el update");
			e.printStackTrace();
		}
		System.out.println("resultado " + resultado);

		return resultado > 0;
	}

	/*
	 * private boolean commit(int resultado) { if (resultado > 0) { try {
	 * conexion.commit(); } catch (SQLException e) {
	 * System.out.println("fallo en el commit"); e.printStackTrace(); } return true;
	 * } else { try { conexion.rollback(); } catch (SQLException e) {
	 * System.out.println("fallo en el rollback"); e.printStackTrace(); } return
	 * false; } }
	 * 
	 * class CerrarConexion extends Thread {
	 * 
	 * @Override public void run() { try { abrirConexion().close();
	 * System.out.println("... cerrando conexion"); } catch (Exception e) {
	 * System.out.println("error al cerrar"); } } }
	 */
}
