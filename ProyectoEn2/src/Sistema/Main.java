package Sistema;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import gestores_DAO.FactoryDAO;
import interfaces_DAO.MonedaDAO;
import interfaces_DAO.StockDAO;
import comparadores.*;
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int opcion;
		//MENU DE SELECCION
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
		//Limpio el Buffer
		scanner.nextLine();

		switch (opcion) {
			case 1:
				crearMonedas(scanner);
				break;
			case 2:
				listarMonedas(scanner);
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


public static void crearMonedas(Scanner in) {	
		System.out.println("Ingrese tipo de moneda (Cripto/Fiat)");
		String tipo=in.next();
		if (!(tipo.equals("Cripto") || tipo.equals("Fiat"))) {
			System.out.println("La moneda seleccionada no es Cripto o Fiat!");
			return;
		}
		System.out.println("Ingrese nombre de la moneda:");
		String nombre=in.next();
		System.out.println("Ingrese nomenclatura:");
		String nomenclatura = in.next();
		System.out.println("Ingrese valor en USD:");
		float valorUsd = in.nextFloat();
		System.out.println("Ingrese el stock disponible:");
		float cantStock=in.nextFloat();
		System.out.println("Desea confirmar para guardar en la Base de Datos ? (Y/N):");
		String choice =in.next();
		if (choice.equals("Y")) {
			FactoryDAO factory = new FactoryDAO();
			MonedaDAO mondao =factory.getMonedaDAO();
			StockDAO stockdao = factory.getStockDAO();
			Moneda mon;
			Stock stock;
			if (tipo.equals("Cripto")) {
				mon = new Criptomoneda(nombre,nomenclatura,valorUsd,0);
				mondao.create(mon);
				stock = new Stock (cantStock , mon);
				stockdao.create(stock);
			}
			else 
				if (tipo.equals("Fiat")) {
					mon=new MonedaFiat(nombre,nomenclatura,valorUsd);
					mondao.create(mon);
					stock = new Stock (cantStock,mon);
					stockdao.create(stock);
				}
		}else {
			System.out.println("No se ha creado ninguna moneda , la creacion fue cancelada");
			return;
		}
	
}

public static void listarMonedas(Scanner in) {
	System.out.println("Ingrese el criterio para ordenar el listado:");
	System.out.println("1.Nomenclatura");
	System.out.println("2.Valor en USD");
	int choice = in.nextInt();
	if (!(choice == 1 || choice == 2)) { //Valor erroneo
		System.out.println("Valor erroneo ingresado");
		return ;
	}
	Comparator <Moneda> comparador;
    if (choice == 1) { //Ordenar por nomenclatura
    	comparador = new ComparadorMonedaPorNomenclatura();
    }else { //Ordenar por valor en USD
    	comparador = new ComparadorMonedaPorValorUsd();
    }
    
    FactoryDAO factory = new FactoryDAO();
    MonedaDAO monedadao = factory.getMonedaDAO();
    
    List<Moneda> list = monedadao.listarMonedas();
    
    list.sort(comparador);
    for (Moneda mon : list ) {
    	System.out.println("["+mon.getSigla()+"] - ValorUsd :"+ mon.getValorUsd());
    }
}

public static void generarStock() {
	Random random = new Random();
	MonedaDAO monedadao = FactoryDAO.getMonedaDAO();
	StockDAO stockdao = FactoryDAO.getStockDAO();
	List<Moneda> list = monedadao.listarMonedas();
	for (Moneda mon : list)
		stockdao.update(new Stock (random.nextFloat(300.00f),mon));

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

