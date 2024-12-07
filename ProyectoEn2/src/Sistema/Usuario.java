package Sistema;

public class Usuario {
	
	private int idUsuario; //Para acceso a la base de datos
	private String email;
	private String password;
	private boolean aceptoTerminos;
	private Persona persona;
	
	
	public Usuario(int idUsuario, String email, String password, boolean aceptoTerminos, Persona persona) {
		this.idUsuario = idUsuario;
		this.email = email;
		this.password = password;
		this.aceptoTerminos = aceptoTerminos;
		this.persona = persona;
	}

	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAceptoTerminos() {
		return aceptoTerminos;
	}
	public void setAceptoTerminos(boolean aceptoTerminos) {
		this.aceptoTerminos = aceptoTerminos;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	
	
	
}
