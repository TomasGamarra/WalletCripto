package implementaciones_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Sistema.ActivoCripto;
import Sistema.ActivoFiat;
import Sistema.Moneda;
import Sistema.MonedaFiat;
import gestores.FactoryDAO;
import gestores.MyConnection;
import interfaces_DAO.ActivoFiatDAO;
import interfaces_DAO.MonedaDAO;

public class ActivoFiatDAOjdbc implements ActivoFiatDAO {

	@Override
	public void create(ActivoFiat activo) {
		String sql = "INSERT INTO ACTIVO_FIAT (nomenclatura, cantidad) VALUES (?, ?)";
		try {
			Connection con = MyConnection.getConnection();
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, activo.getMonedaFiat().getNomenclatura());
			ps.setFloat(2, activo.getAmount());
		
			if (ps.executeUpdate() == 0) {
				throw new SQLException ("Ninguna fila fue afectada");
			}
			
			
		} catch (SQLException e) {
			System.out.println("Error al insertar ActivoFiat :"+e.getMessage());
		}
	}

	@Override
	public ActivoFiat find(String nomenclatura) {
	    ActivoFiat activoFiat = null;
	    String sql = "SELECT cantidad FROM ACTIVO_FIAT WHERE nomenclatura = ?";

	    try  {
	    	Connection con = MyConnection.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, nomenclatura); 
	        ResultSet rs = ps.executeQuery();

	       
	        if (rs.next()) {
	            float cantidad = rs.getFloat("cantidad");
	            Moneda moneda =  FactoryDAO.getMonedaDAO().find(nomenclatura);
	            if(moneda != null && moneda instanceof MonedaFiat)
	            	activoFiat = new ActivoFiat(cantidad,(MonedaFiat)moneda );
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al buscar ActivoFiat: " + e.getMessage());
	    }

	    return activoFiat; 
	}

	@Override
	public void update(ActivoFiat activo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String nomenclatura) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int incrementarCantidad(String nomenclatura, float cantidadIncremento) {
	    String sqlSelect = "SELECT cantidad FROM ACTIVO_FIAT WHERE nomenclatura = ?";
	    String sqlUpdate = "UPDATE ACTIVO_FIAT SET cantidad = ? WHERE nomenclatura = ?";
	    
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
	                return 0; // Actualizacion exitosa
	            }
	        } else {
	            return -1; // No se encontro la moneda
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al incrementar cantidad en ACTIVO_FIAT: " + e.getMessage());
	    }
	    return -1;
	}

	@Override
	public List<ActivoFiat> listarActivosFiat() {
		String sql = "SELECT * FROM ACTIVO_FIAT";
		List <ActivoFiat> list = new LinkedList<>();
		try {
			Connection con = MyConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			list = new LinkedList<ActivoFiat>();
			MonedaDAO mondao = FactoryDAO.getMonedaDAO();
			Moneda mon;
			while (rs.next()) {
				mon = mondao.find(rs.getString("nomenclatura"));
				if (mon != null && mon instanceof MonedaFiat)
					list.add(new ActivoFiat(rs.getFloat("cantidad"),(MonedaFiat)mon ));
			}
			
		}catch (SQLException e) {
			System.out.println("Error SQL :"+e.getMessage());
		}
		return list;
	}

}
