package comparadores;

import java.util.Comparator;

import Sistema.Stock;

public class ComparadorStockPorCantidad implements Comparator <Stock> {

	@Override
	public int compare(Stock o1, Stock o2) {
		return (int)(o1.getCantidad() - o2.getCantidad());
	}

}
