package comparadores;

import java.util.Comparator;

import Sistema.Moneda;

public class ComparadorMonedaPorNomenclatura implements Comparator <Moneda>{

	@Override
	public int compare(Moneda o1, Moneda o2) {
		return o1.getSigla().compareTo(o2.getSigla());

	}

}
