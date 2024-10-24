package interfaces_DAO;

import Sistema.Moneda;

public interface MonedaDAO {
	
	void create (Moneda activo);
	Moneda find (String nomenclatura);
	void update (Moneda activo);
	void delete (String nomenclatura);
}
