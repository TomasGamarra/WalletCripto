package Sistema;

public class Recepcion extends Transaccion{

	private String usuario;	
	
	public Recepcion() {
		
	}
	public Recepcion(float amount, float comision, String estado, String identificador, Moneda moneda, Fecha fecha, String usuario) {
		super(amount,comision,estado,identificador,moneda,fecha);
		this.usuario= usuario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
	
	
}
