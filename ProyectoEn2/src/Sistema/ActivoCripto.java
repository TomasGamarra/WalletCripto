package Sistema;

public class ActivoCripto extends Activo {
	
	private String direccion;
	private Criptomoneda cripto;
	
    public ActivoCripto() {
    }
	
	public ActivoCripto(float amount, Criptomoneda cripto, String direccion) {
		super(amount);
		this.direccion=direccion;
		this.cripto=cripto;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Criptomoneda getCripto() {
		return cripto;
	}

	public void setCripto(Criptomoneda cripto) {
		this.cripto = cripto;
	}
	
	
	
	
	

}
