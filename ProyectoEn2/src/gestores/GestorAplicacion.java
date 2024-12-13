package gestores;

import java.util.LinkedList;
import java.util.List;

import Sistema.CriptomonedaEnum;
import Sistema.Usuario;

public class GestorAplicacion {
	
	private static Usuario user ;
	private static List<String> listaCriptoSoportadas;
	
	static {
		listaCriptoSoportadas = new LinkedList<>();
		for (CriptomonedaEnum cripto : CriptomonedaEnum.values())  
			listaCriptoSoportadas.add(cripto.getClaveApi()); 
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


	
	
}
