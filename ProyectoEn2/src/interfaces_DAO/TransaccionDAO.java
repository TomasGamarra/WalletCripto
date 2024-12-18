package interfaces_DAO;

import java.util.List;

import sistema.Transaccion;

public interface TransaccionDAO {
	
	void create (Transaccion activo);
	List<Transaccion> find (int idUsuario);
	void update (Transaccion activo);
	void delete (String nomenclatura);
}
