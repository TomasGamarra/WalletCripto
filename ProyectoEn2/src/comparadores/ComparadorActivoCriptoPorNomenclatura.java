package comparadores;

import java.util.Comparator;

import sistema.ActivoCripto;

public class ComparadorActivoCriptoPorNomenclatura implements Comparator<ActivoCripto> {

	@Override
	public int compare(ActivoCripto o1, ActivoCripto o2) {
		return o1.getCripto().getNomenclatura().compareTo(o2.getCripto().getNomenclatura());
	}

}
