package interfaces_DAO;
import Sistema.Persona;

public interface PersonaDAO {
	void create(String nombre,String apellido);
	Persona find(String nombre, String apellido);
	void create(Persona persona);
	

}
