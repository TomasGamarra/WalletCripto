package enumerativos;
public enum CriptomonedaEnum {
    BITCOIN("Bitcoin", "BTC", "bitcoin", "/images/Bitcoin.png"),
    ETHEREUM("Ethereum", "ETH", "ethereum", "/images/Ethereum.png"),
    DOGECOIN("Dogecoin", "DOGE", "dogecoin", "/images/Dogecoin.png"),
    USDC("Usdc", "USDC", "usd-coin", "/images/Usdc.png"),
    TETHER("Tether", "USDT", "tether", "/images/Tether.png");

    private final String nombre;
    private final String nomenclatura;
    private final String claveApi;
    private final String rutaIcono;

    CriptomonedaEnum(String nombre, String nomenclatura, String claveApi, String rutaIcono) {
        this.nombre = nombre;
        this.nomenclatura = nomenclatura;
        this.claveApi = claveApi;
        this.rutaIcono = rutaIcono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNomenclatura() {
        return nomenclatura;
    }

    public String getClaveApi() {
        return claveApi;
    }

    public String getRutaIcono() {
        return rutaIcono;
    }
}