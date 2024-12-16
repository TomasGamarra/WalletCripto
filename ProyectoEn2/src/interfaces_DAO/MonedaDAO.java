package interfaces_DAO;

import java.util.List;

import sistema.Moneda;

public interface MonedaDAO {
	
	void create (Moneda activo);
	Moneda find (String nomenclatura);
	void update (Moneda activo);
	void delete (String nomenclatura);
	List<Moneda> listarMonedas();
	String obtenerTipoMoneda(String nomenclatura);
}
