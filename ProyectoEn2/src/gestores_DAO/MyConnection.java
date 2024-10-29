package gestores_DAO;
import java.sql.*;
public class MyConnection {
 private static Connection connection = null; 
 
 static { //Se ejecuta cuando la clase se carga en memoria , previo a cualquier llamado a metodo o instanciacion (En este caso no hay x ser Singleton)
	 try {
		 connection = DriverManager.getConnection("jdbc:sqlite:src/BaseDeDatos.db");
	  } catch (java.sql.SQLException e) {
		 System.out.println("Error de SQL: "+e.getMessage());
      } 
}

public static Connection getConnection() {
		 return connection;
 }
 
 private MyConnection() {
 	
 }

}


