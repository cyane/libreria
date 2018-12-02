package acceso;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UtilSQL {

	public final static String sqlInsertar(Object objeto)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		StringBuffer cadena = new StringBuffer("INSERT INTO `" + objeto.getClass().getSimpleName() + "` ");
		StringBuffer columnas = new StringBuffer("(");
		StringBuffer valores = new StringBuffer("(");
		for (java.util.Map.Entry<String, Method> geter : MapsDTO.obtenerGeters(objeto).entrySet()) {
			columnas.append("`" + geter.getKey() + "` , ");
			valores.append("'" + geter.getValue().invoke(objeto, null) + "' , ");
		}
		cadena.append(columnas.replace(columnas.length() - 2, columnas.length(), ") VALUES "));
		cadena.append(valores.replace(valores.length() - 2, valores.length(), ")"));
		System.out.println(cadena.toString());
		return cadena.toString();
	}

	public final static String sqlBuscar(Class clase, String nombre) {
		return "SELECT * FROM `" + "libreria`.`libro" + "` WHERE `" + MapsDTO.obtenerClave(clase) + "` = '" + nombre
				+ "'";
	}

	public final static String sqlBorrar(Class clase, String nombre) {
		return "DELETE FROM `" + clase.getSimpleName() + "` WHERE `" + MapsDTO.obtenerClave(clase) + "` = '" + nombre
				+ "'";
	}

	public final static String sqlObtener(Class clase) {
		return "SELECT * FROM `" + clase.getSimpleName() + "`";
	}

	public final static String sqlModificar(String anterior, Object objeto)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		StringBuffer cadena = new StringBuffer("UPDATE `" + objeto.getClass().getSimpleName() + "` SET");
		Field[] campos = objeto.getClass().getDeclaredFields();
		for (java.util.Map.Entry<String, Method> geter : MapsDTO.obtenerGeters(objeto).entrySet()) {
			cadena.append("`" + geter.getKey() + "` = '" + geter.getValue().invoke(objeto, null) + "' , ");
		}
		return cadena.replace(cadena.length() - 2, cadena.length(),
				" WHERE `" + MapsDTO.obtenerClave(objeto.getClass()) + "` = '" + anterior + "'").toString();
	}

}
