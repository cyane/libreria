package controlador;

public enum Mensaje {
	LIBRO_REPETIDO("El libro ya existe"),
	ISBN_MAL_ESCRITO("ISBN incorrecto"),
	TITULO_MAL_ESCRITO("Titulo mal escrito"),
	NPAG_MAL_ESCRITO("Número de Paginas incorrecto"),
	AUTOR_MAL_ESCRITO("Autor mal escrito"),
	BAJA_COMPLETA_ELIMINAR("Libro eliminado"),
	BAJA_COMPLETA_DISMINUIR("Se ha quitado un ejemplar"),
	ALTA_COMPLETA("Libro creado y añadido"),
	MODIFICACION_GUARDADA("Cambios guardados"),
	MODIFICACION_PREPARADA("Campos editables, modifique"),
	LIBRO_NUEVO("Introduzca el ISBN del libro"),
	MOSTRAR_LIBRO("Mostrando datos del libro"),
	ISBN_NO_EXISTE_ADD_LIBRO("Introduca los datos del libro"),
	CAMPOS_VACIOS("Hay campos vacios"),
	ADD_UNIDADES("Unidades añadidas"), 
	LIBROS_INSUFICIENTES("No hay tantos libros en stock"), 
	SIN_UNIDADES("No quedan unidades"), 
	CAMPOS_ERRONEOS("campos erroneos"), 
	EJEMPLARES_MAL_ESCRITO("Ejemplares mal escritos"), 
	EDITORIAL_MAL_ESCRITO("Editorial mal escrito");
	
	
	
	
	
	
	
    private final String cadena;

    Mensaje(String mensaje) {
        this.cadena = mensaje;
    }

    public String getMensaje() {
        return cadena;
    }
	
}
