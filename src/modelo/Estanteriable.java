package modelo;

public interface Estanteriable {

	public boolean insertarLibro(Libro libro);
	
	public boolean borrarLibro(String isbn);
	
	public Libro buscarLibro(String isbn);
	
	public boolean libroRepetido(String isbn);
	
	public void aumentarUnidades(String isbn, int unidades);
	
	public void decrementarEjemplares(String isbn, int unidades) ;
	
}