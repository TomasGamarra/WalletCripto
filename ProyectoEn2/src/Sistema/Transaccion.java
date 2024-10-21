package Sistema;

public abstract class Transaccion {
 private float amount;
 private float comision;
 private String estado;
 private String identificador;
 private Moneda moneda;
 private Fecha fecha;

public Transaccion() {
	
	
}
 
 
 
 public Transaccion(float amount, float comision, String estado, String identificador, Moneda moneda, Fecha fecha) {
	this.amount = amount;
	this.comision = comision;
	this.estado = estado;
	this.identificador = identificador;
	this.moneda = moneda;
	this.fecha = fecha;
}



public float getAmount() {
	return amount;
}



public void setAmount(float amount) {
	this.amount = amount;
}



public float getComision() {
	return comision;
}



public void setComision(float comision) {
	this.comision = comision;
}



public String getEstado() {
	return estado;
}



public void setEstado(String estado) {
	this.estado = estado;
}



public String getIdentificador() {
	return identificador;
}



public void setIdentificador(String identificador) {
	this.identificador = identificador;
}



public Moneda getMoneda() {
	return moneda;
}



public void setMoneda(Moneda moneda) {
	this.moneda = moneda;
}



public Fecha getFecha() {
	return fecha;
}



public void setFecha(Fecha fecha) {
	this.fecha = fecha;
}
 
}
