package implementaciones_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Sistema.MonedaFiat;
import gestores.MyConnection;
import interfaces_DAO.FiatDAO;

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

}
