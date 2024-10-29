package interfaces_DAO;

import Sistema.Stock;

public interface StockDAO {
	
	void create (Stock stock);
	Stock find (String nomenclatura);
	void update (Stock stock);
	void delete (String nomenclatura);

}
