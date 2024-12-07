package Sistema;

import java.awt.Image;



public class Criptomoneda extends Moneda{
	private float volatilidad;
	
	public Criptomoneda(String nombre, String nomenclatura, float precio, float volatilidad) {
		super(nombre,nomenclatura,precio);
		this.setVolatilidad(volatilidad);
	}
	

	public float getVolatilidad() {
		return volatilidad;
	}

	public void setVolatilidad(float volatilidad) {
		this.volatilidad = volatilidad;
	}
	

}
