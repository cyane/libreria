package modelo;

import java.io.Serializable;

import controlador.Indexable;

public class Libro implements Serializable, Indexable<String>, Comparable<Libro>{
	
	//Propiedades
	private String titulo;
	private String autor;
	private int tema;
	private String iSBN;
	private int numPaginas;
	private boolean[] formato=new boolean[4];
	private boolean estado;
	private int unidades;
	private String editorial;

	public Libro(){
		this.unidades=1;
	}
	//GETTERS AND SETTERS
	
	public String getISBN() {
		return iSBN;
	}//getisbn
	public void setISBN(String iSBN) {
		this.iSBN = iSBN;
	}//setisbn
	
	public String getTitulo() {
		return titulo;
	}//gettitulo

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}//setTitulo
	public String getAutor() {
		return autor;
	}//getAutor
	public void setAutor(String autor) {
		this.autor = autor;
	}//setAutor
	public int getTema() {
		return tema;
	}//getTema
	public void setTema(int tema) {
		this.tema = tema;
	}//setTema
	public int getNumPaginas() {
		return numPaginas;
	}//getNumPaginas
	public void setNumPaginas(int numPaginas) {
		this.numPaginas = numPaginas;
	}//setPaginas
	public boolean getFormato(int posicion) {
		return formato[posicion];
	}//getFormato
	public void setFormato(int posicion,boolean formato) {
		this.formato[posicion] = formato;
	}//setFormato
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public boolean getEstado() {
		return estado;
	}//getEstado
	public void setEstado(boolean estado) {
		this.estado = estado;
	}//setEstado
	public void aumentarUnidades(int unidades) {
		this.unidades+=unidades;
	}
	public int getUnidades(){
		return this.unidades;
	}

	public void reducirUnidades(int unidades) {
		this.unidades-=unidades;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	@Override
	public String getKey() {
		return this.iSBN;
	}

	@Override
	public int compareTo(Libro o) {
		return this.iSBN.compareTo(o.iSBN);
	}
	
	@Override
	public boolean equals(Object obj) {
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
}//Libreria
