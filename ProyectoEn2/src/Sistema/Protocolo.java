package Sistema;

public class Protocolo {
private float apy;
private String nombre;


public Protocolo(float apy, String nombre) {
	this.apy = apy;
	this.nombre = nombre;
}


public float getApy() {
	return apy;
}


public void setApy(float apy) {
	this.apy = apy;
}


public String getNombre() {
	return nombre;
}


public void setNombre(String nombre) {
	this.nombre = nombre;
}


}
