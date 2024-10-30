package Sistema;

import java.awt.Image;


// Cambio USD Y CONVERSION  debo esperar a terner la base de datos para conectarlos



public abstract class Moneda {
	private String nombre;
	private String nomenclatura;
	private float valorUsd;


	
	
	public Moneda() {
		
		
	}
	
	public Moneda(String nombre, String nomenclatura, float valorUsd) {
		this.nombre = nombre;
		this.nomenclatura = nomenclatura;
		this.valorUsd =valorUsd;

	}




	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	public String getNomenclatura() {
		return nomenclatura;
	}




	public void setNomenclatura(String nomenclatura) {
		this.nomenclatura = nomenclatura;
	}

	public float getValorUsd() {
		return valorUsd;
	}

	public void setValorUsd(float valorUsd) {
		this.valorUsd = valorUsd;
	}



	
	
	
}
