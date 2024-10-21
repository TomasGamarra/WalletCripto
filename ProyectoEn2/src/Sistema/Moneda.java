package Sistema;

import java.awt.Image;


// Cambio USD Y CONVERSION  debo esperar a terner la base de datos para conectarlos



public abstract class Moneda {
	private String nombre;
	private String sigla;
	private Image icono;
	
	
	public Moneda() {
		
		
	}
	
	public Moneda(String nombre, String sigla, Image icono) {
		this.nombre = nombre;
		this.sigla = sigla;
		this.icono = icono;
	}




	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	public String getSigla() {
		return sigla;
	}




	public void setSigla(String sigla) {
		this.sigla = sigla;
	}




	public Image getIcono() {
		return icono;
	}




	public void setIcono(Image icono) {
		this.icono = icono;
	}

	
	
	
	
	

	
	
	
	
	
	
}
