package Sistema;

public class Stock {
	private float cantidad;
	private Moneda moneda;
	
	public Stock (float cantidad, Moneda moneda) {
		this.moneda=moneda;
		this.cantidad=cantidad;
	}
	
	public Stock () {
		
	}
	
	public float getCantidad() {
		return cantidad;
	}
	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}
	public Moneda getMoneda() {
		return moneda;
	}
	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}
	
}
