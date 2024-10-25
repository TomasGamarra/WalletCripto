package comparadores;

import java.util.Comparator;

import Sistema.Moneda;

public class ComparadorMonedaPorValorUsd implements Comparator <Moneda> {

	@Override
	public int compare(Moneda o1, Moneda o2) {
		if (o1.getValorUsd() > o2.getValorUsd())
			return 1;
		else
			if (o1.getValorUsd() < o2.getValorUsd())
				return -1;
		return 0;
	}

	
}
