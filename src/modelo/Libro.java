package modelo;

import java.io.Serializable;
import java.lang.reflect.Field;

public class Libro implements Serializable, Comparable<Libro> {

	private static final long serialVersionUID = 9009726371390821461L;

	private String titulo;
	private String autor;
	private int tema;
	private String iSBN;
	private int numPaginas;
	private boolean[] formato = new boolean[4];
	private boolean estado;
	private int unidades;
	private String editorial;
/*
	public Libro() {
		Field[] asd = this.getClass().getDeclaredFields();
		for (int i = 0; i < asd.length; i++) {
			System.out.println(asd[i].toString());
		}
		try {
			asd[1].set(asd[1].getType(), "titulito");
			asd[1].
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("titulo:"+this.titulo);
	}
*/
	public String getISBN() {
		return iSBN;
	}

	public void setISBN(String iSBN) {
		assert (iSBN != null && !iSBN.isEmpty());
		this.iSBN = iSBN;
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

	public int getTema() {
		return tema;
	}

	public void setTema(int tema) {
		this.tema = tema;
	}

	public int getNumPaginas() {
		return numPaginas;
	}

	public void setNumPaginas(int numPaginas) {
		this.numPaginas = numPaginas;
	}

	public boolean getFormato(int posicion) {
		return formato[posicion];
	}

	public void setFormato(int posicion, boolean formato) {
		this.formato[posicion] = formato;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
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
		return this.iSBN.compareTo(o.iSBN);
	}

	@Override
	public boolean equals(Object obj) {
		assert (obj != null);
		Libro libro = (Libro) obj;
		boolean retorno = super.equals(obj);
		if (!retorno) {
			retorno = this.iSBN.equals(libro.iSBN);
		}
		return retorno;

	}

	@Override
	public int hashCode() {
		return this.iSBN.hashCode();
	}

}
