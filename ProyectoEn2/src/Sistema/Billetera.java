package Sistema;

/**
 * Esta clase representa la billetera personal de cada usuario, con datos como
 * la comision y su balance en USD.
 * 
 * @author Ignacio Mucci Bigliani
 * @author Tomas Gamarra
 * @version 1.0
 **/

/*
 * private List(DefiActvo) listaDefi;
 * private List(Activo) listaActivo ;
 * private List(Transaccion) historialTransacciones;
 * dentro de la clase deben existir estas 3 atributos con sus respectivos
 * getters y setters, tambien deben estar incluidas en el constructor.
 */
public class Billetera {

	private float balanceUsd;
	private float comision;
	private Fecha fechaRegistracion;

	public Billetera() {

	}

	/**
	 * Constructor con 3 parametros, mas los demas aclarados
	 * 
	 * @param balanceUsd        Balance en dolares del conjunto de activos
	 * @param comision          Comision que la billetera se queda
	 * @param fechaRegistracion Fecha en que la billetera se creo para el usuario
	 */

	public Billetera(float balanceUsd, float comision, Fecha fechaRegistracion) {
		super();
		this.balanceUsd = balanceUsd;
		this.comision = comision;
		this.fechaRegistracion = fechaRegistracion;
	}

	/**
	 * Obtiene el saldo en dolares (USD).
	 *
	 *
	 * @return El saldo en dólares del total de los activos de la cuenta,
	 *         representado como un valor de tipo {@code float}.
	 */

	public float getBalanceUsd() {
		return balanceUsd;
	}

	public void setBalanceUsd(float balanceUsd) {
		this.balanceUsd = balanceUsd;
	}

	/**
	 * Obtiene la comision establecida de la propia billetera al hacer una
	 * operacion.
	 *
	 *
	 * @return La comision de la billetera , representado como un valor de tipo
	 *         {@code float}.
	 */

	public float getComision() {
		return comision;
	}

	public void setComision(float comision) {
		this.comision = comision;
	}

	/**
	 * Obtiene la fecha de registracion de la billetera.
	 *
	 *
	 * @return La fecha de registracion, representado como un valor de tipo
	 *         {@code Fecha}.
	 */

	public Fecha getFechaRegistracion() {
		return fechaRegistracion;
	}

	public void setFechaRegistracion(Fecha fechaRegistracion) {
		this.fechaRegistracion = fechaRegistracion;
	}

}
