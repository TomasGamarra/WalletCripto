package sistema;

import java.awt.Image;
import java.net.URL;


// Cambio USD Y CONVERSION  debo esperar a terner la base de datos para conectarlos



public abstract class Moneda {
	private String nombre;
	private String nomenclatura;
	private float valorUsd;
	private String nombreIcono;
	
	
	public Moneda(String nombre, String nomenclatura, float valorUsd, String nombreIcono) {
		this.nombre = nombre;
		this.nomenclatura = nomenclatura;
		this.valorUsd =valorUsd;
		this.nombreIcono=nombreIcono;

	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public float convertir(float cantidad, Moneda monedaDestino) {
	    // Asegúrate de que las monedas no sean nulas
	    if ( monedaDestino == null) {
	        throw new IllegalArgumentException("Las monedas no pueden ser nulas.");
	    }

	    // Obtén el valor en USD de ambas monedas
	    float valorUsdOrigen = this.getValorUsd();
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


	public String getNombreIcono() {
		return nombreIcono;
	}


	public void setNombreIcono(String nombreIcono) {
		this.nombreIcono = nombreIcono;
	}

	
}
