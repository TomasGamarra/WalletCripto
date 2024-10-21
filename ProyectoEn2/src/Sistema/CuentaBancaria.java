package Sistema;

/**
 * Esta clase representa la cuenta bancaria de el usuario, con su cbu, alias,
 * moneda asociada y
 * la empresa del banco.
 * 
 * @author Ignacio Mucci Bigliani
 * @author Tomas Gamarra
 * @version 1.0
 **/

public class CuentaBancaria {
	private String alias;
	private String cbu;
	private String empresa;
	private MonedaFiduciaria monedaAsociada;

	public CuentaBancaria() {

	}

	/**
	 * @param alias   alias para enviar dinero a la cuenta bancaria
	 * @param cbu     digitos de una direccion a una cuenta bancaria
	 * @param empresa empresa de banco
	 */
	public CuentaBancaria(String alias, String cbu, String empresa,MonedaFiduciaria monedaAsociada) {
		this.alias = alias;
		this.cbu = cbu;
		this.empresa = empresa;
		this.monedaAsociada= monedaAsociada;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getCBU() {
		return cbu;
	}

	public void setCBU(String cbu) {
		this.cbu = cbu;
	}

	/**
	 * @return String Devuelve el nombre de la empresa
	 */

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	public MonedaFiduciaria getMonedaAsociada() {
		return monedaAsociada;
		
	}
	
	public void setMonedaAsociada(MonedaFiduciaria monedaAsociada) {
		this.monedaAsociada=monedaAsociada;
	}

}
