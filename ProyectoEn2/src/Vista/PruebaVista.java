package Vista;



import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import Controlador.Controlador;
import Sistema.Modelo;
import gestores.MyConnection;

public class PruebaVista {
    public static void main(String[] args) {
    	try {
    	creacionDeTablasEnBD(MyConnection.getConnection()); 
    	} catch (SQLException e) {
    		System.out.println("Error SQL: "+e.getMessage());
    	}
    	Vista vista =new Vista();
    	Modelo modelo = new Modelo();
    	Controlador cont = new Controlador(vista,modelo);
    	
    }

    /**
    * Este método se encarga de la creación de las tablas.
    *
    * @param connection objeto conexion a la base de datos SQLite
    * @throws SQLException
    */
    private static void creacionDeTablasEnBD(Connection connection) throws SQLException {
    	Statement stmt;
    	stmt = connection.createStatement();
    	String sql = "CREATE TABLE  IF NOT EXISTS PERSONA "
    			+ "("
    			+ " ID       INTEGER   PRIMARY KEY AUTOINCREMENT NOT NULL , "
    			+ " NOMBRES       VARCHAR(50)    NOT NULL, "
    			+ " APELLIDOS       VARCHAR(50)    NOT NULL "
    			+ ")";
    	
    	stmt.executeUpdate(sql);
    	
    	sql = "CREATE TABLE  IF NOT EXISTS USUARIO " + "(" 
    			+ " ID       INTEGER   PRIMARY KEY AUTOINCREMENT NOT NULL , "
    			+ " ID_PERSONA       INTEGER   NOT NULL, "
    			+ " EMAIL       VARCHAR(50)    NOT NULL, "
    			+ " PASSWORD       VARCHAR(50)    NOT NULL, "
    			+ " ACEPTA_TERMINOS       BOOLEAN    NOT NULL, "
    			+ " FOREIGN KEY(ID_PERSONA) REFERENCES PERSONA(ID)"
    			+ ")";
    	
    	stmt.executeUpdate(sql);
    			
    	sql = "CREATE TABLE  IF NOT EXISTS CRIPTOMONEDA "
    			+ "("
    			+ " ID       INTEGER   PRIMARY KEY AUTOINCREMENT NOT NULL , "
    			+ " NOMBRE       VARCHAR(50)    NOT NULL, "
    			+ " NOMENCLATURA VARCHAR(10)  NOT NULL, "
    			+ " VALOR_DOLAR	REAL     NOT NULL, "
    			+ " VOLATILIDAD	REAL     NULL, "
    			+ " NOMBRE_ICONO       VARCHAR(50)    NOT NULL, "
    			+ " CONSTRAINT unique_constraint UNIQUE (NOMBRE, NOMENCLATURA)"
    			+ ")";
    	
    	stmt.executeUpdate(sql);
    	
    	sql= "CREATE TABLE  IF NOT EXISTS MONEDAFIAT "
    			+ "("
    			+ " ID       INTEGER   PRIMARY KEY AUTOINCREMENT NOT NULL , "
    			+ " NOMBRE       VARCHAR(50)    NOT NULL, "
    			+ " NOMENCLATURA VARCHAR(10)  NOT NULL, "
    			+ " VALOR_DOLAR	REAL     NOT NULL, "    			
    			+ " NOMBRE_ICONO       VARCHAR(50)    NOT NULL, "
    			+ " CONSTRAINT unique_constraint UNIQUE (NOMBRE, NOMENCLATURA)"
    			+ ")";
    	
    	stmt.executeUpdate(sql);
    	
    	sql = "CREATE TABLE  IF NOT EXISTS ACTIVO_CRIPTO"
    			+ "("
    			+ " ID       INTEGER   PRIMARY KEY AUTOINCREMENT NOT NULL , "
    			+ " ID_USUARIO INTEGER    NOT NULL, "
    			+ " ID_CRIPTOMONEDA INTEGER    NOT NULL, "
    			+ " CANTIDAD	REAL    NOT NULL, "
    			+ " FOREIGN KEY(ID_USUARIO) REFERENCES USUARIO(ID),"
    			+ " FOREIGN KEY(ID_CRIPTOMONEDA) REFERENCES CRIPTOMONEDA(ID) "
    			+ ")";
    	
    	stmt.executeUpdate(sql);
    	
    	sql = "CREATE TABLE  IF NOT EXISTS ACTIVO_FIAT"
    			+ "("
    			+ " ID       INTEGER   PRIMARY KEY AUTOINCREMENT NOT NULL , "
    			+ " ID_USUARIO INTEGER    NOT NULL, "
    			+ " ID_MONEDAFIAT INTEGER    NOT NULL, "
    			+ " CANTIDAD	REAL    NOT NULL, "
    			+ " FOREIGN KEY(ID_USUARIO) REFERENCES USUARIO(ID),"
    			+ " FOREIGN KEY(ID_MONEDAFIAT) REFERENCES MONEDAFIAT(ID)"
    			+ ")";
    	
    	stmt.executeUpdate(sql);
    	
    	sql = "CREATE TABLE  IF NOT EXISTS TRANSACCION"
    			+ "("
    			+ " ID     INTEGER   PRIMARY KEY AUTOINCREMENT NOT NULL , "
    			+ " RESUMEN VARCHAR(1000)   NOT NULL, "
    			+ " TIPO VARCHAR(20) NOT NULL,"
    			+ " FECHA_HORA		DATETIME  NOT NULL, " 
    			+ " ID_USUARIO INTEGER    NOT NULL, "
    			+ " FOREIGN KEY(ID_USUARIO) REFERENCES USUARIO(ID)"
    			+ ")";
    	
    	stmt.executeUpdate(sql);
    	
    	sql = "CREATE TABLE IF NOT EXISTS STOCK"
    	+ "("
    	+ " ID     INTEGER   PRIMARY KEY AUTOINCREMENT NOT NULL , "
    	+ " ID_CRIPTOMONEDA INTEGER NOT NULL ,"
    	+ " CANTIDAD REAL NOT NULL ,"
    	+ " CONSTRAINT unique_constraint UNIQUE (ID_CRIPTOMONEDA),"
    	+ " FOREIGN KEY(ID_CRIPTOMONEDA) REFERENCES CRIPTOMONEDA(ID)"
    	+ ")";
    	
    	stmt.executeUpdate(sql);
    	
    	stmt.close();
    }

}

 