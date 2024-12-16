package implementaciones_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import gestores.FactoryDAO;
import gestores.MyConnection;
import interfaces_DAO.ActivoFiatDAO;
import interfaces_DAO.MonedaDAO;
import sistema.ActivoCripto;
import sistema.ActivoFiat;
import sistema.Criptomoneda;
import sistema.Moneda;
import sistema.MonedaFiat;

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
	public ActivoFiat find(int idUsuario,String nombreFiat) {
	    ActivoFiat activoFiat = null;
	    String sql = "SELECT af.CANTIDAD, f.NOMBRE, f.NOMENCLATURA, f.VALOR_DOLAR, " +
                " f.NOMBRE_ICONO " +
                "FROM ACTIVO_FIAT af " +
                "JOIN MONEDAFIAT f ON af.ID_MONEDAFIAT = f.ID " +
                "WHERE af.ID_USUARIO = ? AND f.NOMBRE = ?";

	    try  {
	    	Connection con = MyConnection.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, idUsuario); 
	        ps.setString(2, nombreFiat); 
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
	public int incrementarCantidad(int idUsuario, int idMonedaFiat, float cantidadIncremento) {
	    String sqlSelect = "SELECT CANTIDAD FROM ACTIVO_FIAT WHERE ID_MONEDAFIAT = ? AND ID_USUARIO = ?";
	    String sqlUpdate = "UPDATE ACTIVO_FIAT SET CANTIDAD = ? WHERE ID_MONEDAFIAT = ? AND ID_USUARIO = ?";

	    try {
	        Connection con = MyConnection.getConnection();
	        PreparedStatement psSelect = con.prepareStatement(sqlSelect);
	        psSelect.setInt(1, idMonedaFiat);
	        psSelect.setInt(2, idUsuario);

	        ResultSet rs = psSelect.executeQuery();
	        if (rs.next()) {
	            float cantidadActual = rs.getFloat("CANTIDAD");
	            float nuevaCantidad = cantidadActual + cantidadIncremento;

	            PreparedStatement psUpdate = con.prepareStatement(sqlUpdate);
	            psUpdate.setFloat(1, nuevaCantidad);
	            psUpdate.setInt(2, idMonedaFiat);
	            psUpdate.setInt(3, idUsuario);
	            
	            int filasAfectadas = psUpdate.executeUpdate();
	            if (filasAfectadas > 0) {
	                return 0; // Actualización exitosa
	            }
	        } else {
	            return -1; // No se encontró la moneda
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
	
	public List<ActivoFiat> obtenerActivosFiatPorUsuario(int idUsuario) {
        List<ActivoFiat> activosFiat = new ArrayList<>();
        String sql = "SELECT a.CANTIDAD, a.ID_MONEDAFIAT, m.NOMBRE, m.NOMENCLATURA, m.VALOR_DOLAR, m.NOMBRE_ICONO " +
                     "FROM ACTIVO_FIAT a " +
                     "JOIN MONEDAFIAT m ON a.ID_MONEDAFIAT = m.ID " +
                     "WHERE a.ID_USUARIO = ?";

        try {
        	Connection con = MyConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            // Establecer el idUsuario en la consulta
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) { 
                float cantidad = rs.getFloat("CANTIDAD");
                int idMonedaFiat = rs.getInt("ID_MONEDAFIAT");
                String nombreMonedaFiat = rs.getString("NOMBRE");
                String nomenclatura = rs.getString("NOMENCLATURA");
                float valorDolar = rs.getFloat("VALOR_DOLAR");
                String nombreIcono = rs.getString("NOMBRE_ICONO");

                MonedaFiat monedaFiat = new MonedaFiat(nombreMonedaFiat,nomenclatura,  valorDolar,nombreIcono );

                ActivoFiat activoFiat = new ActivoFiat(cantidad, monedaFiat);

                activosFiat.add(activoFiat);
            }

        } catch (SQLException e) {
        	System.out.println("Error al listar activos cripto: " + e.getMessage());
        }

        return activosFiat;
    }
	
	@Override
	public void eliminarActivosFiatPorUsuario(int idUsuario) {
	    String sql = "DELETE FROM ACTIVO_FIAT WHERE ID_USUARIO = ?";
	    try  {
	    	Connection con = MyConnection.getConnection();
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, idUsuario);
	        pstmt.executeUpdate();
	
	    } catch (SQLException e) {
	        System.out.println("Error al eliminar activos fiat: " + e.getMessage());
	    }
	}


}
