package interfaces_DAO;

import java.util.List;

import sistema.Stock;

public interface StockDAO {
	
	void create (int idCripto, double cantidad);
	Stock find (int idCripto);
	void update (Stock stock);
	void delete (String nomenclatura);
	List<Stock> listarStock () ;
	void incrementarCantidad(int idCripto, float cantidadASumar);

}
