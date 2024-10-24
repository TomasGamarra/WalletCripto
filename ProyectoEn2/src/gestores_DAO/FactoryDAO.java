package gestores_DAO;

import implementaciones_DAO.ActivoCriptoDAOjdbc;
import implementaciones_DAO.ActivoFiatDAOjdbc;
import implementaciones_DAO.MonedaDAOjdbc;
import implementaciones_DAO.TransaccionDAOjdbc;
import interfaces_DAO.ActivoCriptoDAO;
import interfaces_DAO.ActivoFiatDAO;
import interfaces_DAO.MonedaDAO;
import interfaces_DAO.TransaccionDAO;

public class FactoryDAO {
	
	MonedaDAO getMonedaDAO () {
		return new MonedaDAOjdbc();
	}
	
	ActivoFiatDAO getActivoFiatDAO () {
		return new ActivoFiatDAOjdbc() ;
	}
	
	ActivoCriptoDAO getActivoCriptoDAO () {
		return new ActivoCriptoDAOjdbc() ;
	}
	
	TransaccionDAO getTransaccionDAO() {
		return new TransaccionDAOjdbc() ;
	}

}
