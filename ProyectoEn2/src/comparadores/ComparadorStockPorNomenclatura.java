package comparadores;

import java.util.Comparator;

import Sistema.Stock;

public class ComparadorStockPorNomenclatura implements Comparator <Stock>{

	@Override
	public int compare(Stock o1, Stock o2) {
		return o1.getMoneda().getNomenclatura().compareTo(o2.getMoneda().getNomenclatura());
	}

}
