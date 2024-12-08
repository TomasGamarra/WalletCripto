package Controlador;

import Vista.Vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Sistema.Usuario;

public class Controlador {
	private Vista vista;
	private Modelo modelo;
	
	
	public Controlador(Vista vista, Modelo modelo) {
		this.modelo= modelo;
		this.vista= vista;
		
		vista.getPanelMain().getPanelLogin().getLoginButton().addActionListener(new Boton_login());
		vista.getPanelMain().getPanelLogin().getRegisterButton().addActionListener(new Boton_vista_registrar());
		
		
		vista.getPanelMain().getPanelRegistro().getVolverButton().addActionListener(new Boton_cancelar_registro());
		vista.getPanelMain().getPanelRegistro().getRegisterButton().addActionListener(new Boton_registrar());
		//Tengo que terminar el boton registrar
		
		vista.getPanelMain().getPanelActivos().getBotonLogout().addActionListener(new Boton_Logout_Activos());
		vista.getPanelMain().getPanelActivos().getBotonCotizaciones().addActionListener(new Boton_cotizacion());
	
		
		vista.getPanelMain().getPanelActivos().getBotonHistorial().addActionListener(new Boton_trans());
		
		// Falta boton csv y boton generar datos de prueba
		
		vista.getPanelMain().getPanelHistorial().getBotonVolver().addActionListener(new Boton_salir_trans());
		
		
		//vista.getPanelMain().getPanelCotizaciones().getVolverButton().addActionListener(new Boton_salir_cotizacion());
		//new Boton_salir_cotizacion()
		//vista.getPanelMain().getPanelCotizaciones().getLogOutButton().addActionListener(new Boton_Logout_Cotizacion());
		//new Boton_Logout_Cotizacion()
		//Falta boton comprar 
		
		
		
		//vista.getPanelMain().getPanelCompra().getCancelarButton().addActionListener(new Boton_cancelar_compra());
		//Falta boton conversion, lista de monedas, boton de compra
	
	}
	
	public class Boton_vista_registrar implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			vista.cambiarCarta("registro");
		}
	}
	
	
	public class Boton_cancelar_registro implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			vista.cambiarCarta("login");
		}
	}

public class Boton_Logout_Activos implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		vista.cambiarCarta("login");
	}
	
}

public class Boton_Logout_Cotizacion implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		vista.cambiarCarta("login");
	}
	
}



public class Boton_cotizacion implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		vista.cambiarCarta("cotizacion");
	}
}

public class Boton_trans implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		vista.cambiarCarta("historial");
	}
}

public class Boton_salir_trans implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		vista.cambiarCarta("activos");
	}
}


public class Boton_salir_cotizacion implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		vista.cambiarCarta("activos");
	}
}

public class Boton_cancelar_compra implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		vista.cambiarCarta("cotizacion");
	}
}










public class Boton_login implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		if(vista.getPanelMain().getPanelLogin().getUserField().getText().isBlank() || (new String(vista.getPanelMain().getPanelLogin().getPasswdField().getText())==null)) {
			//Ver como queda centrado pq la posta ni idea
			JOptionPane.showMessageDialog(vista.getPanelMain().getPanelLogin(), "Por favor complete todos campos para proceder");
			return;}
			else {
				Usuario user = modelo.getUsuarioDao(vista.getPanelMain().getPanelLogin().getUserField().getText());
				if (user == null || !user.getPasswr().equals(new String(vista.getPanelMain().getPanelLogin().getPasswdField().getText())) ) {
					JOptionPane.showMessageDialog(vista.getPanelMain().getPanelLogin(), "Usuario o contraseña desconocidos");
					return;
				}else {
					iniciarMenu(user);
				
               }
			}
      }
	}





public class Boton_registrar implements ActionListener{
	
	
	public void actionPerformed(ActionEvent e) {
		if(vista.getPanelMain().getPanelLogin().getUserField().getText().isBlank() || (new String(vista.getPanelMain().getPanelLogin().getPasswdField().getText())==null)) {
			//Ver como queda centrado pq la posta ni idea
			JOptionPane.showMessageDialog(vista.getPanelMain().getPanelLogin(), "Por favor complete todos campos para proceder");
		return;}
			else {
			// En este caso estoy tomando como si usuario devuelve null si no existe en la base de datos
				Usuario user = modelo.getUsuarioDao(vista.getPanelMain().getPanelLogin().getUserField().getText());
				if ( user == null || user.==null) {
					JOptionPane.showMessageDialog(vista.getvistaLogin(), "Usuario o contraseña desconocidos");
					return;
				}}
				
				vista.cambiarCarta("login");
		}
	}
	

	public  void iniciarMenu(Usuario user) {
		vista.cambiarCarta("activos");
		vista.getPanelMain().getPanelActivos().getLabelNombre().setText(user.getNombre()+ ""+ user.getApellido());
		
	
	
		
} 
	
}