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

import Excepciones.RequestException;

public class ServicioCotizaciones {
    private static final String URL_API = 
        "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin,ethereum,usd-coin,tether,dogecoin&vs_currencies=usd";
    private static Map <String,Float> map;
   

    public static float obtenerPrecio  ( String claveApi) throws RequestException {
    	 HttpClient cliente = HttpClient.newHttpClient();
         HttpRequest solicitud = HttpRequest.newBuilder()
                 .uri(URI.create(URL_API))
                 .GET()
                 .build();

         try {
             HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
             if (respuesta.statusCode() == 200) {
            	 JSONObject json = new JSONObject(respuesta.body());
                 return json.getJSONObject(claveApi).getFloat("usd");
             } else {
            	 if( respuesta.statusCode() == 429)
                 	throw new RequestException("Demasiadas solicitudes a la API");
             }
         } catch (IOException | InterruptedException e) {
             e.printStackTrace();
         }
         return -1;
    }

    
    public static Map<String, Float> obtenerPrecios(List<String> clavesApi) throws RequestException {
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(URL_API))
                .GET()
                .build();

        try {
            HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
            if (respuesta.statusCode() == 200) {
                return obtenerPreciosHelper(respuesta.body(), clavesApi);
            } else {
                if( respuesta.statusCode() == 429)
                	throw new RequestException("Demasiadas solicitudes a la API");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    private static Map<String, Float> obtenerPreciosHelper(String cuerpoRespuesta, List<String> clavesApi) {
        JSONObject json = new JSONObject(cuerpoRespuesta);
        Map<String, Float> precios = new HashMap<>();

        
        for (String claveApi : clavesApi) {
            if (json.has(claveApi)) {
                float precio = json.getJSONObject(claveApi).getFloat("usd");
                precios.put(claveApi,precio);
            } else {
                System.err.println("Criptomoneda no encontrada: " + claveApi);
                precios.put(claveApi, -1f); // Si no se encuentra, asignamos -1 como valor
            }
        }
        map=precios;
        return precios;
    }


	public static Map <String,Float> getMap() {
		return map;
	}

    


}
