package Sistema;

public class Envio extends Transaccion{
	
	private String usuarioReceptor;
	private String direccionEnvio;
	
	
	public Envio() {
		
	}
	public Envio(float amount, float comision, String estado, String identificador, Moneda moneda, Fecha fecha, String usuarioReceptor,String direccionEnvio) {
		super(amount,comision,estado,identificador,moneda,fecha);
		this.usuarioReceptor=usuarioReceptor;
		this.direccionEnvio= direccionEnvio;
	}
	public String getUsuarioReceptor() {
		return usuarioReceptor;
	}
	public void setUsuarioReceptor(String usuarioReceptor) {
		this.usuarioReceptor = usuarioReceptor;
	}
	public String getDireccionEnvio() {
		return direccionEnvio;
	}
	public void setDireccionEnvio(String direccionEnvio) {
		this.direccionEnvio = direccionEnvio;
	}

	
	
	
}
