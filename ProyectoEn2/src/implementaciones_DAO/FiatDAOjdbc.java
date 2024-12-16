package implementaciones_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import gestores.MyConnection;
import interfaces_DAO.FiatDAO;
import sistema.Criptomoneda;
import sistema.MonedaFiat;

public class FiatDAOjdbc implements FiatDAO {

	@Override
	public void create(MonedaFiat monedaFiat) {
        String sql = "INSERT INTO MONEDAFIAT (NOMBRE, NOMENCLATURA, VALOR_DOLAR, NOMBRE_ICONO) "
                   + "VALUES (?, ?, ?, ?)";
        try {
        	Connection con = MyConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
          
            stmt.setString(1, monedaFiat.getNombre());
            stmt.setString(2, monedaFiat.getNomenclatura());
            stmt.setFloat(3, monedaFiat.getValorUsd());
            stmt.setString(4, monedaFiat.getNombreIcono());

            
            if(stmt.executeUpdate() < 0)
            	throw new SQLException();


        } catch (SQLException e) {
            System.out.println("Error SQL :"+e.getMessage());
        }
    }

	
	
	@Override
	public List<MonedaFiat> obtenerFiats() {
		List<MonedaFiat> lista = new LinkedList<>();
		String sql = "SELECT * FROM MONEDAFIAT";
		try {
			Connection con = MyConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MonedaFiat cripto = new MonedaFiat(rs.getString("NOMBRE"),rs.getString("NOMENCLATURA"),rs.getFloat("VALOR_DOLAR"),rs.getString("NOMBRE_ICONO"));
				lista.add(cripto);
			}
		}catch (SQLException e) {
			System.out.println("Error SQL :"+e.getMessage());
		}
		return lista;
	}
	
	@Override
    public int obtenerIdFiat(String nomenclatura) {
        int id= 0;
        String sql = "SELECT ID FROM MONEDAFIAT WHERE NOMENCLATURA = ? ";
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
	public MonedaFiat find(int idMoneda) {
	    MonedaFiat monedaFiat = null;
	    String sql = "SELECT * FROM MONEDAFIAT WHERE ID = ?";
	    
	    try {
	        Connection con = MyConnection.getConnection();
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, idMoneda);
	        ResultSet rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	            monedaFiat = new MonedaFiat(
	                rs.getString("NOMBRE"),
	                rs.getString("NOMENCLATURA"),
	                rs.getFloat("VALOR_DOLAR"),
	                rs.getString("NOMBRE_ICONO")
	            );
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al buscar moneda fiat: " + e.getMessage());
	    }
	    
	    return monedaFiat;
	}
	
}
