package comparadores;

import java.util.Comparator;

import Sistema.Activo;

public class ComparadorActivoPorNomenclatura implements Comparator<Activo> {

	@Override
	public int compare(Activo o1, Activo o2) {
		return o1.getMoneda().getNomenclatura().compareTo(o2.getMoneda().getNomenclatura());
	}

}
