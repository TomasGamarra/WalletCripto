package interfaces_DAO;

import java.util.List;

import Sistema.ActivoFiat;

public interface ActivoFiatDAO {
	
	void create (int idUsuario,int idFiatMoneda,double cantidad);
	ActivoFiat find (int idUsuario,int nombreFiat);
	void update (ActivoFiat activo);
	void delete (String nomenclatura);
	List <ActivoFiat> listarActivosFiat();
	int incrementarCantidad(int idUsuario,String nomenclatura, float cantidadIncremento);
}
