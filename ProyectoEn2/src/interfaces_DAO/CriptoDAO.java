package interfaces_DAO;


import java.util.List;

import Sistema.Criptomoneda;

public interface CriptoDAO {
	
	public void create (Criptomoneda moneda);
	public List<Criptomoneda> obtenerCriptomonedas () ;
	int obtenerIdCripto(String nomenclatura);
}
