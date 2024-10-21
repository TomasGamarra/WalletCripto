package Sistema;

import java.util.List;

public class Sistema{

    // Instancia única de la clase
    private static Sistema instance;

    // Variables dentro del Singleton
    private float comisionOperaciones;
    private String terminosYCondiciones;
    private GestorStock gestorStock;
    private List<Criptomoneda> listaCriptos;
    private List<MonedaFiduciaria> listaFidu;

    // Constructor privado para evitar que se cree una nueva instancia
    private Sistema() {
        this.comisionOperaciones = 0.01f; // Ejemplo de comisión predeterminada
        this.terminosYCondiciones = "Términos estándar";
        this.gestorStock = new GestorStock();
    }

    // Método estático para obtener la única instancia
    public static Sistema getInstance() {
        if (instance == null) {
            instance = new Sistema();
        }
        return instance;
    }

    public float getComisionOperaciones() {
        return comisionOperaciones;
    }

    public void setComisionOperaciones(float comisionOperaciones) {
        this.comisionOperaciones = comisionOperaciones;
    }

    public String getTerminosYCondiciones() {
        return terminosYCondiciones;
    }

    public void setTerminosYCondiciones(String terminosYCondiciones) {
        this.terminosYCondiciones = terminosYCondiciones;
    }

    public GestorStock getGestorStock() {
        return gestorStock;
    }

    public void setGestorStock(GestorStock gestorStock) {
        this.gestorStock = gestorStock;
    }

    public List<Criptomoneda> getListaCriptos() {
        return listaCriptos;
    }

    public void setListaCriptos(List<Criptomoneda> listaCriptos) {
        this.listaCriptos = listaCriptos;
    }

    public List<MonedaFiduciaria> getListaFidu() {
        return listaFidu;
    }

    public void setListaFidu(List<MonedaFiduciaria> listaFidu) {
        this.listaFidu = listaFidu;
    }
}   
    
    