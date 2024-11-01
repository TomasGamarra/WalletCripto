package implementaciones_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Sistema.ActivoCripto;
import Sistema.Moneda;
import gestores.FactoryDAO;
import gestores.MyConnection;
import interfaces_DAO.ActivoCriptoDAO;
import interfaces_DAO.MonedaDAO;

public class ActivoCriptoDAOjdbc implements ActivoCriptoDAO {

	@Override
	public void create(ActivoCripto activo) {
		String sql = "INSERT INTO ACTIVO_CRIPTO (nomenclatura, cantidad, direccion) VALUES (?, ?, ?)";
		PreparedStatement ps;
		try {
			Connection con = MyConnection.getConnection();
			
			ps = con.prepareStatement(sql);
			
			ps.setString(1, activo.getMoneda().getNomenclatura());
			ps.setFloat(2, activo.getAmount());
			ps.setString(3,activo.getDireccion());
			
		
			if (ps.executeUpdate() <= 0) 
				throw new SQLException ("Ninguna fila fue afectada");
			
			
			
		} catch (SQLException e) {
			System.out.println("Error al insertar ActivoCripto :"+e.getMessage());
		}
	}

	@Override
	public ActivoCripto find(String nomenclatura) {
		// Codigo del Find
		return null;
	}

	@Override
	public void update(ActivoCripto activo) {
		// Codigo del Update
		
	}

	@Override
	public void delete(String nomenclatura) {
		// Codigo del Delete
		
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
				if(mon != null)
					list.add(new ActivoCripto(rs.getFloat("cantidad"),mon, "DireccionRandom"));
			}

		}catch (SQLException e) {
			System.out.println("Error SQL :"+e.getMessage());
		}
		return list;
	}


}
