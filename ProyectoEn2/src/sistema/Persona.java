package sistema;

public class Persona {
	private int idPersona;
	private String nombre;
	private String apellido;
	
	public Persona() {
		
		
	}
	public Persona (String nombre , String apellido) {
		this.nombre=nombre;
		this.apellido=apellido;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getId() {
		return idPersona;
	}
	public void setId(int idPersona) {
		this.idPersona = idPersona;
	}
	
	

}
