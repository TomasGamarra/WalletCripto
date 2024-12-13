package Sistema;

public enum MonedaFiatEnum {
    PESOS_ARGENTINOS("Pesos Argentinos", "ARS", 1077, "/images/Ars.png"),
    DOLARES("Dolares", "USD", 1, "/images/Usd.png");

    private final String nombre;
    private final String nomenclatura;
    private final int cotizacion;
    private final String rutaIcono;

    MonedaFiatEnum(String nombre, String nomenclatura, int cotizacion, String rutaIcono) {
        this.nombre = nombre;
        this.nomenclatura = nomenclatura;
        this.cotizacion = cotizacion;
        this.rutaIcono = rutaIcono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNomenclatura() {
        return nomenclatura;
    }

    public int getCotizacion() {
        return cotizacion;
    }

    public String getRutaIcono() {
        return rutaIcono;
    }
}

