package Sistema;

import java.awt.Image;



public class Criptomoneda extends Moneda{
	private float volatilidad;
	
	public Criptomoneda(String nombre, String sigla, float precio, float volatilidad) {
		super(nombre,sigla,precio);
		this.setVolatilidad(volatilidad);
	}
	
	public Criptomoneda() {

		
	}

	public float getVolatilidad() {
		return volatilidad;
	}

	public void setVolatilidad(float volatilidad) {
		this.volatilidad = volatilidad;
	}
	

}
