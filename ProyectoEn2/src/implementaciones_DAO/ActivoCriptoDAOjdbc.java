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
	public boolean create(int idUsuario, int idCriptomoneda, float cantidad) {
        try {
        	Connection connection = MyConnection.getConnection();
            String sql = "INSERT INTO ACTIVO_CRIPTO (ID_USUARIO, ID_CRIPTOMONEDA, CANTIDAD) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, idUsuario);
                pstmt.setInt(2, idCriptomoneda);
                pstmt.setDouble(3, cantidad);
                pstmt.executeUpdate();
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al crear el activo cripto: " + e.getMessage());
            return false;
        }
    }

	@Override
	public ActivoCripto find(int idUsuario, String nomenclatura) {
	    Connection connection = MyConnection.getConnection();
	    String sql = "SELECT ac.CANTIDAD, c.NOMBRE, c.NOMENCLATURA, c.VALOR_DOLAR, " +
	                 "c.VOLATILIDAD, c.NOMBRE_ICONO " +
	                 "FROM ACTIVO_CRIPTO ac " +
	                 "JOIN CRIPTOMONEDA c ON ac.ID_CRIPTOMONEDA = c.ID " +
	                 "WHERE ac.ID_USUARIO = ? AND c.NOMENCLATURA = ?";

	    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	        pstmt.setInt(1, idUsuario);
	        pstmt.setString(2, nomenclatura.toUpperCase());

	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            float cantidad = rs.getFloat("CANTIDAD");

	            Criptomoneda cripto = new Criptomoneda(
	                rs.getString("NOMBRE"),
	                rs.getString("NOMENCLATURA"),
	                rs.getFloat("VALOR_DOLAR"),
	                rs.getFloat("VOLATILIDAD"),
	                rs.getString("NOMBRE_ICONO")
	            );

	            // Generación aleatoria de dirección (simulación)
	            String direccion = generarDireccionAleatoria();

	            return new ActivoCripto(cantidad, cripto, direccion);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al buscar el activo cripto: " + e.getMessage());
	    }
	    return null;  // Si no se encuentra
	}


	private String generarDireccionAleatoria() {
		return "abcde";
	}



	@Override
	public void delete(int idUsuario ,String nomenclatura) {
		// Codigo del Delete
		
	}
	
	@Override
	public int incrementarCantidad(int idUsuario,String nomenclatura, float cantidadIncremento) {
	    String sqlSelect = "SELECT cantidad FROM ACTIVO_CRIPTO WHERE NOMENCLATURA = ? AND ID_USUARIO = ?";
	    String sqlUpdate = "UPDATE ACTIVO_CRIPTO SET CANTIDAD = ? WHERE NOMENCLATURA = ? AND ID_USUARIO = ?";
	    
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
	            psUpdate.setInt(3,idUsuario);
  
	            if (psUpdate.executeUpdate() > 0) 
	                return 0; 
	            else 
	            	return -1; 
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al incrementar cantidad en ACTIVO_CRIPTO: " + e.getMessage());
	    }
	    return -1;
	}
	
	
	@Override
	public List<ActivoCripto> listarActivosCriptos() {
	    String sql = "SELECT ac.CANTIDAD, c.ID, c.NOMBRE, c.NOMENCLATURA, c.VALOR_DOLAR, " +
	                 "c.VOLATILIDAD, c.NOMBRE_ICONO " +
	                 "FROM ACTIVO_CRIPTO ac " +
	                 "JOIN CRIPTOMONEDA c ON ac.ID_CRIPTOMONEDA = c.ID";

	    List<ActivoCripto> lista = new LinkedList<>();
	    try  {
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

	            String direccion = generarDireccionAleatoria(); 
	            ActivoCripto activo = new ActivoCripto(rs.getFloat("CANTIDAD"), cripto, direccion);
	            lista.add(activo);
	        }

	    } catch (SQLException e) {
	        System.out.println("Error al listar activos cripto: " + e.getMessage());
	    }
	    return lista;
	}

	@Override
	public void update(int idUsuario, int idCriptomoneda, float cantidad) {
		//Codigo del update
		
	}


	

	



}
