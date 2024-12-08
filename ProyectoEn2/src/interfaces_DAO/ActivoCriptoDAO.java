package interfaces_DAO;

import java.util.List;

import Sistema.ActivoCripto;

public interface ActivoCriptoDAO {

	void create (ActivoCripto activo);
	ActivoCripto find (int idUsuario,String nomenclatura);
	void update (ActivoCripto activo);
	void delete (int idUsuario,String nomenclatura);
	List<ActivoCripto> listarActivosCriptos ();
	int incrementarCantidad(int idUsuario,String nomenclatura, float cantidadIncremento);
}
