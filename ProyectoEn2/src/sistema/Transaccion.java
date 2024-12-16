package sistema;


import java.sql.Timestamp;

public class Transaccion {
 private String resumen;
 private String tipo;
 private Timestamp fecha;
 private Usuario usuario;


public Transaccion(String resumen , String tipo, Timestamp fecha, Usuario usuario) {
	
	this.resumen=resumen;
	this.tipo=tipo;
	this.fecha=fecha;
	this.usuario=usuario;
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


public Timestamp getFecha() {
	return fecha;
}


public void setFecha(Timestamp fecha) {
	this.fecha = fecha;
}

public Usuario getUsuario() {
	return usuario;
}

public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
}
 
 
 
 



 
}
