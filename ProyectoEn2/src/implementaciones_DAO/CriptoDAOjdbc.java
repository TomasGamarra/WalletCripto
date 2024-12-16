package implementaciones_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Sistema.Criptomoneda;
import gestores.MyConnection;
import interfaces_DAO.CriptoDAO;

public class CriptoDAOjdbc implements CriptoDAO {

	@Override
	public void create(Criptomoneda moneda) {
        String sql = "INSERT INTO CRIPTOMONEDA (NOMBRE, NOMENCLATURA, VALOR_DOLAR, VOLATILIDAD, NOMBRE_ICONO) "
                   + "VALUES (?, ?, ?, ?, ?)";

        // Conexión a la base de datos
        try {
        	Connection con = MyConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);

            // Establecer los parámetros del PreparedStatement
            pstmt.setString(1, moneda.getNombre());
            pstmt.setString(2, moneda.getNomenclatura());
            pstmt.setFloat(3, moneda.getValorUsd());
            pstmt.setFloat(4, moneda.getVolatilidad()); 
            pstmt.setString(5, moneda.getNombreIcono());

           
            if ( pstmt.executeUpdate() < 0 )
            	throw new SQLException ("Ninguna fila fue afectada");

        } catch (SQLException e) {
            System.out.println("Error SQL:"+ e.getMessage());
        }
    }
	
	public List<Criptomoneda> obtenerCriptomonedas () {
		List<Criptomoneda> lista = new LinkedList<>();
		String sql = "SELECT * FROM CRIPTOMONEDA";
		try {
			Connection con = MyConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Criptomoneda cripto = new Criptomoneda(rs.getString("NOMBRE"),rs.getString("NOMENCLATURA"),rs.getFloat("VALOR_DOLAR"),rs.getFloat("VOLATILIDAD"),rs.getString("NOMBRE_ICONO"));
				lista.add(cripto);
			}
		}catch (SQLException e) {
			System.out.println("Error SQL :"+e.getMessage());
		}
		return lista;
	}
	
	@Override
    public int obtenerIdCripto(String nomenclatura) {
            int id= 0;
            String sql = "SELECT ID FROM CRIPTOMONEDA WHERE NOMENCLATURA = ? ";
            try  {
                Connection con = MyConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, nomenclatura);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                      id = rs.getInt("ID");
                  }

                } catch (SQLException e) {
                System.out.println("Error al buscar id: " + e.getMessage());
            }

            return id;
        }
	

	@Override
	public Criptomoneda find(int idCripto) {
	    Criptomoneda cripto = null;
	    String sql = "SELECT * FROM CRIPTOMONEDA WHERE ID = ?";
	    
	    try {
	        Connection con = MyConnection.getConnection();
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, idCripto);
	        ResultSet rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	            cripto = new Criptomoneda(
	                rs.getString("NOMBRE"),
	                rs.getString("NOMENCLATURA"),
	                rs.getFloat("VALOR_DOLAR"),
	                rs.getFloat("VOLATILIDAD"),
	                rs.getString("NOMBRE_ICONO")
	            );
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al buscar criptomoneda: " + e.getMessage());
	    }
	    
	    return cripto;
	}
}
