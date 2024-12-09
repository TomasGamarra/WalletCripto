package interfaces_DAO;

import java.util.List;

import Sistema.ActivoCripto;

public interface ActivoCriptoDAO {

	boolean create (int idUsuario, int idCriptomoneda , float cantidad);
	ActivoCripto find (int idUsuario,String nomenclatura);
	void update (int idUsuario , int idCriptomoneda , float cantidad);
	void delete (int idUsuario,String nomenclatura);
	List<ActivoCripto> listarActivosCriptos ();
	int incrementarCantidad(int idUsuario,String nomenclatura, float cantidadIncremento);
	public List<ActivoCripto> obtenerActivosCriptoPorUsuario(int idUsuario);
	public void eliminarActivosCriptoPorUsuario(int idUsuario) ;
}
