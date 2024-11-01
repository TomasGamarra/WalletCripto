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
import Sistema.Stock;
import gestores.FactoryDAO;
import gestores.MyConnection;
import interfaces_DAO.MonedaDAO;
import interfaces_DAO.StockDAO;

public class StockDAOjdbc implements StockDAO {

	@Override
	public void create(Stock stock) {
		String sql = "INSERT INTO STOCK (cantidad, nomenclatura) VALUES (?,?)";
		try {
			Connection con = MyConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setFloat(1,stock.getCantidad());
			ps.setString(2,stock.getMoneda().getNomenclatura());
			
			if (ps.executeUpdate() < 0)  {
				throw new SQLException("No se afecto ninguna fila") ;
			}
			
		}catch (SQLException e) {
			System.out.println("Error al crear stock : "+e.getMessage());
		}
	
	}

	@Override
	public Stock find(String nomenclatura) {
	    Stock stock = null;
	    String sql = "SELECT cantidad FROM STOCK WHERE nomenclatura = ?";

	    try  {
	    	Connection con = MyConnection.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, nomenclatura);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	              float cantidad = rs.getFloat("cantidad");
	              Moneda moneda = FactoryDAO.getMonedaDAO().find(nomenclatura);
	                
	              if (moneda != null) {
	                  stock = new Stock(cantidad, moneda);
	               }
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
	public void incrementarCantidad(String nomenclatura, float cantidadASumar) {
	    String sqlSelect = "SELECT cantidad FROM STOCK WHERE nomenclatura = ?";
	    String sqlUpdate = "UPDATE STOCK SET cantidad = ? WHERE nomenclatura = ?";
	    
	    try {
	        Connection con = MyConnection.getConnection();
	        
	        // Seleccionar la cantidad actual
	        PreparedStatement psSelect = con.prepareStatement(sqlSelect);
	        psSelect.setString(1, nomenclatura);
	        ResultSet rs = psSelect.executeQuery();

	        if (rs.next()) {
	            // Obtener la cantidad actual y sumar el valor proporcionado
	            float cantidadActual = rs.getFloat("cantidad");
	            float nuevaCantidad = cantidadActual + cantidadASumar;

	            // Actualizar la cantidad en la base de datos
	            PreparedStatement psUpdate = con.prepareStatement(sqlUpdate);
	            psUpdate.setFloat(1, nuevaCantidad);
	            psUpdate.setString(2, nomenclatura);
	            
	            if (psUpdate.executeUpdate() < 0) {
	                throw new SQLException("No se afectó ninguna fila");
	            }

	        } else {
	            System.out.println("No se encontró stock para la nomenclatura: " + nomenclatura);
	        }
	        
	    } catch (SQLException e) {
	        System.out.println("Error al incrementar cantidad de stock: " + e.getMessage());
	    }
	}

	public List<Stock> listarStock () { 
		String sql = "SELECT * FROM STOCK";
		List<Stock> list = new LinkedList<Stock>();
		try {
			Connection con = MyConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			MonedaDAO dao = FactoryDAO.getMonedaDAO();
			while (rs.next()) {
				 String nomenclatura = rs.getString("nomenclatura");  
		         float cant = rs.getFloat("cantidad");
		         Moneda mon = dao.find(nomenclatura);
		         list.add(new Stock(cant, mon)); 
			}
		}catch (SQLException e) {
			System.out.println("No se pudo listar el stock :"+e.getMessage());
		}
		return list;
	}
	
	
	
}
