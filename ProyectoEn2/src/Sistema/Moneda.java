package Sistema;

import java.awt.Image;
import java.net.URL;


// Cambio USD Y CONVERSION  debo esperar a terner la base de datos para conectarlos



public abstract class Moneda {
	private String nombre;
	private String nomenclatura;
	private float valorUsd;
	
	
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
	
	public static float convertir(float cantidad, Moneda monedaOrigen, Moneda monedaDestino) {
	    // Asegúrate de que las monedas no sean nulas
	    if (monedaOrigen == null || monedaDestino == null) {
	        throw new IllegalArgumentException("Las monedas no pueden ser nulas.");
	    }

	    // Obtén el valor en USD de ambas monedas
	    float valorUsdOrigen = monedaOrigen.getValorUsd();
	    float valorUsdDestino = monedaDestino.getValorUsd();

	    // Realiza la conversión
	    return (cantidad * valorUsdOrigen) / valorUsdDestino;
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
