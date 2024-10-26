package implementaciones_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Sistema.Stock;
import gestores_DAO.MyConnection;
import interfaces_DAO.StockDAO;

public class StockDAOjdbc implements StockDAO {

	@Override
	public void create(Stock stock) {
		try (Connection con = MyConnection.getConnection();){
			String sql = "INSERT INTO STOCK (cantidad, nomenclatura) VALUES (?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setFloat(1,stock.getCantidad());
			ps.setString(2,stock.getMoneda().getSigla());
			
			if (ps.executeUpdate() < 0)  {
				System.out.println("No se afecto ninguna fila");
				throw new SQLException() ;
			}
			
		}catch (SQLException e) {
			System.out.println("Error al crear stock : "+e.getMessage());
		}
	
	}

	@Override
	public Stock find(String nomenclatura) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Stock stock) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String nomenclatura) {
		// TODO Auto-generated method stub
		
	}

}
