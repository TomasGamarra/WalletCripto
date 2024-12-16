package interfaces_DAO;

import sistema.Usuario;

public interface UsuarioDAO {

	void create (int idPersona, String email, String passwr, boolean terminos);
	int create (Usuario user);
	Usuario find (int idPersona, String nombre, String apellido);
	Usuario findByEmail(String email);
	
}
