package implementaciones_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Sistema.Transaccion;
import gestores_DAO.MyConnection;
import interfaces_DAO.TransaccionDAO;

public class TransaccionDAOjdbc implements TransaccionDAO {

	@Override
	public void create(Transaccion activo) {
		String sql = "INSERT INTO TRANSACCION (resumen, fecha_hora, tipo) VALUES (?, ?, ?)";
		try {
			Connection con = MyConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, activo.getEstado());
			ps.setString(2, "activo.getFecha().getDia()" + "/" + "activo.getFecha().getMes()" + "/" + "activo.getFecha().getAnio()" + "	" + "activo.getFecha().getHora()" + ":" + "activo.getFecha().getMin()");
			ps.setString(3,activo.getIdentificador());
			
			int filasAfectadas =ps.executeUpdate();
			if (filasAfectadas < 0) {
				throw new SQLException ("Error al insertar ActivoCripto , ninguna fila fue afectada");
			}
			
			
		} catch (SQLException e) {
			System.out.println("Error al insertar ActivoCripto"+e.getMessage());
		}
		
	}

	@Override
	public Transaccion find(String nomenclatura) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Transaccion activo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String nomenclatura) {
		// TODO Auto-generated method stub
		
	}

}
