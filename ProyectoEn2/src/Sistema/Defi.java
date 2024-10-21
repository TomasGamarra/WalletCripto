package Sistema;

public class Defi extends Transaccion{
	
	private float apy;
	
	public Defi() {
		
	}
	public Defi(float amount, float comision, String estado, String identificador, Moneda moneda, Fecha fecha, float apy) {
		super(amount,comision,estado,identificador,moneda,fecha);
		this.apy=apy;
	}
	public float getApy() {
		return apy;
	}
	public void setApy(float apy) {
		this.apy = apy;
	}
	
	
	
	
	

}
