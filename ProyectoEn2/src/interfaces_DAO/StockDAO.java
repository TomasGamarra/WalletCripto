package interfaces_DAO;

import java.util.List;

import Sistema.Stock;

public interface StockDAO {
	
	void create (Stock stock);
	Stock find (String nomenclatura);
	void update (Stock stock);
	void delete (String nomenclatura);
	List<Stock> listarStock () ;
	void incrementarCantidad(String nomenclatura, float cantidadASumar);

}
