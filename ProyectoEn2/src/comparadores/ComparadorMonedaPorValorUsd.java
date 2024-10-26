package comparadores;

import java.util.Comparator;

import Sistema.Moneda;

public class ComparadorMonedaPorValorUsd implements Comparator <Moneda> {

	@Override
	public int compare(Moneda o1, Moneda o2) {
		return Float.compare(o1.getValorUsd(), o2.getValorUsd());
	}

	
}
