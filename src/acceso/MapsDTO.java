package acceso;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import modelo.Clave;


public class MapsDTO <clase>{
	
	/**
	 * Obtenemos todos los seters del objeto que se le pasa por parametros
	 * @param objeto
	 * @return la clave sera el nombre de la propiedad
	 */
	public static HashMap<String, Method> obtenerSeters(Object objeto){
		HashMap<String, Method> hashMap = new HashMap<String, Method>();
		Method method[] = objeto.getClass().getMethods();
		for (Method method2 : method) {
			if (method2.getName().startsWith("set")) {
				hashMap.put(method2.getName().substring(3).toLowerCase(), method2);
			}
		}
		return hashMap;
	}
	
	public static HashMap<String, Method> obtenerGeters(Object objeto){
		HashMap<String, Method> hashMap = new HashMap<String, Method>();
		Method method[] = objeto.getClass().getDeclaredMethods();
		for (Method method2 : method) {
			if (method2.getName().startsWith("get")) {
				hashMap.put(method2.getName().substring(3).toLowerCase(), method2);
			}
		}
		//hashMap.remove("class");
		return hashMap;
	}

	/**
	 * Busca en la clase que le pasemos por paramtero, la anotación "clave"
	 * @param clase
	 * @return Clave de la tabla 
	 */
	public static String obtenerClave(Class clase){
	 return ((Clave)clase.getDeclaredAnnotation(Clave.class)).clavePrimaria();
	}
	
}
