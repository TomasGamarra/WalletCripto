package Sistema;

// Se necesita crear el obtener_saldo_USD con la Base de Datos


public abstract class Activo {
 private float amount;



 public Activo() {
	 
 }

 
 public Activo(float amount) {
	this.amount = amount;

}

  
 
 

public float getAmount() {
	return amount;
}


public void setAmount(float amount) {
	this.amount = amount;
}




}
