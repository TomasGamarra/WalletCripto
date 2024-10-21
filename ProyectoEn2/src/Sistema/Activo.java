package Sistema;

// Se necesita crear el obtener_saldo_USD con la Base de Datos


public abstract class Activo {
 private float amount;
 private Moneda moneda;


 public Activo() {
	 
 }

 
 public Activo(float amount, Moneda moneda) {
	this.amount = amount;
	this.moneda = moneda;
}

  
 
 

public float getAmount() {
	return amount;
}


public void setAmount(float amount) {
	this.amount = amount;
}


public Moneda getMoneda() {
	return moneda;
}


public void setMoneda(Moneda moneda) {
	this.moneda = moneda;
}
 

}
