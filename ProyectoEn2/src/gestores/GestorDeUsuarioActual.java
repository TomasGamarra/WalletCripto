package gestores;

import Sistema.Usuario;

public class GestorDeUsuarioActual {
	private static Usuario user ;

	public static Usuario getUser() {
		return user;
	}

	public static void setUser(Usuario user) {
		GestorDeUsuarioActual.user = user;
	}
	
	
}
