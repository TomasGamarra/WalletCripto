package Sistema;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class ServicioCotizaciones {
    private static final String URL_API = 
        "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin,ethereum,usd-coin,tether,dogecoin&vs_currencies=usd";

   
    public static String[][] consultarPreciosParaTabla() {
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


    
    public static Map<String, Float> obtenerPrecios(List<String> nombresCripto) {
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(URL_API))
                .GET()
                .build();

        try {
            HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
            if (respuesta.statusCode() == 200) {
                return obtenerPreciosHelper(respuesta.body(), nombresCripto);
            } else {
                System.out.println("Error: " + respuesta.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    private static Map<String, Float> obtenerPreciosHelper(String cuerpoRespuesta, List<String> nombresCripto) {
        JSONObject json = new JSONObject(cuerpoRespuesta);
        Map<String, Float> precios = new HashMap<>();

        
        for (String nombreCripto : nombresCripto) {
            if (json.has(nombreCripto)) {
                float precio = json.getJSONObject(nombreCripto).getFloat("usd");
                precios.put(nombreCripto, precio);
            } else {
                System.err.println("Criptomoneda no encontrada: " + nombreCripto);
                precios.put(nombreCripto, -1f); // Si no se encuentra, asignamos -1 como valor
            }
        }
        return precios;
    }

    
    private static String[][] parsearPrecios(String cuerpoRespuesta) {
        JSONObject json = new JSONObject(cuerpoRespuesta);

        return new String[][]{
            {"/images/Bitcoin.png", "Bitcoin(BTC)", "$"+String.valueOf(json.getJSONObject("bitcoin").getFloat("usd"))},
            {"/images/Ethereum.png", "Ethereum(ETH)","$"+ String.valueOf(json.getJSONObject("ethereum").getFloat("usd"))},
            {"/images/Usdc.png", "USDC","$"+ String.valueOf(json.getJSONObject("usd-coin").getFloat("usd"))},
            {"/images/Tether.png", "USDT", "$"+String.valueOf(json.getJSONObject("tether").getFloat("usd"))},
            {"/images/Dogecoin.png", "Dogecoin(DOGE)","$"+ String.valueOf(json.getJSONObject("dogecoin").getFloat("usd"))}
        };
    }
}
