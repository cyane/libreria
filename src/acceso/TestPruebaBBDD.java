package acceso;

import modelo.Estanteria;
import modelo.Libro;

public class TestPruebaBBDD {

	public static void main(String[] args) {
		
		Estanteria estanteria = new Estanteria();
		Libro libro= new Libro();
		libro.setAutor("autor");
		libro.setEditorial("editorial");
		libro.setEstado(false);
		libro.setFormato(0, false);
		libro.setFormato(1, false);
		libro.setFormato(2, true);
		libro.setFormato(3, false);
		libro.setISBN("1231231231");
		libro.setNumPaginas(2);
		libro.setTema(4);
		libro.setTitulo("titulo");
		libro.setUnidades(6);
		estanteria.insertarLibro(libro);
		estanteria.buscarLibro(libro.getISBN());
		estanteria.aumentarUnidades(libro.getISBN(), 10);
		System.out.println(libro.getUnidades());
		estanteria.borrarLibro(libro.getISBN());

	}

}
