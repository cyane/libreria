package acceso;

import modelo.Estanteria;
import modelo.Libro;

public class TestPruebaBBDD {

	public static void main(String[] args) {
		
		Estanteria estanteria = new Estanteria();
		Libro libro= new Libro();

		libro.setISBN("00963984723");
		libro.setTitulo("titulo");
		libro.setAutor("autor");
		libro.setEditorial("editorial");
		libro.setNumPaginas(3);
		libro.setTema("Aventura");
		libro.setUnidades(6);
		libro.setEstado(0);
		libro.setFormato("Grapado");
		estanteria.insertar(libro);
		libro = estanteria.buscarLibro(libro.getISBN());
		System.out.println(libro.getUnidades());
		estanteria.aumentarUnidades(libro.getISBN(), 11);
		libro = estanteria.buscarLibro(libro.getISBN());
		System.out.println(libro.getUnidades());
		estanteria.borrarLibro(libro.getISBN());

	}

}
