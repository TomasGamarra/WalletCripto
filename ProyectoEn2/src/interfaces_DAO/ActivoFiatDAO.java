package interfaces_DAO;

import java.util.List;

import Sistema.ActivoFiat;

public interface ActivoFiatDAO {
	
	void create (ActivoFiat activo);
	ActivoFiat find (String nomenclatura);
	void update (ActivoFiat activo);
	void delete (String nomenclatura);
	List <ActivoFiat> listarActivosFiat();
	int incrementarCantidad(String nomenclatura, float cantidadIncremento);
}
