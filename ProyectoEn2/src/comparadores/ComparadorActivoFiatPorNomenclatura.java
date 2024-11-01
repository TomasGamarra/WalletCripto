package comparadores;

import java.util.Comparator;

import Sistema.ActivoFiat;

public class ComparadorActivoFiatPorNomenclatura implements Comparator<ActivoFiat> {

	@Override
	public int compare(ActivoFiat o1, ActivoFiat o2) {		
			return o1.getMonedaFiat().getNomenclatura().compareTo(o2.getMonedaFiat().getNomenclatura());
	}

		
	

}
