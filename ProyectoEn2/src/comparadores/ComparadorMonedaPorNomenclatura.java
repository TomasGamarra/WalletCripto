package comparadores;

import java.util.Comparator;

import Sistema.Moneda;

public class ComparadorMonedaPorNomenclatura implements Comparator <Moneda>{

	@Override
	public int compare(Moneda o1, Moneda o2) {
		return o1.getNomenclatura().compareTo(o2.getNomenclatura());

	}

}
