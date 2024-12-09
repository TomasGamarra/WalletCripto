package implementaciones_DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

	 public int create(Usuario usuario) {
	        
	        String sql = "INSERT INTO USUARIO (email, password, acepta_terminos, id_persona) VALUES (?, ?, ?, ?)";
	        
	        try  {
	        	Connection con = MyConnection.getConnection();
	            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	           
	            stmt.setString(1, usuario.getEmail());
	            stmt.setString(2, usuario.getPassword());
	            stmt.setBoolean(3, usuario.isAceptoTerminos());
	            stmt.setInt(4, usuario.getPersona().getId()); 
	            
	   	            
	            if (stmt.executeUpdate() > 0) {
	            	ResultSet generatedKeys = stmt.getGeneratedKeys();
	                 if (generatedKeys.next()) {
	                     int idGenerado = generatedKeys.getInt(1);
	                     usuario.setIdUsuario(idGenerado); 
	                     return idGenerado; 
	                    
	                }
	            }
	        } catch (SQLException e) {
	            System.out.println("Error SQL : "+e.getMessage());
	        }
	        
	        return -1; 
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
	
	public Usuario findByEmail(String email) {
	    String sql = "SELECT u.ID, u.EMAIL, u.PASSWORD, u.ACEPTA_TERMINOS, " +
	                 "p.NOMBRES, p.APELLIDOS " +
	                 "FROM USUARIO u " +
	                 "JOIN PERSONA p ON u.ID_PERSONA = p.ID " +
	                 "WHERE u.EMAIL = ?";

	    try  {
	    	Connection con = MyConnection.getConnection();
	        PreparedStatement stmt = con.prepareStatement(sql);
	        stmt.setString(1, email);
	        
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	             int idUsuario = rs.getInt("ID");
	             String password = rs.getString("PASSWORD");
	             boolean aceptoTerminos = rs.getBoolean("ACEPTA_TERMINOS");
	             String nombre = rs.getString("NOMBRES");
	             String apellido = rs.getString("APELLIDOS");
	             Persona persona = new Persona(nombre, apellido);
	             return new Usuario(idUsuario, email, password, aceptoTerminos, persona);
	         }
	        
	    } catch (SQLException e) {
	        System.out.println("Error al buscar el usuario: " + e.getMessage());
	    }

	    return null; // Devuelve null si no se encuentra el usuario
	}

	
	
}