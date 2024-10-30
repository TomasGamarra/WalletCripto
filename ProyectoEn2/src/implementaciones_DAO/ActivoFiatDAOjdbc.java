package implementaciones_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Sistema.ActivoCripto;
import Sistema.ActivoFiat;
import gestores_DAO.MyConnection;
import interfaces_DAO.ActivoFiatDAO;

public class ActivoFiatDAOjdbc implements ActivoFiatDAO {

	@Override
	public void create(ActivoFiat activo) {
		String sql = "INSERT INTO ACTIVO_FIAT (nomenclatura, cantidad) VALUES (?, ?)";
		try {
			Connection con = MyConnection.getConnection();
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, activo.getMoneda().getNomenclatura());
			ps.setFloat(2, activo.getAmount());
		
			if (ps.executeUpdate() < 0) {
				throw new SQLException ("Ninguna fila fue afectada");
			}
			
			
		} catch (SQLException e) {
			System.out.println("Error al insertar ActivoCripto :"+e.getMessage());
		}
	}

	@Override
	public ActivoFiat find(String nomenclatura) {
		return null;
	}

	@Override
	public void update(ActivoFiat activo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String nomenclatura) {
		// TODO Auto-generated method stub
		
	}

}
