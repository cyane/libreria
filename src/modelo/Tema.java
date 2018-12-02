package modelo;

@Clave(clavePrimaria="nombre")
public class Tema {

	private String nombre;

	public Tema(String nombre) {
		this.nombre=nombre;
	}

	public String getNombre() {
		return nombre;
	}
	
	
}
