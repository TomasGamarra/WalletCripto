package implementaciones_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import gestores.FactoryDAO;
import gestores.MyConnection;
import interfaces_DAO.PersonaDAO;
import sistema.Persona;


public class PersonaDAOjdbc implements PersonaDAO {

	@Override
	public void create(String nombre, String apellido) {
		String sql= "INSERT INTO PERSONA (NOMBRES,APELLIDOS)  VALUES (?,?)";
		try {
			Connection con = MyConnection.getConnection();
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nombre);
			ps.setString(2, apellido);
		
			if (ps.executeUpdate() == 0) {
				throw new SQLException ("Ninguna fila fue afectada");
			}
			
			
		} catch (SQLException e) {
			System.out.println("Error al insertar Persona :"+e.getMessage());
		}
		
	}

	@Override
	public Persona find(String nombre, String apellido) {
		Persona per = null;
		String sql= "SELECT * WHERE NOMBRES = ? AND APELLIDOS ?";
		try {
		Connection con= MyConnection.getConnection();
		PreparedStatement ps= con.prepareStatement(sql); 	
		ps.setString(1, nombre);
		ps.setString(1, apellido);
		ResultSet res= ps.executeQuery();
		
		if(res.next()) {
			String nom= res.getString("NOMBRES");
			String ape= res.getString("APELLIDOS");
			
			per = new Persona(nom,ape);
		}
		
		}catch (SQLException e) {
	        System.out.println("Error al buscar ActivoFiat: " + e.getMessage());
	    }
		return per;
	}
	
	public void create(Persona persona) {
	    String sql = "INSERT INTO PERSONA (NOMBRES, APELLIDOS) VALUES (?, ?)";
	    
	    try  {
	    	Connection con = MyConnection.getConnection();
	        PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        
	        stmt.setString(1, persona.getNombre());
	        stmt.setString(2, persona.getApellido());
	        
	        
	        if (stmt.executeUpdate() > 0) {
	           ResultSet rs = stmt.getGeneratedKeys();
	           if (rs.next()) {
	               persona.setId(rs.getInt(1));  // Asignar el ID al objeto 
	            }
	        }
	        
	    } catch (SQLException e) {
	        System.out.println("Error al crear Persona: " + e.getMessage());
	    }
	
	
	}
}