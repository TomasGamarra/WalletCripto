package gestores;

import java.util.LinkedList;
import java.util.List;

import Sistema.Criptomoneda;
import Sistema.CriptomonedaEnum;
import Sistema.MonedaFiat;
import Sistema.MonedaFiatEnum;
import Sistema.Usuario;

public class GestorAplicacion {
	
	private static Usuario user ;
	private static List<String> listaCriptoSoportadas;
	private static Criptomoneda criptoComprar;
	private static MonedaFiat fiatCompra;
	private static float conversion;
	private static List<String> listaFiatSoportadas;
	static {
		listaCriptoSoportadas = new LinkedList<>();
		for (CriptomonedaEnum cripto : CriptomonedaEnum.values())  
			listaCriptoSoportadas.add(cripto.getClaveApi()); 
		listaFiatSoportadas = new LinkedList<>();
		for (MonedaFiatEnum fiat : MonedaFiatEnum.values())
			listaFiatSoportadas.add(fiat.getNomenclatura());

	}
	
	public static Usuario getUser() {
		return user;
	}

	public static void setUser(Usuario user) {
		GestorAplicacion.user = user;
	}

	public static List<String> getListaCriptoSoportadas() {
		return listaCriptoSoportadas;
	}
	
	public static List<String> getListaFiatSoportadas() {
		return listaFiatSoportadas;
	}

	public static void setCriptoComprar( Criptomoneda cripto) {
		criptoComprar=cripto;
	}
	
	public static Criptomoneda getCriptoComprar() {
		return criptoComprar;
	}

	public static MonedaFiat getFiatCompra() {
		return fiatCompra;
	}

	public static void setFiatCompra(MonedaFiat fiatCompra) {
		GestorAplicacion.fiatCompra = fiatCompra;
	}

	public static float getConversion() {
		return conversion;
	}

	public static void setConversion(float conversion) {
		GestorAplicacion.conversion = conversion;
	}
	
}
