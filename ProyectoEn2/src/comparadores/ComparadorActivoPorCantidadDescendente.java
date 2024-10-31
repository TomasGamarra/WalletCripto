package comparadores;

import java.util.Comparator;

import Sistema.Activo;

public class ComparadorActivoPorCantidadDescendente implements Comparator<Activo> {

	@Override
	public int compare(Activo o1, Activo o2) {
		return Float.compare(o2.getAmount(), o1.getAmount());
		
	}

}
