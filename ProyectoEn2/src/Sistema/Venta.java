package Sistema;

public class Venta extends Transaccion{
	
private MonedaFiat monedaAsociada;

public Venta() {
	
}
public Venta(float amount, float comision, String estado, String identificador, Moneda moneda, Fecha fecha,MonedaFiat monedaAsociada) {
	super(amount,comision,estado,identificador,moneda,fecha);
	this.monedaAsociada=monedaAsociada;
}
public MonedaFiat getMonedaAsociada() {
	return monedaAsociada;
}
public void setMonedaAsociada(MonedaFiat monedaAsociada) {
	this.monedaAsociada = monedaAsociada;
}





}
