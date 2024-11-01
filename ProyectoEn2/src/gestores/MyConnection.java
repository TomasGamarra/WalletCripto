package gestores;
import java.sql.*;
public class MyConnection {
 private static Connection connection = null; 
 
 static { //Se ejecuta cuando la clase se carga en memoria , previo a cualquier llamado a metodo o instanciacion (En este caso no hay x ser Singleton)
	 try {
		 connection = DriverManager.getConnection("jdbc:sqlite:src/BaseDeDatos.db");
	  } catch (SQLException e) {
		 System.out.println("Error de SQL: "+e.getMessage());
      } 
}

public static Connection getConnection() {
		 return connection;
 }
 
 private MyConnection() {
 	
 }

public static void cerrarCon() {
		try {
			
			if (connection != null) connection.close();
			
		} catch (SQLException e) {
			
			System.out.println("Error al cerrar la conexion: " + e.getMessage());	
		}
	}
}


