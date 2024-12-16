package gestores;

import java.util.LinkedList;
import java.util.List;

import enumerativos.CriptomonedaEnum;
import sistema.Criptomoneda;
import sistema.MonedaFiat;
import sistema.MonedaFiatEnum;
import sistema.Usuario;

public class GestorAplicacion {
	
	private static Usuario user ;
	private static List<String> listaCriptoSoportadas;
	private static Criptomoneda criptoComprar;
	private static MonedaFiat fiatCompra;
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

	
}
