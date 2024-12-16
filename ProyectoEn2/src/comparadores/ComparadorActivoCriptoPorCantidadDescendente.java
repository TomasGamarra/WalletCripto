package comparadores;

import java.util.Comparator;

import sistema.Activo;
import sistema.ActivoCripto;

public class ComparadorActivoCriptoPorCantidadDescendente implements Comparator<ActivoCripto> {

	@Override
	public int compare(ActivoCripto o1, ActivoCripto o2) {
		return Float.compare(o2.getAmount(), o1.getAmount());
		
	}

}
