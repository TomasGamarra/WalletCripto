package Sistema;

/**
 * Esta clase representa una Fecha, en los casos necesarios se utilizara tanto hora como minuto.
*@author  Ignacio Mucci Bigliani
*@author  Tomas Gamarra
*@version 1.0
**/
public class Fecha {
	private int dia;
    private int mes;
    private int anio;
    private int hora;
    private int min;
    
public Fecha(){
        
    }
public Fecha(int dia, int mes, int anio) {
	this.dia = dia;
	this.mes = mes;
	this.anio = anio;
}
public Fecha(int dia, int mes, int anio, int hora, int min) {
	this.dia = dia;
	this.mes = mes;
	this.anio = anio;
	this.hora = hora;
	this.min = min;
}

/**
 * Devuelve el dia de la Fecha
 * 
 * @return Devuelve el dia 
 */
public int getDia() {
	return dia;
}

public void setDia(int dia) {
	this.dia = dia;
}

public int getMes() {
	return mes;
}

public void setMes(int mes) {
	this.mes = mes;
}

public int getAnio() {
	return anio;
}

public void setAnio(int anio) {
	this.anio = anio;
}

public int getHora() {
	return hora;
}

public void setHora(int hora) {
	this.hora = hora;
}

public int getMin() {
	return min;
}

public void setMin(int min) {
	this.min = min;
}


}
