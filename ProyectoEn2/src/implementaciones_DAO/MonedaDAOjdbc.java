package implementaciones_DAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Sistema.Criptomoneda;
import Sistema.GestorStock;
import Sistema.MonedaFiat;
import Sistema.Sistema;
import gestores.MyConnection;
import Sistema.Moneda;
import interfaces_DAO.MonedaDAO;

public class MonedaDAOjdbc implements MonedaDAO {

	@Override
	public void create(Moneda moneda) {
		String sql = "INSERT INTO MONEDA (nombre, nomenclatura, valor_dolar, volatilidad, tipo) VALUES (?, ?, ?, ?, ?)";
		try {
			Connection con = MyConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			//Extraigo valores comunes a las monedas
			ps.setString(1, moneda.getNombre());
			ps.setString(2, moneda.getNomenclatura());
			ps.setFloat(3, moneda.getValorUsd());
			
			//Volatilidad dependiendo del tipo de moneda
			if (moneda instanceof Criptomoneda )
				ps.setFloat(4, ((Criptomoneda)moneda).getVolatilidad());
			else
				ps.setNull(4, java.sql.Types.FLOAT);	
			
			ps.setString(5, moneda.getClass().getSimpleName()); //CHECKED ?
			//Chequeo de errores
			int filasAfectadas =ps.executeUpdate();
			if (filasAfectadas <= 0) 
				throw new SQLException ("Error al insertar moneda , ninguna fila fue afectada");
			
		} catch (SQLException e) {
			System.out.println("Error al insertar moneda"+e.getMessage());
		}
	}

	@Override
	public Moneda find(String nomenclatura) {
		String sql = "SELECT * FROM MONEDA WHERE nomenclatura=?";
		Moneda moneda= null;
		try {
			Connection con= MyConnection.getConnection();
			PreparedStatement ps= con.prepareStatement(sql); 	
			ps.setString(1, nomenclatura);
			ResultSet res= ps.executeQuery();	
		    if (res.next()) {   
		      String nomenclatura1 = res.getString("nomenclatura"); 
		      String nombre = res.getString("nombre"); 
		      float valor_dolar = res.getFloat("valor_dolar"); 
		      float volatilidad = res.getFloat("volatilidad");
		      String tipo = res.getString("tipo"); 
		      // en SQL lite tenemos que tirar un check en la base de datos para los valores Cripto y Fiat
		      if(tipo.equals("Criptomoneda")) 
		    	  moneda = new Criptomoneda(nombre, nomenclatura1, valor_dolar,volatilidad);
		      else 
		    	  if (tipo.equals("MonedaFiat"))
		    		  moneda = new MonedaFiat(nombre, nomenclatura1, valor_dolar);
		    }
				
		} catch (SQLException e) {
				System.out.println("Error al buscar en la tabla MONEDA:"+e.getMessage());
		}
		return moneda;
	}

//	@Override
	public void update(Moneda moneda) {
//		String sql = "UPDATE MONEDA SET nombre= ?, nomenclatura = ?, valor_dolar = ?, volatilidad = ?, stock = ?, tipo = ?";
//		try (Connection con = MyConnection.getConnection();
//			PreparedStatement ps = con.prepareStatement(sql);){
//			if (moneda.)
//			ps.setString(1, moneda.getNombre());
//			ps.setString(2, moneda.getSigla());
//			ps.setFloat(3, moneda.getValorUsd());
//			ps.setString(5, moneda.getTipo());
//			ps.setFloat(6, moneda.getCant());
//			
//			int res = ps.executeUpdate();
//			
//			if (res < 0) {
//				throw new SQLException ("Error al insertar monedaCripto , ninguna fila fue afectada");
//			}
//			
//			
//		} catch (SQLException e) {
//			System.out.println("Error al insertar monedaCripto"+e.getMessage());
//		}
//		
	}

	@Override
	public void delete(String nomenclatura) {
		String sql= "DELETE FROM MONEDA WHERE nomenclatura=?";
		try {
			Connection con = MyConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nomenclatura);
			
			if(ps.executeUpdate() != 0) {
				System.out.print("Se elimino la moneda");
			}else {
				System.out.print("No se pudo eliminar la moneda porque no se encontro");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Moneda> listarMonedas () { 
		String sql = "SELECT * FROM MONEDA";
		List<Moneda> list = new LinkedList<Moneda>();
		Moneda mon ;
		try {
			Connection con = MyConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				 String nomenclatura = rs.getString("nomenclatura"); 
		         String nombre = rs.getString("nombre"); 
		         float valor_dolar = rs.getFloat("valor_dolar"); 
		         float volatilidad = rs.getFloat("volatilidad");
		         String tipo = rs.getString("tipo");
		           // en SQL lite tenemos que tirar un check en la base de datos para los valores Cripto y Fiat
		         if(tipo.equals("Criptomoneda")) 
		            mon = new Criptomoneda(nombre, nomenclatura, valor_dolar,volatilidad);
		         else 
		            mon = new MonedaFiat(nombre, nomenclatura, valor_dolar);
		         list.add(mon);  
			}
		}catch (SQLException e) {
			System.out.println("No se pudo listar las monedas: "+e.getMessage());
		}
		return list;
	}
	
	public String obtenerTipoMoneda(String nomenclatura) {
	    String tipo = null;
	    try  {
	    	Connection con = MyConnection.getConnection();
	        PreparedStatement stmt = con.prepareStatement("SELECT tipo FROM Moneda WHERE nomenclatura = ?");
	        stmt.setString(1, nomenclatura);
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            tipo = rs.getString("tipo");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al obtener tipo de moneda: " + e.getMessage());
	    }
	    return tipo;
	}

}
