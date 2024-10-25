package Sistema;
import java.util.Scanner;
import gestores_DAO.FactoryDAO;
import interfaces_DAO.MonedaDAO;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int opcion;

		while (true) {
		System.out.println("----- Menú -----");
		System.out.println("1. Crear Monedas");
		System.out.println("2. Listar Monedas");
		System.out.println("3. Generar Stock");
		System.out.println("4. Listar Stock");
		System.out.println("5. Generar Mis Activos");
		System.out.println("6. Listar Mis Activos");
		System.out.println("7. Simular una COMPRA de Criptomoneda");
		System.out.println("8. Simular un SWAP");
		System.out.println("0. Salir");
		System.out.print("Seleccione una opción: ");
		            
		opcion = scanner.nextInt();

		switch (opcion) {
			case 1:
				crearMonedas();
				break;
			case 2:
				listarMonedas();
				break;
			case 3:
				generarStock();
				break;
			case 4:
				listarStock();
				break;
			case 5:
				generarMisActivos();
				break;
			case 6:
				listarMisActivos();
				break;
			case 7:
				simularCompra();
				break;
			case 8:
				simularSwap();
                break;
            case 0:
                System.out.println("Saliendo del programa...");
                scanner.close(); // Cierra el escáner antes de salir
                return; // Sale del método main
            default:
                System.out.println("Opción no válida. Intente de nuevo.");
                break;
		   }
		}
}


public static void crearMonedas() {
	try(Scanner in = new Scanner(System.in);){
		System.out.println("Ingrese tipo de moneda (Cripto/Fiat)");
		String tipo=in.next();
		System.out.println("Ingrese nombre de la moneda:");
		String nombre=in.next();
		System.out.println("Ingrese nomenclatura:");
		String nomenclatura = in.next();
		System.out.println("Ingrese valor en USD:");
		float valorUsd = in.nextFloat();
		System.out.println("Ingrese el stock disponible:");
		float stock=in.nextFloat();
		System.out.println("Desea confirmar para guardar en la Base de Datos ? (Y/N):");
		String choice =in.next();
		if (choice.equals("Y")) {
			FactoryDAO factory = new FactoryDAO();
			MonedaDAO dao =factory.getMonedaDAO();
			
			Moneda mon;
			Sistema sis = Sistema.getInstance();
			if (tipo.equals("Cripto")) {
				mon = new Criptomoneda(nombre,nomenclatura,valorUsd,0);
				//sis.getGestorStock().agregarStock(mon, stock) Tengo que hacer cambios en el gestor para que funcione
				dao.create(mon);
			}
			else 
				if (tipo.equals("Fiat")) {
					mon=new MonedaFiat(nombre,nomenclatura,valorUsd);
					dao.create(mon);
				}
		}else {
			System.out.println("No se ha creado ninguna moneda");
			return;
		}
	
	} 
	
}

public static void listarMonedas() {
    
}

public static void generarStock() {
		        
}

public static void listarStock() {
		        // Implementación
}

public static void generarMisActivos() {
		        // Implementación
}

public static void listarMisActivos() {
		        // Implementación
}

public static void simularCompra() {
		        // Implementación
	    }

public static void simularSwap() {
    // Implementación
	}
		
		
		
}

