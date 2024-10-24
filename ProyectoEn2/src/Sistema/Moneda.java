package Sistema;

import java.awt.Image;


// Cambio USD Y CONVERSION  debo esperar a terner la base de datos para conectarlos



public abstract class Moneda {
	private String nombre;
	private String sigla;
	private float precio;
	private float volatilidad;
	private String tipo;
	private float cant;
	
	
	public Moneda() {
		
		
	}
	
	public Moneda(String nombre, String sigla, float precio, float volatilidad, float cant) {
		this.nombre = nombre;
		this.sigla = sigla;
		this.precio =precio;
		this.volatilidad=volatilidad;
		this.cant=cant;
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

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public float getVolatilidad() {
		return volatilidad;
	}

	public void setVolatilidad(float volatilidad) {
		this.volatilidad = volatilidad;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public float getCant() {
		return cant;
	}

	public void setCant(float cant) {
		this.cant = cant;
	}
	
	
	
}
