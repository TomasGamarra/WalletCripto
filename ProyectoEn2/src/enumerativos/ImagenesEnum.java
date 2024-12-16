package enumerativos;

public enum ImagenesEnum {
     LOGOEMPRESA("/images/hodl.png"),
     ICONOHISTORIAL("/images/Historial.png"),
     ICONOCOTIZACIONES("/images/Cotizaciones.png");
	
	private String rutaFoto;
	
	ImagenesEnum(String rutaFoto) {
		this.setRutaFoto(rutaFoto);
	}

	public String getRutaFoto() {
		return rutaFoto;
	}

	public void setRutaFoto(String rutaFoto) {
		this.rutaFoto = rutaFoto;
	}
}
