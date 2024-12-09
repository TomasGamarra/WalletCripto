package implementaciones_DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import gestores.FactoryDAO;
import gestores.MyConnection;
import interfaces_DAO.UsuarioDAO;
import Sistema.ActivoFiat;
import Sistema.MonedaFiat;
import Sistema.Persona;
import Sistema.Usuario;


public class UsuarioDAOjdbc implements UsuarioDAO{
	
	@Override
	public void create (int idPersona, String email, String passwr, boolean terminos) {
		String sql = "INSERT INTO USUARIO (ID_PERSONA,EMAIL,PASSWORD,ACEPTA_TERMINOS) VALUES (?, ?,?,?)";
		try {
			Connection con = MyConnection.getConnection();
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idPersona);
			ps.setString(2, email);
			ps.setString(3, passwr);
			ps.setBoolean(4, terminos);
		
			if (ps.executeUpdate() == 0) {
				throw new SQLException ("Ninguna fila fue afectada");
			}
			
			
		} catch (SQLException e) {
			System.out.println("Error al insertar Usuario :"+e.getMessage());
		}
		
	}

	@Override
	public Usuario find(int idPersona, String nombre, String apellido) {
		 Usuario user = null;
		    String sql = "SELECT u.EMAIL, u.PASSWORD, u.ACEPTA_TERMINOS, p.NOMBRES, " +
	                " p.APELLIDOS " +
	                "FROM USUARIO u " +
	                "JOIN PERSONA p ON u.ID_PERSONA = p.ID " +
	                "WHERE u.ID_PERSONA = ? AND p.NOMBRES = ?  AND p.APELLIDOS = ?";

		    try  {
		    	Connection con = MyConnection.getConnection();
		        PreparedStatement ps = con.prepareStatement(sql);
		        ps.setInt(1, idPersona); 
		        ps.setString(2, nombre);
		        ps.setString(3, apellido);
		        ResultSet rs = ps.executeQuery();

		       
		        if (rs.next()) {
		        	
		        	int id = idPersona; 
					String mail= rs.getString("EMAIL");
					String passwr= rs.getString("PASSWORD");
		            boolean terminos = rs.getBoolean("ACEPTA_TERMINOS");
		            
		            Persona per = new Persona(
		            		 rs.getString("NOMBRES"),
							 rs.getString("APELLIDOS")
			            );
		            
		            user = new Usuario(id, mail,passwr,terminos, per);
		            
		            
		            return user;
		            
		    }
		        } catch (SQLException e) {
		        System.out.println("Error al buscar ActivoFiat: " + e.getMessage());
		    }

		    return user; 
	}
	
	
}