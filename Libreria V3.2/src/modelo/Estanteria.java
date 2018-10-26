package modelo;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import controlador.AlmacenIndice;
import utiles.Utiles;

public class Estanteria implements Estanteriable {

	private ArrayList<Libro> libros;

	public Estanteria() {
		iniciarFicheros();
		this.libros=new ArrayList<>();
	}


	private void iniciarFicheros() {
		if(!Utiles.comprobarExiste(Utiles.pathLibrosDatos)){
			File ficheroLibros = new File(Utiles.pathLibrosDatos);
		}
		if(!Utiles.comprobarExiste(Utiles.pathLibrosIndice)){
			File ficheroIndice = new File(Utiles.pathLibrosIndice);
		}
	}

	
	public  ArrayList<Libro> cargarLista(){
		 ArrayList coleccionIsbn= new AlmacenIndice(Utiles.pathLibrosIndice, Utiles.pathLibrosDatos).obtenerColeccionValues();
		 for (Object object : coleccionIsbn) {
			 this.libros.add(buscarLibro(object.toString()));
		}
		System.out.println(this.libros);
		return this.libros;
	}
	
	public boolean insertarLibro(Libro libro) {
		this.libros.add(libro);
		return new AlmacenIndice<>(Utiles.pathLibrosIndice, Utiles.pathLibrosDatos).grabar(libro,libro.getISBN());
	}

	public boolean borrarLibro(String isbn) {
		return new AlmacenIndice<>(Utiles.pathLibrosIndice, Utiles.pathLibrosDatos).borrar(buscarLibro(isbn));
	}

	public Libro buscarLibro(String isbn) {
		return (Libro) new AlmacenIndice<>(Utiles.pathLibrosIndice, Utiles.pathLibrosDatos).obtener(isbn);
	}

	public boolean libroRepetido(String isbn) {
		return buscarLibro(isbn) != null;
	}

	public void aumentarUnidades(String isbn, int unidades) {
		Libro libro = buscarLibro(isbn);
		libro.aumentarUnidades(unidades);
		borrarLibro(isbn);
		insertarLibro(libro);		
	}


	public Libro buscarLibro(int index) {
		return this.libros.get(index);
	}


	public void decrementarEjemplares(String isbn, int unidades) {
		buscarLibro(isbn).reducirUnidades(unidades);
	}

}
