package implementaciones_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Sistema.ActivoCripto;
import Sistema.ActivoFiat;
import Sistema.Criptomoneda;
import Sistema.Moneda;
import Sistema.MonedaFiat;
import gestores.FactoryDAO;
import gestores.MyConnection;
import interfaces_DAO.ActivoFiatDAO;
import interfaces_DAO.MonedaDAO;

public class ActivoFiatDAOjdbc implements ActivoFiatDAO {

	@Override
	public void create(int idUsuario,int idFiatMoneda,double cantidad) {
		String sql = "INSERT INTO ACTIVO_FIAT (ID_USUARIO,ID_MONEDAFIAT,CANTIDAD) VALUES (?, ?,?)";
		try {
			Connection con = MyConnection.getConnection();
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idUsuario);
			ps.setInt(2, idFiatMoneda);
			ps.setDouble(3, cantidad);
		
			if (ps.executeUpdate() == 0) {
				throw new SQLException ("Ninguna fila fue afectada");
			}
			
			
		} catch (SQLException e) {
			System.out.println("Error al insertar ActivoFiat :"+e.getMessage());
		}
	}

	@Override
	public ActivoFiat find(int idUsuario,int nombreFiat) {
	    ActivoFiat activoFiat = null;
	    String sql = "SELECT af.CANTIDAD, f.NOMBRE, f.NOMENCLATURA, f.VALOR_DOLAR, " +
                " f.NOMBRE_ICONO " +
                "FROM ACTIVO_FIAT af " +
                "JOIN MONEDAFIAT f ON af.ID_MONEDAFIA = f.ID " +
                "WHERE af.ID_USUARIO = ? AND f.NOMBRE = ?";

	    try  {
	    	Connection con = MyConnection.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, idUsuario); 
	        ps.setInt(2, nombreFiat); 
	        ResultSet rs = ps.executeQuery();

	       
	        if (rs.next()) {
	            float cantidad = rs.getFloat("CANTIDAD");
	            
	            MonedaFiat fiat = new MonedaFiat(
		                rs.getString("NOMBRE"),
		                rs.getString("NOMENCLATURA"),
		                rs.getFloat("VALOR_DOLAR"),
		                rs.getString("NOMBRE_ICONO")
		            );
	            
	            
	            return new ActivoFiat(cantidad, fiat);
	            
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
	public int incrementarCantidad(int idUsuario,String nomenclatura, float cantidadIncremento) {
	    String sqlSelect = "SELECT cantidad FROM ACTIVO_FIAT WHERE NOMENCLATURA = ? AND ID_USUARIO = ?";
	    String sqlUpdate = "UPDATE ACTIVO_FIAT SET CANTIDAD = ? WHERE NOMENCLATURA = ?"	;
	    
	    try {		
	        Connection con = MyConnection.getConnection();
	        PreparedStatement psSelect = con.prepareStatement(sqlSelect);
	        psSelect.setString(1, nomenclatura);
	        psSelect.setInt(2, idUsuario);

	        ResultSet rs = psSelect.executeQuery();
	        if (rs.next()) {
	            float cantidadActual = rs.getFloat("CANTIDAD");
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
	    String sql = "SELECT af.CANTIDAD, f.ID, f.NOMBRE, f.NOMENCLATURA, f.VALOR_DOLAR, " +
	                 "f.NOMBRE_ICONO " +
	                 "FROM ACTIVO_FIAT af " +
	                 "JOIN MONEDAFIAT f ON af.ID_MONEDAFIAT = f.ID";

	    List<ActivoFiat> lista = new LinkedList<>();
	    try  {
	    	Connection con = MyConnection.getConnection();
	    	PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) {
	            MonedaFiat fiat = new MonedaFiat(
	                rs.getString("NOMBRE"),
	                rs.getString("NOMENCLATURA"),
	                rs.getFloat("VALOR_DOLAR"),
	                rs.getString("NOMBRE_ICONO")
	            ); 
	            ActivoFiat activo = new ActivoFiat(rs.getFloat("CANTIDAD"), fiat);
	            lista.add(activo);
	        }

	    } catch (SQLException e) {
	        System.out.println("Error al listar activos cripto: " + e.getMessage());
	    }
	    return lista;
	}

}
