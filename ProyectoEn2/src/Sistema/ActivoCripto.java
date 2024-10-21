package Sistema;

public class ActivoCripto extends Activo {
	
	private String direccion;

	
    public ActivoCripto() {
    	
    }
	
	public ActivoCripto(float amount, Moneda moneda, String direccion) {
		super(amount,moneda);
		this.direccion=direccion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	
	

}
