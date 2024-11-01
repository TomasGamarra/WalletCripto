package implementaciones_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Sistema.ActivoCripto;
import Sistema.ActivoFiat;
import Sistema.Moneda;
import gestores.FactoryDAO;
import gestores.MyConnection;
import interfaces_DAO.ActivoFiatDAO;
import interfaces_DAO.MonedaDAO;

public class ActivoFiatDAOjdbc implements ActivoFiatDAO {

	@Override
	public void create(ActivoFiat activo) {
		String sql = "INSERT INTO ACTIVO_FIAT (nomenclatura, cantidad) VALUES (?, ?)";
		try {
			Connection con = MyConnection.getConnection();
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, activo.getMoneda().getNomenclatura());
			ps.setFloat(2, activo.getAmount());
		
			if (ps.executeUpdate() == 0) {
				throw new SQLException ("Ninguna fila fue afectada");
			}
			
			
		} catch (SQLException e) {
			System.out.println("Error al insertar ActivoFiat :"+e.getMessage());
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

	@Override
	public List<ActivoFiat> listarActivosFiat() {
		String sql = "SELECT * FROM ACTIVO_FIAT";
		List <ActivoFiat> list = new LinkedList<>();
		try {
			Connection con = MyConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			list = new LinkedList<ActivoFiat>();
			MonedaDAO mondao = FactoryDAO.getMonedaDAO();
			Moneda mon;
			while (rs.next()) {
				mon = mondao.find(rs.getString("nomenclatura"));
				if (mon != null)
					list.add(new ActivoFiat(rs.getFloat("cantidad"),mon ));
			}
			
		}catch (SQLException e) {
			System.out.println("Error SQL :"+e.getMessage());
		}
		return list;
	}

}
