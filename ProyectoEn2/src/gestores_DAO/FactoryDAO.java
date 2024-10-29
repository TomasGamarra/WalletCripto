package gestores_DAO;

import implementaciones_DAO.ActivoCriptoDAOjdbc;
import implementaciones_DAO.ActivoFiatDAOjdbc;
import implementaciones_DAO.MonedaDAOjdbc;
import implementaciones_DAO.StockDAOjdbc;
import implementaciones_DAO.TransaccionDAOjdbc;
import interfaces_DAO.ActivoCriptoDAO;
import interfaces_DAO.ActivoFiatDAO;
import interfaces_DAO.MonedaDAO;
import interfaces_DAO.StockDAO;
import interfaces_DAO.TransaccionDAO;

public class FactoryDAO {
	
	public FactoryDAO() {
		
	}
	
	public static MonedaDAO getMonedaDAO () {
		return new MonedaDAOjdbc();
	}
	
	public static ActivoFiatDAO getActivoFiatDAO () {
		return new ActivoFiatDAOjdbc() ;
	}
	
	public static ActivoCriptoDAO getActivoCriptoDAO () {
		return new ActivoCriptoDAOjdbc() ;
	}
	
	public static TransaccionDAO getTransaccionDAO() {
		return new TransaccionDAOjdbc() ;
	}
	
	public static StockDAO getStockDAO () {
		return new StockDAOjdbc();
	}
}
