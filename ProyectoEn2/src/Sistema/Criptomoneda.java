package Sistema;

import java.awt.Image;

//Al final le sacamos a  esta verga el abstract?'


public class Criptomoneda extends Moneda{

	
	public Criptomoneda(String nombre, String sigla, float precio, float volatilidad,float cant) {
		super(nombre,sigla,precio,volatilidad,cant );
		setTipo("Cripto");
	}
	
	public Criptomoneda() {
		setTipo("Cripto");
		
	}
	

}
