package implementaciones_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import Sistema.Persona;
import gestores.FactoryDAO;
import gestores.MyConnection;
import interfaces_DAO.PersonaDAO;


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
	
	

}