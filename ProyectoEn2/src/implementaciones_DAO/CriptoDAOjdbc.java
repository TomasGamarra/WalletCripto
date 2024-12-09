package implementaciones_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
