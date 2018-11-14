package modelo;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;

public class Libro implements Serializable, Comparable<Libro> {

	private static final long serialVersionUID = 9009726371390821461L;

	private String isbn;
	private String titulo;
	private String autor;
	private String editorial;
	private int numPaginas;
	private String tema;
	private int unidades;
	private int estado;
	private String formato;

	public Libro() {
	}

	/**
	 * 
	 * -Funcionamiento del for
	 * En el atributo i insertamos el objeto(que es un parametro) que esta
	 * guardado en el map indexado a la clave con el nombre del atributo i
	 * (este necesita ser igual que el nombre de su columna en la bbdd)
	 * 
	 * @param parametros esto es un HashMap con los datos del libro que hemos extraido de la bbdd, y los nombres de los campos en los que va cada uno 
	 */
	public Libro(HashMap<String, Object> parametros) {
		Field[] atributos = this.getClass().getDeclaredFields();
		for (int i = 0; i < atributos.length; i++) {
			if (parametros.get(atributos[i].getName()) != null) {

				try {
					atributos[i].set(this, parametros.get(atributos[i].getName()));
				} catch (IllegalArgumentException | IllegalAccessException e) {
				}
			} else {
				System.out.println(" no esta en la bbdd");
			}
		}
	}

	public String getISBN() {
		return this.isbn;
	}

	public void setISBN(String iSBN) {
		assert (iSBN != null && !iSBN.isEmpty());
		this.isbn = iSBN;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		assert (titulo != null && !titulo.isEmpty());
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		assert (autor != null && !autor.isEmpty());
		this.autor = autor;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		assert (tema != null && !tema.isEmpty());
		this.tema = tema;
	}

	public int getNumPaginas() {
		return numPaginas;
	}

	public void setNumPaginas(int numPaginas) {
		this.numPaginas = numPaginas;
	}

	public String getFormato() {
		return this.formato;
	}

	public void setFormato(String formato) {
		assert (formato != null && !formato.isEmpty());
		this.formato = formato;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getUnidades() {
		return this.unidades;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		assert (editorial != null && !editorial.isEmpty());
		this.editorial = editorial;
	}

	@Override
	public int compareTo(Libro o) {
		assert (o != null);
		return this.isbn.compareTo(o.isbn);
	}

	@Override
	public boolean equals(Object obj) {
		assert (obj != null);
		Libro libro = (Libro) obj;
		boolean retorno = super.equals(obj);
		if (!retorno) {
			retorno = this.isbn.equals(libro.isbn);
		}
		return retorno;

	}

	@Override
	public int hashCode() {
		return this.isbn.hashCode();
	}

}
