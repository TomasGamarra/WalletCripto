package implementaciones_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Sistema.Transaccion;
import gestores.MyConnection;
import interfaces_DAO.TransaccionDAO;

public class TransaccionDAOjdbc implements TransaccionDAO {

	@Override
	public void create(Transaccion transaccion) {
		String sql = "INSERT INTO TRANSACCION (resumen, fecha_hora, tipo) VALUES (?, ?, ?)";
		try {
			Connection con = MyConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, transaccion.getResumen());
			ps.setString(2, transaccion.getFecha().toString());
			ps.setString(3,transaccion.getTipo());
			
			int filasAfectadas =ps.executeUpdate();
			if (filasAfectadas < 0) {
				throw new SQLException ("Error al insertar Transaccion , ninguna fila fue afectada");
			}
			
			
		} catch (SQLException e) {
			System.out.println("Error al insertar transaccionCripto"+e.getMessage());
		}
		
	}

	@Override
	public Transaccion find(String nomenclatura) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Transaccion transaccion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String nomenclatura) {
		// TODO Auto-generated method stub
		
	}

}
