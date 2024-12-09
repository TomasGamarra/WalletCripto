package Sistema;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class ServicioCotizaciones {
    private static final String URL_API = 
        "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin,ethereum,usd-coin,tether,dogecoin&vs_currencies=usd";

   
    public static String[][] consultarPreciosDesdeAPI() {
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(URL_API))
                .GET()
                .build();

        try {
            HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
            if (respuesta.statusCode() == 200) {
                return parsearPrecios(respuesta.body());
            } else {
                System.out.println("Error: " + respuesta.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null; 
    }

   
    private static String[][] parsearPrecios(String cuerpoRespuesta) {
        JSONObject json = new JSONObject(cuerpoRespuesta);

        return new String[][]{
            {"images/Bitcoin.png", "Bitcoin(BTC)", "$"+String.valueOf(json.getJSONObject("bitcoin").getDouble("usd"))},
            {"images/Ethereum.png", "Ethereum(ETH)","$"+ String.valueOf(json.getJSONObject("ethereum").getDouble("usd"))},
            {"images/Usdc.png", "USDC","$"+ String.valueOf(json.getJSONObject("usd-coin").getDouble("usd"))},
            {"images/Tether.png", "USDT", "$"+String.valueOf(json.getJSONObject("tether").getDouble("usd"))},
            {"images/Dogecoin.png", "Dogecoin(DOGE)","$"+ String.valueOf(json.getJSONObject("dogecoin").getDouble("usd"))}
        };
    }
}
