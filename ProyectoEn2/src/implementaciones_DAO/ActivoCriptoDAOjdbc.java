package implementaciones_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Sistema.ActivoCripto;
import Sistema.Criptomoneda;
import Sistema.Moneda;
import gestores.FactoryDAO;
import gestores.MyConnection;
import interfaces_DAO.ActivoCriptoDAO;
import interfaces_DAO.MonedaDAO;

public class ActivoCriptoDAOjdbc implements ActivoCriptoDAO {

	@Override
	public void create(ActivoCripto activo) {
		String sql = "INSERT INTO ACTIVO_CRIPTO (nomenclatura, cantidad, direccion) VALUES (?, ?, ?)";
		PreparedStatement ps;
		try {
			Connection con = MyConnection.getConnection();
			
			ps = con.prepareStatement(sql);
			
			ps.setString(1, activo.getCripto().getNomenclatura());
			ps.setFloat(2, activo.getAmount());
			ps.setString(3,activo.getDireccion());
			
		
			if (ps.executeUpdate() <= 0) 
				throw new SQLException ("Ninguna fila fue afectada");
			
			
			
		} catch (SQLException e) {
			System.out.println("Error al insertar ActivoCripto :"+e.getMessage());
		}
	}

	@Override
	public ActivoCripto find(String nomenclatura) {
	    ActivoCripto activoCripto = null;
	    String sql = "SELECT cantidad, direccion FROM ACTIVO_CRIPTO WHERE nomenclatura = ?";

	    try {
	        Connection con = MyConnection.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, nomenclatura);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            // Recuperar los valores de cantidad y dirección
	            float cantidad = rs.getFloat("cantidad");
	            String direccion = rs.getString("direccion");

	            // Obtener la moneda correspondiente desde el DAO
	            MonedaDAO monedaDAO = FactoryDAO.getMonedaDAO();
	            Moneda moneda = monedaDAO.find(nomenclatura);

	            if (moneda != null && moneda instanceof Criptomoneda) {
	                activoCripto = new ActivoCripto(cantidad, (Criptomoneda) moneda, direccion);
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al buscar ActivoCripto: " + e.getMessage());
	    }

	    return activoCripto;
	}


	@Override
	public void update(ActivoCripto activo) {
	    String sql = "UPDATE ACTIVO_CRIPTO SET cantidad = ?, direccion = ? WHERE nomenclatura = ?";
	    try {
	        Connection con = MyConnection.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);

	        // Configurar los parámetros del PreparedStatement
	        ps.setFloat(1, activo.getAmount());
	        ps.setString(2, activo.getDireccion());
	        ps.setString(3, activo.getCripto().getNomenclatura());

	        

	        if (ps.executeUpdate() <= 0) {
	            throw new SQLException("No se actualizó ninguna fila");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al actualizar ActivoCripto: " + e.getMessage());
	    }
	}


	@Override
	public void delete(String nomenclatura) {
		// Codigo del Delete
		
	}
	
	@Override
	public int incrementarCantidad(String nomenclatura, float cantidadIncremento) {
	    String sqlSelect = "SELECT cantidad FROM ACTIVO_CRIPTO WHERE nomenclatura = ?";
	    String sqlUpdate = "UPDATE ACTIVO_CRIPTO SET cantidad = ? WHERE nomenclatura = ?";
	    
	    try {
	        Connection con = MyConnection.getConnection();
	        PreparedStatement psSelect = con.prepareStatement(sqlSelect);
	        psSelect.setString(1, nomenclatura);

	        ResultSet rs = psSelect.executeQuery();
	        if (rs.next()) {
	            float cantidadActual = rs.getFloat("cantidad");
	            float nuevaCantidad = cantidadActual + cantidadIncremento;

	            PreparedStatement psUpdate = con.prepareStatement(sqlUpdate);
	            psUpdate.setFloat(1, nuevaCantidad);
	            psUpdate.setString(2, nomenclatura);
	            
	            int filasAfectadas = psUpdate.executeUpdate();
	            if (filasAfectadas > 0) {
	                return 0; 
	            }
	        } else {
	            return -1; 
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al incrementar cantidad en ACTIVO_CRIPTO: " + e.getMessage());
	    }
	    return -1;
	}
	
	
	@Override
	public List<ActivoCripto> listarActivosCriptos() {
		String sql = "SELECT * FROM ACTIVO_CRIPTO";
		List<ActivoCripto> list = new LinkedList<>();
		Moneda mon=null;
		try {
			Connection con = MyConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			MonedaDAO mondao =FactoryDAO.getMonedaDAO();
			while (rs.next()) {
				mon = mondao.find(rs.getString("nomenclatura"));
				if(mon != null && mon instanceof Criptomoneda)
					list.add(new ActivoCripto(rs.getFloat("cantidad"),(Criptomoneda)mon, "DireccionRandom"));
			}

		}catch (SQLException e) {
			System.out.println("Error SQL :"+e.getMessage());
		}
		return list;
	}


}
