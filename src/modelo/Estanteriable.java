package modelo;

public interface Estanteriable {

	public boolean insertarLibro(Libro libro);
	
	public boolean borrarLibro(String isbn);
	
	public Libro buscarLibro(String isbn);
	
}