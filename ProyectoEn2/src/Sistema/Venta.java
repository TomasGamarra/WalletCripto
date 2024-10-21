package Sistema;

public class Venta extends Transaccion{
	
private MonedaFiduciaria monedaAsociada;

public Venta() {
	
}
public Venta(float amount, float comision, String estado, String identificador, Moneda moneda, Fecha fecha,MonedaFiduciaria monedaAsociada) {
	super(amount,comision,estado,identificador,moneda,fecha);
	this.monedaAsociada=monedaAsociada;
}
public MonedaFiduciaria getMonedaAsociada() {
	return monedaAsociada;
}
public void setMonedaAsociada(MonedaFiduciaria monedaAsociada) {
	this.monedaAsociada = monedaAsociada;
}





}
