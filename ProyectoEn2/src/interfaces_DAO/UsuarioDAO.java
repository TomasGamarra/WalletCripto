package interfaces_DAO;

import Sistema.Usuario;

public interface UsuarioDAO {

	void create (int idPersona, String email, String passwr, boolean terminos);
	
	Usuario find (int idPersona, String nombre, String apellido);
}
