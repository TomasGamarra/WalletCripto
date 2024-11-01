package comparadores;
import java.util.Comparator;


import Sistema.ActivoFiat;
public class ComparadorActivoFiatPorCantidadDescendente implements Comparator <ActivoFiat>{

	@Override
	public int compare(ActivoFiat o1, ActivoFiat o2) {
		return Float.compare(o2.getAmount(), o1.getAmount());
			
	}
}
