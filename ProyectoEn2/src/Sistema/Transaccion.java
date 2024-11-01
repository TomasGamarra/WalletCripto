package Sistema;

import java.time.LocalDateTime;

public class Transaccion {
 private String resumen;
 private String tipo;
 private LocalDateTime fecha;


public Transaccion(String resumen , String tipo, LocalDateTime fecha) {
	
	this.resumen=resumen;
	this.tipo=tipo;
	this.fecha=fecha;
}

public Transaccion (String resumen, String tipo) {
	this.tipo=tipo;
	this.resumen=resumen;
}


public String getResumen() {
	return resumen;
}


public void setResumen(String resumen) {
	this.resumen = resumen;
}


public String getTipo() {
	return tipo;
}


public void setTipo(String tipo) {
	this.tipo = tipo;
}


public LocalDateTime getFecha() {
	return fecha;
}


public void setFecha(LocalDateTime fecha) {
	this.fecha = fecha;
}
 
 
 
 



 
}
