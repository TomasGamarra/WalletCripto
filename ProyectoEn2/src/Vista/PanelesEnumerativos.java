package Vista;

public enum PanelesEnumerativos {
	ACTIVOS("activos"),
	COTIZACIONES("cotizaciones"),
	LOGIN("login"),
	HISTORIAL("historial"),
	REGISTRO("registro"),
	COMPRA("compra");
	
	private String nombre;
	
	 PanelesEnumerativos (String nombre) {
		this.setNombre(nombre);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
