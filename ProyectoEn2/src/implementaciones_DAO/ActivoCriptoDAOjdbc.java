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
import interfaces_DAO.ActivoCriptoDAO;
import interfaces_DAO.MonedaDAO;
import sistema.ActivoCripto;
import sistema.Criptomoneda;
import sistema.Moneda;

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

	            // 
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
	public int incrementarCantidad(int idUsuario, int idCriptomoneda, float cantidadIncremento) {
	    String sqlSelect = "SELECT CANTIDAD FROM ACTIVO_CRIPTO WHERE ID_CRIPTOMONEDA = ? AND ID_USUARIO = ?";
	    String sqlUpdate = "UPDATE ACTIVO_CRIPTO SET CANTIDAD = ? WHERE ID_CRIPTOMONEDA = ? AND ID_USUARIO = ?";

	    try {
	        Connection con = MyConnection.getConnection();
	        PreparedStatement psSelect = con.prepareStatement(sqlSelect);
	        psSelect.setInt(1, idCriptomoneda); // Ahora usamos ID_CRIPTOMONEDA en lugar de nomenclatura
	        psSelect.setInt(2, idUsuario);

	        ResultSet rs = psSelect.executeQuery();
	        if (rs.next()) {
	            float cantidadActual = rs.getFloat("CANTIDAD");
	            float nuevaCantidad = cantidadActual + cantidadIncremento;

	            PreparedStatement psUpdate = con.prepareStatement(sqlUpdate);
	            psUpdate.setFloat(1, nuevaCantidad);
	            psUpdate.setInt(2, idCriptomoneda); // También actualizamos ID_CRIPTOMONEDA en lugar de nomenclatura
	            psUpdate.setInt(3, idUsuario);

	            if (psUpdate.executeUpdate() > 0) 
	                return 0; // Éxito al actualizar
	            else 
	                return -1; // Error al actualizar
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al incrementar cantidad en ACTIVO_CRIPTO: " + e.getMessage());
	    }
	    return -1; // Error si no se encontró el activo o hubo otro problema
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

	public List<ActivoCripto> obtenerActivosCriptoPorUsuario(int idUsuario) {
        List<ActivoCripto> activosCripto = new ArrayList<>();
        String sql = "SELECT a.CANTIDAD, a.ID_CRIPTOMONEDA, c.NOMBRE, c.NOMENCLATURA, c.VALOR_DOLAR, c.NOMBRE_ICONO " +
                     "FROM ACTIVO_CRIPTO a " +
                     "JOIN CRIPTOMONEDA c ON a.ID_CRIPTOMONEDA = c.ID " +
                     "WHERE a.ID_USUARIO = ?";

        try  {
        	Connection con = MyConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

           
            while (rs.next()) {
                float cantidad = rs.getFloat("CANTIDAD");
                int idCripto = rs.getInt("ID_CRIPTOMONEDA");
                String nombreCripto = rs.getString("NOMBRE");
                String nomenclatura = rs.getString("NOMENCLATURA");
                float valorDolar = rs.getFloat("VALOR_DOLAR");
                String nombreIcono = rs.getString("NOMBRE_ICONO");

                Criptomoneda cripto = new Criptomoneda(nombreCripto, nomenclatura,valorDolar,1,nombreIcono );

                ActivoCripto activoCripto = new ActivoCripto(cantidad, cripto, "DireccionAleatoria");  //Ver tema de la direccion

       
                activosCripto.add(activoCripto);
            }

        } catch (SQLException e) {
           System.out.println("Error SQL :"+e.getMessage());
        }

        return activosCripto;
    }
	
	@Override
	public void update(int idUsuario, int idCriptomoneda, float cantidad) {
		//Codigo del update
		
	}

	
	public void eliminarActivosCriptoPorUsuario(int idUsuario) {
	    String sql = "DELETE FROM ACTIVO_CRIPTO WHERE ID_USUARIO = ?";
	    try  {
	    	Connection con = MyConnection.getConnection();
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, idUsuario);
	        pstmt.executeUpdate();
	        
	    } catch (SQLException e) {
	        System.out.println("Error al eliminar activos cripto: " + e.getMessage());
	    }
	}

	

	



}
