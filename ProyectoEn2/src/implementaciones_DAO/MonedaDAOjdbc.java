package implementaciones_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Sistema.Criptomoneda;
import Sistema.MonedaFiduciaria;
import Sistema.Moneda;
import gestores_DAO.MyConnection;
import interfaces_DAO.MonedaDAO;

public class MonedaDAOjdbc implements MonedaDAO {

	@Override
	public void create(Moneda activo) {
		String sql = "INSERT INTO MONEDA (nombre, nomenclatura, valor_dolar, volatilidad, stock, tipo) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			Connection con = MyConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, activo.getNombre());
			ps.setString(2, activo.getSigla());
			ps.setFloat(3, activo.getPrecio());
			ps.setFloat(4, activo.getVolatilidad());
			ps.setString(5, activo.getTipo());
			ps.setFloat(6, activo.getCant());
			
			int filasAfectadas =ps.executeUpdate();
			if (filasAfectadas < 0) {
				throw new SQLException ("Error al insertar ActivoCripto , ninguna fila fue afectada");
			}
			
			
		} catch (SQLException e) {
			System.out.println("Error al insertar ActivoCripto"+e.getMessage());
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
		            float cant= res.getFloat("stock");
		            
		            
		            // en SQL lite tenemos que tirar un check en la base de datos para los valores Cripto y Fiat
		            if(tipo.equals("Cripto")) {	
		            
		             moneda = new Criptomoneda(nombre, nomenclatura1, valor_dolar,volatilidad,cant);
		            }else {
		             moneda = new MonedaFiduciaria(nombre, nomenclatura1, valor_dolar,volatilidad,cant);
		            }
		            }
				
				
				
				
			} catch (SQLException e) {
				
			}
			return moneda;
		}

	@Override
	public void update(Moneda activo) {
		String sql = "UPDATE MONEDA  SET nombre= ?, nomenclatura = ?, valor_dolar = ?, volatilidad = ?, stock = ?, tipo = ?";
		try {
			Connection con = MyConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, activo.getNombre());
			ps.setString(2, activo.getSigla());
			ps.setFloat(3, activo.getPrecio());
			ps.setFloat(4, activo.getVolatilidad());
			ps.setString(5, activo.getTipo());
			ps.setFloat(6, activo.getCant());
			
			int res = ps.executeUpdate();
			
			if (res < 0) {
				throw new SQLException ("Error al insertar ActivoCripto , ninguna fila fue afectada");
			}
			
			
		} catch (SQLException e) {
			System.out.println("Error al insertar ActivoCripto"+e.getMessage());
		}
		
	}

	@Override
	public void delete(String nomenclatura) {
		String sql= "DELETE FROM MONEDA WHERE nomenclatura ?";
		try {
			Connection con = MyConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, nomenclatura);
			
			int res= ps.executeUpdate();
			
			if(res != 0) {
				System.out.print("Se elimino la moneda");
			}else {
				System.out.print("No se pudo eliminar la moneda porque no se encontro");
			}
			
		}catch (SQLException e) {
			
		}
		
	}

}
