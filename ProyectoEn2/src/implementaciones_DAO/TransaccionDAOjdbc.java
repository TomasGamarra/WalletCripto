package implementaciones_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import Sistema.Persona;
import Sistema.Transaccion;
import Sistema.Usuario;
import gestores.MyConnection;
import interfaces_DAO.TransaccionDAO;

public class TransaccionDAOjdbc implements TransaccionDAO {

	@Override
	public void create(Transaccion transaccion) {
		String sql = "INSERT INTO TRANSACCION (RESUMEN, FECHA_HORA, TIPO, ID_USUARIO) VALUES (?, ?, ? ,?)";
		try {
			Connection con = MyConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, transaccion.getResumen());
			ps.setTimestamp(2, transaccion.getFecha());
			ps.setString(3,transaccion.getTipo());
			ps.setInt(4, transaccion.getUsuario().getIdUsuario());
			
			if (ps.executeUpdate() < 0) {
				throw new SQLException ("Error al insertar Transaccion , ninguna fila fue afectada");
			}
			
			
		} catch (SQLException e) {
			System.out.println("Error al insertar transaccionCripto"+e.getMessage());
		}
		
	}

	@Override
	public List<Transaccion> find(int idUsuario) {
		List<Transaccion> transacciones = new LinkedList<>();
		String sql = "SELECT t.RESUMEN, t.TIPO, t.FECHA_HORA, u.ID, u.EMAIL, u.PASSWORD, u.ACEPTA_TERMINOS, " +
                "p.NOMBRES, p.APELLIDOS " +
                "FROM TRANSACCION t " +
                "JOIN USUARIO u ON t.ID_USUARIO = u.ID " +
                "JOIN PERSONA p ON u.ID_PERSONA = p.ID " +
                "WHERE t.ID_USUARIO = ?";

		try  {
			Connection con = MyConnection.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, idUsuario); 
       
			ResultSet rs = stmt.executeQuery(); 

			while (rs.next()) {
				String resumen = rs.getString("RESUMEN");
				String tipo = rs.getString("TIPO");
				Timestamp fecha = rs.getTimestamp("FECHA_HORA");
           
				int usuarioId = rs.getInt("ID");
				String email = rs.getString("EMAIL");
				String password = rs.getString("PASSWORD");
				boolean aceptoTerminos = rs.getBoolean("ACEPTA_TERMINOS");
				String nombre = rs.getString("NOMBRES");
	            String apellido = rs.getString("APELLIDOS");

	           Persona persona = new Persona(nombre, apellido);

	           Usuario usuario = new Usuario(usuarioId, email, password, aceptoTerminos, persona);

	           Transaccion transaccion = new Transaccion(resumen, tipo, fecha, usuario);
  
	           transacciones.add(transaccion);
       }
	}
       catch (SQLException e) {
    	   System.out.println("Error al buscar transacciones :"+e.getMessage());
   }

   return transacciones; // Devuelve la lista de transacciones
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
