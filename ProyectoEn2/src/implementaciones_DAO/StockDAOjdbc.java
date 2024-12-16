package implementaciones_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import Sistema.Criptomoneda;
import Sistema.Moneda;
import Sistema.MonedaFiat;
import Sistema.Stock;
import gestores.FactoryDAO;
import gestores.MyConnection;
import interfaces_DAO.MonedaDAO;
import interfaces_DAO.StockDAO;

public class StockDAOjdbc implements StockDAO {

	@Override
	public void create(int idCripto, double cantidad) {
		String sql = "INSERT INTO STOCK (ID_CRIPTOMONEDA, CANTIDAD) VALUES (?,?)";
		try {
			Connection con = MyConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1,idCripto);
			ps.setDouble(2,cantidad);
			
			if (ps.executeUpdate() < 0)  {
				throw new SQLException("No se afecto ninguna fila") ;
			}
			
		}catch (SQLException e) {
			System.out.println("Error al crear stock : "+e.getMessage());
		}
	
	}

	@Override
	public Stock find(int idCripto) {
	    Stock stock = null;
	    String sql = "SELECT s.CANTIDAD, c.NOMBRE, c.NOMENCLATURA, c.VALOR_DOLAR, " +
	                 "c.VOLATILIDAD, c.NOMBRE_ICONO " +
	                 "FROM STOCK s " +
	                 "JOIN CRIPTOMONEDA c ON s.ID_CRIPTOMONEDA = c.ID " +
	                 "WHERE s.ID_CRIPTOMONEDA = ?";

	    try {
	        Connection con = MyConnection.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, idCripto);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            float cantidad = rs.getFloat("CANTIDAD");
	            Criptomoneda cripto = new Criptomoneda(
	                rs.getString("NOMBRE"),
	                rs.getString("NOMENCLATURA"),
	                rs.getFloat("VALOR_DOLAR"),
	                rs.getFloat("VOLATILIDAD"),
	                rs.getString("NOMBRE_ICONO")
	            );
	            stock = new Stock(cantidad, cripto);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al buscar stock: " + e.getMessage());
	    }

	    return stock;
	}


	@Override
	public void delete(String nomenclatura) {
		// TODO Auto-generated method stub
		
	}

	public void update(Stock stock) {
		String sql=  "UPDATE STOCK SET cantidad = ? WHERE nomenclatura = ? ";

		try {
			Connection con = MyConnection.getConnection();
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setFloat(1, stock.getCantidad());
			ps.setString(2, stock.getMoneda().getNomenclatura());
	
			if (ps.executeUpdate()< 0) 
				throw new SQLException ("Ninguna fila fue afectada");
			
			
		} catch (SQLException e) {
			System.out.println("Error al actualizar stock: "+e.getMessage());
	}
}
@Override
	public void incrementarCantidad(int idCripto, float cantidadASumar) {
	    String sqlSelect = "SELECT cantidad FROM STOCK WHERE ID_CRIPTOMONEDA = ?";
	    String sqlUpdate = "UPDATE STOCK SET CANTIDAD = ? WHERE ID_CRIPTOMONEDA = ?";
	    
	    try {
	        Connection con = MyConnection.getConnection();
	        
	        // Seleccionar la cantidad actual
	        PreparedStatement psSelect = con.prepareStatement(sqlSelect);
	        psSelect.setInt(1, idCripto);
	        ResultSet rs = psSelect.executeQuery();

	        if (rs.next()) {
	            // Obtener la cantidad actual y sumar el valor proporcionado
	            float cantidadActual = rs.getFloat("CANTIDAD");
	            float nuevaCantidad = cantidadActual + cantidadASumar;

	            // Actualizar la cantidad en la base de datos
	            PreparedStatement psUpdate = con.prepareStatement(sqlUpdate);
	            psUpdate.setFloat(1, nuevaCantidad);
	            psUpdate.setInt(2, idCripto);
	            
	            if (psUpdate.executeUpdate() < 0) {
	                throw new SQLException("No se afectó ninguna fila");
	            }

	        } else {
	            System.out.println("No se encontró stock para la nomenclatura: " + idCripto);
	        }
	        
	    } catch (SQLException e) {
	        System.out.println("Error al incrementar cantidad de stock: " + e.getMessage());
	    }
	}

	public List<Stock> listarStock () { 
		String sql = "SELECT s.CANTIDAD, c.NOMBRE, c.NOMENCLATURA , C.VALOR_DOLAR," +
	    		"c.VOLATILIDAD, c.NOMBRE_ICONO" +
	    		"FROM STOCK s"+
	    		"JOIN CRIPTOMONEDA c ON s.ID_CRIPTOMONEDA = c.ID";
		
		
		List<Stock> list = new LinkedList<Stock>();
		try {
			Connection con = MyConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				 Criptomoneda cripto = new Criptomoneda(
		  	                rs.getString("NOMBRE"),
		  	                rs.getString("NOMENCLATURA"),
		  	                rs.getFloat("VALOR_DOLAR"),
		  	                rs.getFloat("VOLATILIDAD"),
		  	                rs.getString("NOMBRE_ICONO")
		  	                );
				  
		         float cant = rs.getFloat("cantidad");
		         list.add(new Stock(cant, cripto)); 
			}
		}catch (SQLException e) {
			System.out.println("No se pudo listar el stock :"+e.getMessage());
		}
		return list;
	}
	
	
	
}