package gestores;

import implementaciones_DAO.ActivoCriptoDAOjdbc;
import implementaciones_DAO.ActivoFiatDAOjdbc;
import implementaciones_DAO.CriptoDAOjdbc;
import implementaciones_DAO.FiatDAOjdbc;
import implementaciones_DAO.PersonaDAOjdbc;
import implementaciones_DAO.StockDAOjdbc;
import implementaciones_DAO.TransaccionDAOjdbc;
import implementaciones_DAO.UsuarioDAOjdbc;
import interfaces_DAO.ActivoCriptoDAO;
import interfaces_DAO.ActivoFiatDAO;
import interfaces_DAO.CriptoDAO;
import interfaces_DAO.FiatDAO;
import interfaces_DAO.PersonaDAO;
import interfaces_DAO.StockDAO;
import interfaces_DAO.TransaccionDAO;
import interfaces_DAO.UsuarioDAO;

public class FactoryDAO {
	


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
	public static PersonaDAO getPersonaDAO () {
		return new PersonaDAOjdbc();
	}
	public static UsuarioDAO getUsuarioDAO () {
		return new UsuarioDAOjdbc();
	}
	public static CriptoDAO getCriptoDAO () {
		return new CriptoDAOjdbc();
	}
	public static FiatDAO getFiatDAO () {
		return new FiatDAOjdbc();
	}
}