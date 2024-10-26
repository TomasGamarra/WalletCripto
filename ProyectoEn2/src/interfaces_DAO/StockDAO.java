package interfaces_DAO;

import Sistema.Stock;

public interface StockDAO {
	
	void create (Stock activo);
	Stock find (String nomenclatura);
	void update (Stock activo);
	void delete (String nomenclatura);

}
