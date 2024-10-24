package interfaces_DAO;

import Sistema.ActivoFiat;

public interface ActivoFiatDAO {
	
	void create (ActivoFiat activo);
	ActivoFiat find (String nomenclatura);
	void update (ActivoFiat activo);
	void delete (String nomenclatura);

}
