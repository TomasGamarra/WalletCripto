package interfaces_DAO;

import Sistema.Transaccion;

public interface TransaccionDAO {
	
	void create (Transaccion activo);
	Transaccion find (String nomenclatura);
	void update (Transaccion activo);
	void delete (String nomenclatura);
}
