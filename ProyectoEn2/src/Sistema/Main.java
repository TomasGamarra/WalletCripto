package Sistema;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import gestores_DAO.FactoryDAO;
import interfaces_DAO.MonedaDAO;
import interfaces_DAO.StockDAO;
import comparadores.*;
import interfaces_DAO.ActivoCriptoDAO;
import interfaces_DAO.ActivoFiatDAO;
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
		try {            
		opcion = scanner.nextInt(); 
		}catch (InputMismatchException e) {
			System.err.println("Error en el tipo ingresado , se solicito un entero");
			continue;
		}finally {
			scanner.nextLine();
		}
		//Limpio el Buffer


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
				listarStock(scanner);
				break;
			case 5:
				generarActivo(scanner);
				break;
			case 6:
				listarMisActivos(scanner);
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
			MonedaDAO mondao =FactoryDAO.getMonedaDAO();
			StockDAO stockdao =FactoryDAO.getStockDAO();
			Moneda mon;
			if (tipo.equals("Cripto")) {
				mon = new Criptomoneda(nombre,nomenclatura,valorUsd,0);
				mondao.create(mon);
				stockdao.create(new Stock (cantStock, mon));
			}
			else 
				if (tipo.equals("Fiat")) {
					mon=new MonedaFiat(nombre,nomenclatura,valorUsd);
					mondao.create(mon);
					stockdao.create(new Stock(cantStock,mon));
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
    if (choice == 1) //Ordenar por nomenclatura
    	comparador = new ComparadorMonedaPorNomenclatura();
    else  //Ordenar por valor en USD
    	comparador = new ComparadorMonedaPorValorUsd();
    
    
    MonedaDAO monedadao = FactoryDAO.getMonedaDAO();
    
    List<Moneda> list = monedadao.listarMonedas();
    
    list.sort(comparador);
    for (Moneda mon : list ) {
    	System.out.println("["+mon.getNomenclatura()+"] - ValorUsd :"+ mon.getValorUsd());
    }
}

public static void generarStock() {
	Random random = new Random();
	MonedaDAO monedadao = FactoryDAO.getMonedaDAO();
	StockDAO stockdao = FactoryDAO.getStockDAO();
	List<Moneda> list = monedadao.listarMonedas();
	for (Moneda mon : list)
		stockdao.update(new Stock (random.nextFloat(1000),mon));

}

public static void listarStock(Scanner in) {
	System.out.println("Seleccione el criterio a filtrar el Stock :");
	System.out.println("1. Cantidad (Descendente)");
	System.out.println("2. Nomenclatura");
	int choice = in.nextInt();
	if (!(choice == 1 || choice == 2)) {
		System.out.println("Opcion no valida ...");
		return;
	}
	Comparator <Stock> comp;
	if (choice == 1)
		comp=new ComparadorStockPorCantidadDescendente();
	else
		comp=new ComparadorStockPorNomenclatura();
		
	StockDAO stockdao = FactoryDAO.getStockDAO();
	List<Stock> list = stockdao.listarStock();
	list.sort(comp);
	
	for (Stock stock : list) 
		System.out.println("["+stock.getMoneda().getNomenclatura()+"] - Cantidad :"+ stock.getCantidad());
	
}
public static void generarActivo(Scanner in) {
    System.out.println("Ingrese la nomenclatura de la moneda para crear el activo:");
    String nomenclatura = in.next();
    MonedaDAO monedadao = FactoryDAO.getMonedaDAO();
    Moneda mon = monedadao.find(nomenclatura); //

    if (mon == null) {
        System.out.println("Error: No se encontró la nomenclatura en la tabla moneda.");
        return;
    }

    System.out.println("Ingrese la cantidad del activo:");
    float cantidad;
    try {
        cantidad = in.nextFloat();
    } catch (InputMismatchException e) {
        System.out.println("Error: La cantidad debe ser un número decimal.");
        in.nextLine();
        return;
    }
    if (mon instanceof Criptomoneda)
    	crearActivoCripto((Criptomoneda)mon, cantidad); 
    else
    	if (mon instanceof MonedaFiat)
    	crearActivoFiat((MonedaFiat)mon,cantidad);

}

private static void crearActivoCripto(Criptomoneda mon, float cantidad) {
    ActivoCriptoDAO actdao = FactoryDAO.getActivoCriptoDAO();
    ActivoCripto activo = new ActivoCripto(cantidad, mon, "DireccionRandom");
    actdao.create(activo);
    System.out.println("Activo cripto creado correctamente.");
}

private static void crearActivoFiat(MonedaFiat mon, float cantidad) {
    ActivoFiatDAO actdao = FactoryDAO.getActivoFiatDAO();
    ActivoFiat activo = new ActivoFiat(cantidad, mon);
    actdao.create(activo);
    System.out.println("Activo fiat creado correctamente."); //TENGO QUE CHEQUEAR ESTO , PODRIA METERLE QUE DEVUELVA UN BOOLEAN EL FIND
}

public static void listarMisActivos(Scanner in) {
		System.out.println("Seleccione el criterio para listar los activos :");
		System.out.println("1.Cantidad (Descendente)");
		System.out.println("2.Nomenclatura");
		int choice;
		try {
		choice = in.nextInt(); 
		} catch (InputMismatchException e) {
			System.err.println("Error : Se esperaba un entero");
			return;
		}finally {
			in.nextLine(); //Limpio buffer
		}
		if (!(choice == 1 || choice == 2)) {
			System.out.println("Error : Seleccion distinta de 1 o 2");
			return;
		}
		Comparator <Activo> comp;
		if (choice == 1) {
			comp = new ComparadorActivoPorCantidadDescendente();
		}else
			comp = new ComparadorActivoPorNomenclatura();
		List <ActivoCripto> listCripto = FactoryDAO.getActivoCriptoDAO().listarActivosCriptos();
		List <ActivoFiat> listFiat = FactoryDAO.getActivoFiatDAO().listarActivosFiat();
		List <Activo> listActivo = new LinkedList<Activo>() ;
		listActivo.addAll(listCripto);
		listActivo.addAll(listFiat);
		listActivo.sort(comp);
		for (Activo act : listActivo) {
			System.out.println("["+act.getMoneda().getNomenclatura()+"] - Cantidad : ["+act.getAmount()+"]");
		}
}

public static void simularCompra() {
		        // Implementación
	    }

public static void simularSwap() {
    // Implementación
	}
		
		
		
}

