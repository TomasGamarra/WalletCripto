package Sistema;

public class ActivoFiat extends Activo{
	
	private MonedaFiat fiat;
	
	
	public ActivoFiat(float amount, MonedaFiat fiat) {
		super(amount);
		this.fiat=fiat;
	}

	public MonedaFiat getMonedaFiat() {
		return fiat;
	}

	public void setMonedaFiat(MonedaFiat fiat) {
		this.fiat = fiat;
	}

	
}
