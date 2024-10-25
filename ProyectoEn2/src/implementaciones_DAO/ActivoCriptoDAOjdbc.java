package implementaciones_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Sistema.ActivoCripto;
import gestores_DAO.MyConnection;
import interfaces_DAO.ActivoCriptoDAO;

public class ActivoCriptoDAOjdbc implements ActivoCriptoDAO {

	@Override
	public void create(ActivoCripto activo) {
		String sql = "INSERT INTO ACTIVO_CRIPTO (nomenclatura, cantidad, direccion) VALUES (?, ?, ?, ?)";
		try {
			Connection con = MyConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, activo.getMoneda().getSigla());
			ps.setFloat(2, activo.getAmount());
			ps.setString(3,activo.getDireccion());
			
			int filasAfectadas =ps.executeUpdate();
			if (filasAfectadas < 0) {
				throw new SQLException ("Error al insertar ActivoCripto , ninguna fila fue afectada");
			}
			
			
		} catch (SQLException e) {
			System.out.println("Error al insertar ActivoCripto"+e.getMessage());
		}
	}

	//@Override
//	public ActivoCripto find(String nomenclatura) {
//		String sql = "SELECT * FROM ACTIVO_CRIPTO WHERE nomenclatura=?";
//		try {
//			Connection con = MyConnection.getConnection();
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setString(1,nomenclatura);
//			ResultSet res = ps.executeQuery();
//			res.next();
//			ActivoCripto act = new ActivoCripto(res.getFloat("cantidad"),res.getString(0),res.getString("direccion"));;
//			
//			
//		}catch (SQLException e) {
//			
//		}
//		return null;
//	}

	@Override
	public void update(ActivoCripto activo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String nomenclatura) {
		// TODO Auto-generated method stub
		
	}

}
