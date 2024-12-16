package sistema;

public class Stock {
	private float cantidad;
	private Criptomoneda moneda;
	
	public Stock (float cantidad, Criptomoneda moneda) {
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
	public void setMoneda(Criptomoneda moneda) {
		this.moneda = moneda;
	}
	
}
