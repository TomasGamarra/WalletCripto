package Sistema;

public class Compra extends Transaccion{
	
	
	
	public Compra() {
		
	}
	public Compra(float amount, float comision, String estado, String identificador, Moneda moneda, Fecha fecha) {
		super(amount,comision,estado,identificador,moneda,fecha);
	}
	
	

}
