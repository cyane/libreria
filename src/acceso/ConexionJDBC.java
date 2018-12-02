package acceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ConexionJDBC {

	private String usuario;
	private String constraseña;
	private String url;
	private String driver;
	private final ResourceBundle accesoPropiedades = ResourceBundle.getBundle("Configuracion");
	
	
	private static Connection conexion = null;

	public static Connection getConexion() throws ClassNotFoundException, SQLException {
		if (conexion == null) {
			new ConexionJDBC();
		}
		return conexion;
	}

	private ConexionJDBC() throws ClassNotFoundException, SQLException {
		Runtime.getRuntime().addShutdownHook(new MiShutDownHook());
		usuario = accesoPropiedades.getString("usuario");
		constraseña = accesoPropiedades.getString("password");
		url = accesoPropiedades.getString("url");
		driver = accesoPropiedades.getString("driver");
		Class.forName(driver);
		conexion = DriverManager.getConnection(url, usuario, constraseña);
	}

	public class MiShutDownHook extends Thread {
		@Override
		public void run() {
			try {
				if(conexion != null)
				conexion.close();
			} catch (SQLException e) {
			}
		}
	}

}
