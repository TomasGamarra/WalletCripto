//package Sistema;
//import java.sql.Statement;
//import java.time.LocalDateTime;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.Comparator;
//import java.util.InputMismatchException;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Random;
//import java.util.Scanner;
//import interfaces_DAO.MonedaDAO;
//import interfaces_DAO.StockDAO;
//import interfaces_DAO.TransaccionDAO;
//import comparadores.*;
//import interfaces_DAO.ActivoCriptoDAO;
//import interfaces_DAO.ActivoFiatDAO;
//import gestores.*;
//
//public class Main {
//
//	public static void main(String[] args)  {
//		//Creacion de tablas 
//		creacionDeTablasEnBD();
//		
//		Scanner scanner = MyScanner.getScanner();
//		int opcion;
//		//MENU DE SELECCION
//		while (true) {
//		
//		System.out.println("----- Menú -----");
//		System.out.println("1. Crear Monedas");
//		System.out.println("2. Listar Monedas");
//		System.out.println("3. Generar Stock");
//		System.out.println("4. Listar Stock");
//		System.out.println("5. Generar Mis Activos");
//		System.out.println("6. Listar Mis Activos");
//		System.out.println("7. Simular una COMPRA de Criptomoneda");
//		System.out.println("8. Simular un SWAP");
//		System.out.println("0. Salir");
//		System.out.print("Seleccione una opción: ");
//		try {            
//		opcion = scanner.nextInt(); 
//		}catch (InputMismatchException e) {
//			System.err.println("Error en el tipo ingresado , se solicito un entero");
//			continue;
//		}finally {
//			scanner.nextLine(); //Limpio el Buffer
//		}
//
//		switch (opcion) {
//			case 1:
//				crearMonedas();
//				break;
//			case 2:
//				listarMonedas();
//				break;
//			case 3:
//				generarStock();
//				break;
//			case 4:
//				listarStock();
//				break;
//			case 5:
//				generarActivo();
//				break;
//			case 6:
//				listarMisActivos();
//				break;
//			case 7:
//				simularCompra();
//				break;
//			case 8:
//				simularSwap();
//                break;
//            case 0:
//                System.out.println("Saliendo del programa...");
//                scanner.close(); // Cierra el scanner antes de salir
//                MyConnection.cerrarCon(); //Cierro conexion a la db
//                return; 
//            default:
//                System.out.println("Opción no válida. Intente de nuevo.");
//                break;
//		   }
//		}
//
//}
//
//
//public static void crearMonedas() {	
//		Scanner in =MyScanner.getScanner();
//		
//		String tipo;
//		String choice;
//		float cantStock;
//		String nombre;
//		String nomenclatura;
//		float valorUsd;
//		try {
//			System.out.println("Ingrese tipo de moneda (Cripto/Fiat)");
//			tipo=in.next();
//			if (!(tipo.equals("Cripto") || tipo.equals("Fiat"))) {
//				System.out.println("La moneda seleccionada no es Cripto o Fiat!");
//				return;
//			}
//			System.out.println("Ingrese nombre de la moneda:");
//			nombre=in.next();
//			System.out.println("Ingrese nomenclatura:");
//			nomenclatura = in.next().toUpperCase();
//			System.out.println("Ingrese valor en USD:");
//			valorUsd = in.nextFloat();
//			System.out.println("Ingrese el stock disponible:");
//			cantStock=in.nextFloat();
//			System.out.println("Desea confirmar para guardar en la Base de Datos ? (Y/N):");
//			choice =in.next().toUpperCase();
//		}catch(InputMismatchException e) {
//			System.err.println("Se ingreso un valor no esperado");
//			return;
//		}
//		if (choice.equals("Y")) {
//			MonedaDAO mondao =FactoryDAO.getMonedaDAO();
//			StockDAO stockdao =FactoryDAO.getStockDAO();
//			Moneda mon;
//			if (tipo.equals("Cripto")) {
//				mon = new Criptomoneda(nombre,nomenclatura,valorUsd,0);
//				
//			}
//			else 
//				mon=new MonedaFiat(nombre,nomenclatura,valorUsd);
//		mondao.create(mon);
//		stockdao.create(new Stock (cantStock, mon));
//				
//		}else {
//			System.out.println("No se ha creado ninguna moneda , la creacion fue cancelada");
//			return;
//		}
//	
//}
//
//public static void listarMonedas() {
//	Scanner in =MyScanner.getScanner();
//	
//	System.out.println("Ingrese el criterio para ordenar el listado:");
//	System.out.println("1.Nomenclatura");
//	System.out.println("2.Valor en USD");
//	
//	int choice = in.nextInt();
//	
//	if (!(choice == 1 || choice == 2)) { //Valor erroneo
//		System.out.println("Valor erroneo ingresado");
//		return ;
//	}
//	
//	Comparator <Moneda> comparador;
//    if (choice == 1) //Ordenar por nomenclatura
//    	comparador = new ComparadorMonedaPorNomenclatura();
//    else  //Ordenar por valor en USD
//    	comparador = new ComparadorMonedaPorValorUsd();
//    
//    
//    MonedaDAO monedadao = FactoryDAO.getMonedaDAO();
//    
//    List<Moneda> list = monedadao.listarMonedas();
//    
//    list.sort(comparador);
//    for (Moneda mon : list ) {
//    	System.out.println("["+mon.getNomenclatura()+"] - ValorUsd :"+ mon.getValorUsd());
//    }
//}
//
//public static void generarStock() {
//	
//	Random random = new Random();
//	
//	MonedaDAO monedadao = FactoryDAO.getMonedaDAO();
//	StockDAO stockdao = FactoryDAO.getStockDAO();
//	
//	List<Moneda> list = monedadao.listarMonedas();
//	for (Moneda mon : list)
//		stockdao.update(new Stock (random.nextFloat(1000),mon));
//
//}
//
//public static void listarStock() {
//	Scanner in =MyScanner.getScanner();
//	
//	System.out.println("Seleccione el criterio a filtrar el Stock :");
//	System.out.println("1. Cantidad (Descendente)");
//	System.out.println("2. Nomenclatura");
//	
//	int choice = in.nextInt();
//	if (!(choice == 1 || choice == 2)) {
//		System.out.println("Opcion no valida .");
//		return;
//	}
//	
//	Comparator <Stock> comp;
//	if (choice == 1)
//		comp=new ComparadorStockPorCantidadDescendente();
//	else //choice == 2
//		comp=new ComparadorStockPorNomenclatura();
//		
//	StockDAO stockdao = FactoryDAO.getStockDAO();
//	List<Stock> list = stockdao.listarStock();
//	list.sort(comp);
//	
//	for (Stock stock : list) 
//		System.out.println("["+stock.getMoneda().getNomenclatura()+"] - Cantidad :"+ stock.getCantidad());
//	
//}
//public static void generarActivo() {
//	
//	Scanner in =MyScanner.getScanner();
//	
//    System.out.println("Ingrese la nomenclatura de la moneda para crear el activo:");
//    String nomenclatura = in.next().toUpperCase();
//    
//    MonedaDAO monedadao = FactoryDAO.getMonedaDAO();
//    Moneda mon = monedadao.find(nomenclatura); 
//
//    if (mon == null) {
//        System.out.println("No se encontró la nomenclatura en la tabla moneda.");
//        return;
//    }
//
//    System.out.println("Ingrese la cantidad del activo:");
//    float cantidad;
//    
//    try {
//        cantidad = in.nextFloat();
//    } catch (InputMismatchException e) {
//        System.out.println("Error: La cantidad debe ser un número decimal.");
//        in.nextLine(); //Limpio buffer
//        return;
//    }
//    in.nextLine();
//    if (mon instanceof Criptomoneda)
//    	crearActivoCripto((Criptomoneda)mon, cantidad); 
//    else
//    	if (mon instanceof MonedaFiat)
//    		crearActivoFiat((MonedaFiat)mon,cantidad);
//
//}
//
//private static void crearActivoCripto(Criptomoneda mon, float cantidad) {
//	
//    ActivoCriptoDAO actdao = FactoryDAO.getActivoCriptoDAO();
//    
//    ActivoCripto activo = new ActivoCripto(cantidad, mon, "DireccionRandom");
//    
//    if (actdao.find(mon.getNomenclatura())==null)
//    	actdao.create(activo);
//    else 
//    	System.out.println("El activoCripto ya existia.");
//
//}
//
//private static void crearActivoFiat(MonedaFiat mon, float cantidad) {
//	
//    ActivoFiatDAO actdao = FactoryDAO.getActivoFiatDAO();
//    
//    ActivoFiat activo = new ActivoFiat(cantidad, mon);
//    
//    if (actdao.find(mon.getNomenclatura())==null) 
//    	actdao.create(activo); 
//    else 
//    	System.out.println("El activoFiat ya existia.");
//}
//
//public static void listarMisActivos() {
//	
//		Scanner in =MyScanner.getScanner();
//		
//		System.out.println("Seleccione el criterio para listar los activos :");
//		System.out.println("1.Cantidad (Descendente)");
//		System.out.println("2.Nomenclatura");
//		
//		int choice;
//		try {
//			choice = in.nextInt(); 
//		} catch (InputMismatchException e) {
//			System.err.println("Error : Se esperaba un entero");
//			in.nextLine();
//			return;
//		}
//		
//		in.nextLine(); //Limpio buffer
//		
//		if (!(choice == 1 || choice == 2)) {
//			System.out.println(" Seleccion distinta de 1 o 2");
//			return;
//		}
//		Comparator <Activo> comp;
//		Comparator <ActivoCripto> compCripto;
//		Comparator <ActivoFiat> compFiat;
//		
//		if (choice == 1) {
//			compCripto = new ComparadorActivoCriptoPorCantidadDescendente();
//			compFiat = new ComparadorActivoFiatPorCantidadDescendente();
//		}else {
//			compCripto = new ComparadorActivoCriptoPorNomenclatura();
//			compFiat = new ComparadorActivoFiatPorNomenclatura();
//			}
//		
//		List <ActivoCripto> listCripto = FactoryDAO.getActivoCriptoDAO().listarActivosCriptos();
//		List <ActivoFiat> listFiat = FactoryDAO.getActivoFiatDAO().listarActivosFiat();
//		
//		listCripto.sort(compCripto);
//		
//		System.out.println("Lista de ActivosCripto:");
//		for (ActivoCripto act : listCripto) {
//			System.out.println("["+act.getCripto().getNomenclatura()+"] - Cantidad :"+ act.getAmount());
//		}
//		
//		listFiat.sort(compFiat);
//		
//		System.out.println("Lista de ActivosFiat:");
//		for (ActivoFiat act : listFiat) {
//			System.out.println("["+act.getMonedaFiat().getNomenclatura()+"] - Cantidad :"+ act.getAmount());
//		}
//		
//}
//
//
//public static void simularCompra() {
//	
//	MonedaDAO mondao = FactoryDAO.getMonedaDAO();
//	ActivoFiatDAO actfiatdao = FactoryDAO.getActivoFiatDAO();
//	ActivoCriptoDAO actcriptodao = FactoryDAO.getActivoCriptoDAO();
//	
//	Scanner in = MyScanner.getScanner();
//	
//	System.out.println("Ingrese la nomenclatura de la criptomoneda a comprar:");
//	String nomCripto =in.next().toUpperCase();
//	
//	Moneda monCripto = mondao.find(nomCripto);
//	
//	if (monCripto == null || monCripto instanceof MonedaFiat) {
//		System.out.println("Nomenclatura no existente dentro de la tabla o se ingreso una FIAT.");
//		return;
//	}
//	
//	System.out.println("Ingrese la nomenclatura de la moneda FIAT con la que hara la compra:");
//	String nomFiat = in.next();
//	Moneda monFiat = mondao.find(nomFiat);
//	
//	if (monFiat == null) {
//		System.out.println("Nomenclatura no existente dentro de la tabla.");
//		return;
//	}
//	
//	System.out.println("Ingrese la cantidad de "+ monFiat.getNomenclatura()+" que desea utilizar para la compra.");
//	float cantFiat = in.nextFloat();
//	in.nextLine();
//	
//	if (cantFiat < 0) {
//		System.out.println("La cantidad debe ser mayor a 0.");
//		return;
//	}
//	
//	System.out.println("Ingrese si desea confirmar la compra (Y/N)");
//	String choice = in.next().toUpperCase();
//	if ( !(choice.equals("Y")) ) {
//		System.out.println("Se ha cancelado la operacion.");
//		return;
//	}
//		
//
//	//Chequeo Stock disponible para el usuario y si existe el activo.
//	ActivoFiat activoFiat = actfiatdao.find(nomFiat);
//	
//	if (activoFiat == null || activoFiat.getAmount() < cantFiat) {
//		System.out.println("Dinero insuficiente o activo Fiat inexistente. La operacion no se ha realizado");
//		return;
//	}
//	
//	StockDAO stockdao = FactoryDAO.getStockDAO();
//	
//	float cantEq = Moneda.convertir(cantFiat, monFiat, monCripto);
//	
//	Stock stock = stockdao.find(nomCripto);
//	
//	if (stock !=null) {
//		if (stock.getCantidad() < cantEq) {
//			System.out.println("No hay stock suficiente en la billetera para realizar la compra.");
//			return;
//		}
//	}else {
//		System.out.println("No se encontro el stock en la tabla asociado a la cripto.");
//		return;
//	}
//	
//	//Chequeo si existe el activoCripto
//	
//	if ( actcriptodao.find(nomCripto) == null ) //No existe el activo cripto a comprar , lo creo
//		actcriptodao.create(new ActivoCripto(0.f,(Criptomoneda)monCripto,"DireccionRandom"));
//	
//	//Actualizacion de activos
//	
//	actcriptodao.incrementarCantidad(nomCripto,cantEq);
//	actfiatdao.incrementarCantidad(nomFiat, -cantFiat);
//	
//	//Actualizacion de stock
//	stockdao.incrementarCantidad(nomCripto, -cantEq);
//	
//	TransaccionDAO transdao = FactoryDAO.getTransaccionDAO();
//	
//	String mensaje = String.format("Se realizo una compra de %.2f %s por su equivalente en %s de %.2f", cantFiat, nomFiat, nomCripto, cantEq);
//	transdao.create(new Transaccion(mensaje ,"Compra",LocalDateTime.now()));
//
//}
//
//public static void simularSwap() {
//	
//	Scanner in = MyScanner.getScanner();
//    
//    System.out.println("Ingrese la criptomoneda a convertir (nomenclatura): ");
//    String nomenclaturaOrigen = in.next().toUpperCase();
//    
//    System.out.println("Ingrese la cantidad a convertir: ");
//    float cantidadOrigen = in.nextFloat();
//    in.nextLine(); //Limpio buffer
//    
//   
//    System.out.println("Ingrese la criptomoneda a cambiar (nomenclatura): ");
//    String nomenclaturaDestino = in.next().toUpperCase();
//    
//    ActivoCriptoDAO actcriptodao = FactoryDAO.getActivoCriptoDAO();
//    
//    ActivoCripto activoOrigen = actcriptodao.find(nomenclaturaOrigen);
//    ActivoCripto activoDestino = actcriptodao.find(nomenclaturaDestino);
//
//   
//    if (activoOrigen == null) {
//        System.out.println("La criptomoneda a convertir no está en sus activos.");
//        return;
//    }
//    
//    if (activoDestino == null) {
//        System.out.println("La criptomoneda a cambiar no está en sus activos.");
//        return;
//    }
//    
//   
//    float cantidadDestino = Moneda.convertir(cantidadOrigen, activoOrigen.getCripto(), activoDestino.getCripto());
//    
//    StockDAO stockdao = FactoryDAO.getStockDAO();
//    
//    Stock stockDestino = stockdao.find(activoDestino.getCripto().getNomenclatura());
//    
//    if (stockDestino.getCantidad() < cantidadDestino) {
//        System.out.println("No hay suficiente stock disponible para realizar el swap.");
//        return;
//    }
//
//    
//    System.out.println("Desea realizar el swap? (Y/N)");
//    String confirmacion = in.next();
//    
//    if (!confirmacion.equals("Y")) {
//        System.out.println("Operación cancelada.");
//        return;
//    }
//
//    
//    activoOrigen.setAmount(activoOrigen.getAmount() - cantidadOrigen);
//    activoDestino.setAmount(activoDestino.getAmount() + cantidadDestino);
//
//    
//    actcriptodao.update(activoOrigen);
//    actcriptodao.update(activoDestino);
//    
//    stockdao.incrementarCantidad(nomenclaturaDestino, -cantidadDestino);
//    
//    Transaccion transaccion = new Transaccion("Swap de " + cantidadOrigen + " " + nomenclaturaOrigen + " a " + cantidadDestino + " " + nomenclaturaDestino,
//                                                "Swap",
//                                                LocalDateTime.now());
//    
//    TransaccionDAO transacciondao = FactoryDAO.getTransaccionDAO();
//    transacciondao.create(transaccion);
//
//    System.out.println("Swap realizado con éxito: " + cantidadOrigen + " " + nomenclaturaOrigen + " a " + cantidadDestino + " " + nomenclaturaDestino);
//
//	}
//		
//private static void creacionDeTablasEnBD() {
//    Connection con = MyConnection.getConnection();
//    Statement stmt = null;
//    try {
//        stmt = con.createStatement();
//        
//        String sql = "CREATE TABLE IF NOT EXISTS MONEDA (" +
//                     "tipo VARCHAR(30) NOT NULL, " +
//                     "nombre VARCHAR(50) NOT NULL, " +
//                     "nomenclatura VARCHAR(10) PRIMARY KEY NOT NULL, " +
//                     "valor_dolar REAL NOT NULL, " +
//                     "volatilidad REAL NULL);";
//        stmt.executeUpdate(sql);
//        
//        sql = "CREATE TABLE IF NOT EXISTS ACTIVO_CRIPTO (" +
//              "nomenclatura VARCHAR(5) PRIMARY KEY NOT NULL, " +
//              "cantidad REAL NOT NULL, " +
//              "direccion TEXT NULL);";
//        stmt.executeUpdate(sql);
//        
//        sql = "CREATE TABLE IF NOT EXISTS ACTIVO_FIAT (" +
//              "nomenclatura VARCHAR(10) PRIMARY KEY NOT NULL, " +
//              "cantidad REAL NOT NULL);";
//        stmt.executeUpdate(sql);
//        
//        sql = "CREATE TABLE IF NOT EXISTS TRANSACCION (" +
//        	      "id INTEGER PRIMARY KEY AUTOINCREMENT, " +  
//        	      "resumen VARCHAR(1000) NOT NULL, " +
//        	      "fecha_hora DATETIME NOT NULL, " +
//        	      "tipo VARCHAR(30) NOT NULL" +
//        	      ");";
//        stmt.executeUpdate(sql);
//        
//        sql = "CREATE TABLE IF NOT EXISTS STOCK (" +
//              "nomenclatura VARCHAR(10) PRIMARY KEY NOT NULL, " +
//              "cantidad REAL NOT NULL);";
//        stmt.executeUpdate(sql);
//
//    } catch (SQLException e) {
//        System.err.println("Error al crear las tablas en la base de datos: " + e.getMessage());
//    } finally {
//        try {
//            if (stmt != null) stmt.close();
//        } catch (SQLException e) {
//            System.err.println("Error al cerrar el Statement: " + e.getMessage());
//        }
//    }
//}
//		
//}
//
package sistema;


