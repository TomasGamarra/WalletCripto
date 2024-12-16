package interfaces_DAO;

import java.util.List;

import Sistema.ActivoFiat;

public interface ActivoFiatDAO {
	
	void create (int idUsuario,int idFiatMoneda,double cantidad);
	ActivoFiat find (int idUsuario,String nombreFiat);
	void update (ActivoFiat activo);
	void delete (String nomenclatura);
	List <ActivoFiat> listarActivosFiat();
	int incrementarCantidad(int idUsuario,int idMoneda, float cantidadIncremento);
	public List<ActivoFiat> obtenerActivosFiatPorUsuario(int idUsuario);
	public void eliminarActivosFiatPorUsuario(int idUsuario); 
}
