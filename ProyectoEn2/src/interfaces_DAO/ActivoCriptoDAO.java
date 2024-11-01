package interfaces_DAO;

import java.util.List;

import Sistema.ActivoCripto;

public interface ActivoCriptoDAO {

	void create (ActivoCripto activo);
	ActivoCripto find (String nomenclatura);
	void update (ActivoCripto activo);
	void delete (String nomenclatura);
	List<ActivoCripto> listarActivosCriptos ();
	int incrementarCantidad(String nomenclatura, float cantidadIncremento);
}
