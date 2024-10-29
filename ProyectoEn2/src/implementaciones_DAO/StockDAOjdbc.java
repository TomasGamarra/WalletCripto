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
import gestores_DAO.FactoryDAO;
import gestores_DAO.MyConnection;
import interfaces_DAO.MonedaDAO;
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

	public void updateCant(Stock stock) {
		String sql=  "UPDATE STOCK SET cantidad = ? WHERE nomenclatura = ? ";
		Random random = new Random();
		Float rdm = random.nextFloat(300.00f);
		try {
			Connection con = MyConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setFloat(1, rdm);
			ps.setString(2, stock.getMoneda().getNombre());
			int res = ps.executeUpdate();
			
			if (res < 0) {
				throw new SQLException ("Error al cambiar Stock , ninguna fila fue afectada");
			}
			
			
		} catch (SQLException e) {
	}
}

	public List<Stock> listarStock () { 
		String sql = "SELECT * FROM STOCK";
		List<Stock> list = new LinkedList<Stock>();
		Stock stk ;
		try {
			Connection con = MyConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			// Necesito usar el factory DAO para que me devuelva la moneda cripto lo hago cuando llego a casa
			while (rs.next()) {
				 String nomenclatura = rs.getString("nomenclatura");  
		         float cant = rs.getFloat("cantidad");
		         MonedaDAO dao = FactoryDAO.getMonedaDAO();
		         Moneda mon = dao.find(nomenclatura);
		            stk = new Stock(  cant, mon);
		         list.add(stk);  
			}
		}catch (SQLException e) {
			System.out.println("No se pudo listar las monedas: "+e.getMessage());
		}
		return list;
	}
	
	
	
}
