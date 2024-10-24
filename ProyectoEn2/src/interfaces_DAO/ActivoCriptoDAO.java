package interfaces_DAO;

import Sistema.ActivoCripto;

public interface ActivoCriptoDAO {

	void create (ActivoCripto activo);
	ActivoCripto find (String nomenclatura);
	void update (ActivoCripto activo);
	void delete (String nomenclatura);
}
