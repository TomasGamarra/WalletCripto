package comparadores;

import java.util.Comparator;

import sistema.Stock;

public class ComparadorStockPorCantidadDescendente implements Comparator <Stock> {

	@Override
	public int compare(Stock o1, Stock o2) {
		return Float.compare(o2.getCantidad(), o1.getCantidad());
	}

}
