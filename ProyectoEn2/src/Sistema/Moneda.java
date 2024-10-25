package Sistema;

import java.awt.Image;


// Cambio USD Y CONVERSION  debo esperar a terner la base de datos para conectarlos



public abstract class Moneda {
	private String nombre;
	private String sigla;
	private float valorUsd;


	
	
	public Moneda() {
		
		
	}
	
	public Moneda(String nombre, String sigla, float valorUsd) {
		this.nombre = nombre;
		this.sigla = sigla;
		this.valorUsd =valorUsd;

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

	public float getValorUsd() {
		return valorUsd;
	}

	public void setValorUsd(float valorUsd) {
		this.valorUsd = valorUsd;
	}



	
	
	
}
