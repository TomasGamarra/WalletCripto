package Sistema;

import java.awt.Image;


//me Flata unacampo q en teoria es unaenum asi q tegno q hablar con tomi de q onda eso

public class MonedaFiduciaria extends Moneda{


	
	public MonedaFiduciaria(String nombre, String sigla, float precio, float volatilidad, float cant) {
		super(nombre,sigla,precio,volatilidad,cant);
		setTipo("Fiduciaria");
	}
	
	public MonedaFiduciaria() {
		setTipo("Fiduciaria");
	}
	
	
}
