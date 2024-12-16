package interfaces_DAO;


import java.util.List;

import sistema.Criptomoneda;

public interface CriptoDAO {
	
	public void create (Criptomoneda moneda);
	public List<Criptomoneda> obtenerCriptomonedas () ;
	int obtenerIdCripto(String nomenclatura);
	public Criptomoneda find(int idCripto) ;
		
	
}
