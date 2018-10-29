package utiles;

import java.io.File;

public class Utiles {
	
	private Utiles() {
		throw new IllegalStateException("Utility class");
	}

	public static final String PATH_LIBROS_INDICE = "./data/indice.data";

	public static final String PATH_LIBROS_DATOS = "./data/libros.data";

	public static boolean comprobarExiste(String ruta) {
		File archivo = new File(ruta);
		return archivo.exists();
	}
}