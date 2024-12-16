package interfaces_DAO;

import java.util.List;

import Sistema.MonedaFiat;

public interface FiatDAO {
	public void create (MonedaFiat moneda);
	public List<MonedaFiat> obtenerFiats () ;
	int obtenerIdFiat(String nomenclatura);
	public MonedaFiat find (int idMoneda);
}
