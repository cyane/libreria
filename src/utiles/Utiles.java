package utiles;

import java.io.File;

/**
 * 
 * @author fp-hermoso
 *
 */
public class Utiles {
	// rutas

	public static final String pathLibrosIndice = "./data/indice.data";
	public static final String pathLibrosDatos = "./data/libros.data";

	/**
	 * comprueba que el archivo/directorio de la ruta introducida existe
	 * 
	 * @param ruta
	 *            ruta a buscar
	 * @return true si existe, false si no
	 */
	public static boolean comprobarExiste(String ruta) {
		File archivo = new File(ruta);
		return archivo.exists();
	}
}