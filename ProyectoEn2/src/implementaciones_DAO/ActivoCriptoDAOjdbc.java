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

	public boolean create(int idUsuario, int idCriptomoneda, double cantidad) {
        Connection connection = MyConnection.getConnection();
        
        try {
           
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
	public ActivoCripto find(int idUsuario, String nombreCripto) {
	    Connection connection = MyConnection.getConnection();
	    String sql = "SELECT ac.CANTIDAD, c.NOMBRE, c.NOMENCLATURA, c.VALOR_DOLAR, " +
	                 "c.VOLATILIDAD, c.NOMBRE_ICONO " +
	                 "FROM ACTIVO_CRIPTO ac " +
	                 "JOIN CRIPTOMONEDA c ON ac.ID_CRIPTOMONEDA = c.ID " +
	                 "WHERE ac.ID_USUARIO = ? AND c.NOMBRE = ?";

	    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	        pstmt.setInt(1, idUsuario);
	        pstmt.setString(2, nombreCripto);

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
