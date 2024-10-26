package Sistema;
//CREO QUE ESTO VA A DESAPARECER
import java.util.HashMap;
//tenemos q hacer el actualizarStock

public class GestorStock {

    // HashMap que gestiona el stock, donde la clave es el nombre del producto y el valor es la cantidad
    private HashMap<Moneda, Float> stock;

    // Constructor: Inicializa el HashMap vacío
    public GestorStock() {
        stock = new HashMap<>();
    }

    // Método para agregar o actualizar un producto en el stock
    public void agregarStock(Moneda Moneda, Float cantidad) {
        stock.put(Moneda, cantidad);
    }

    // Método para eliminar un producto del stock
    public void eliminarStock(Moneda moneda) {
        if (stock.containsKey(moneda)) {
            stock.remove(moneda);
        } else {
            System.out.println("El producto " + moneda.getNombre() + " no existe en el stock.");
        }
    }

    // Método para obtener la cantidad de un producto
    public Float obtenerCantidadStock(Moneda moneda) {
        return stock.getOrDefault(moneda, 0.0f); // Devuelve 0 si el producto no existe
    }

    // Método para verificar si un producto está en stock
    public boolean existeProducto(Moneda moneda) {
        return stock.containsKey(moneda);
    }

    // Método para obtener el HashMap completo del stock (útil si quieres verlo todo)
    public HashMap<Moneda, Float> obtenerStockCompleto() {
        return stock;
    }

    // Método para mostrar el stock
    public void mostrarStock() {
        if (stock.isEmpty()) {
            System.out.println("El stock está vacío.");
        } else {
            System.out.println("Stock actual:");
            for (Moneda producto : stock.keySet()) {
                System.out.println("Producto: " + producto + ", Cantidad: " + stock.get(producto));
            }
        }
    }
}
