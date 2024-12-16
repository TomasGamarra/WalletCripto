package sistema;

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
