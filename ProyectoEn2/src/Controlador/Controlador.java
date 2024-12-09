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
		
		//vista.getPanelMain().getPanelLogin().getLoginButton().addActionListener(new Boton_login());
		vista.getPanelMain().getPanelLogin().getRegisterButton().addActionListener(new Boton_vista_registrar());
		
		
		vista.getPanelMain().getPanelRegistro().getVolverButton().addActionListener(new Boton_cancelar_registro());
		vista.getPanelMain().getPanelRegistro().getRegisterButton().addActionListener(new Boton_registrar());
		
		
		vista.getPanelMain().getPanelActivos().getBotonLogout().addActionListener(new Boton_Logout_Activos());
		vista.getPanelMain().getPanelActivos().getBotonCotizaciones().addActionListener(new Boton_cotizacion());
		vista.getPanelMain().getPanelActivos().getBotonExportar().addActionListener(new Boton_exportar);
	
		
		vista.getPanelMain().getPanelActivos().getBotonHistorial().addActionListener(new Boton_trans());
		
		// Falta boton csv y boton generar datos de prueba
		
		vista.getPanelMain().getPanelHistorial().getBotonVolver().addActionListener(new Boton_salir_trans());
		
		
		vista.getPanelMain().getPanelCotizaciones().getVolverButton().addActionListener(new Boton_salir_cotizacion());
		//new Boton_salir_cotizacion()
		vista.getPanelMain().getPanelCotizaciones().getLogOutButton().addActionListener(new Boton_Logout_Cotizacion());
		//new Boton_Logout_Cotizacion()
		//Falta boton comprar 
		
		
		
		vista.getPanelMain().getPanelCompra().getCancelarButton().addActionListener(new Boton_cancelar_compra());
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
		 int respuesta = JOptionPane.showConfirmDialog(
		            null, 
		            "¿Desea salir de esta sesión?", 
		            "Confirmación", 
		            JOptionPane.YES_NO_OPTION, 
		            JOptionPane.WARNING_MESSAGE
		        );

		        if (respuesta == JOptionPane.YES_OPTION) {
		        	vista.cambiarCarta("login");
		        } 
	}
	
}

public class Boton_Logout_Cotizacion implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		 int respuesta = JOptionPane.showConfirmDialog(
		            null, 
		            "¿Desea salir de esta sesión?", 
		            "Confirmación", 
		            JOptionPane.YES_NO_OPTION, 
		            JOptionPane.WARNING_MESSAGE
		        );

		        if (respuesta == JOptionPane.YES_OPTION) {
		        	vista.cambiarCarta("login");
		        } 
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
				if (user == null || !user.getPasswr().equals(new String(vista.getPanelMain().getPanelLogin().getPasswdField().getText()) || !user.getEmail().equals(vista.getPanelMain().getPanelLogin().getLabelEmail().getText())) ) {
					JOptionPane.showMessageDialog(vista.getPanelMain().getPanelLogin(), "Usuario o contraseña desconocidos");
					return;
				}else {
					iniciarMenu(user);
				
               }
			}
      }
	}





public class Boton_registrar implements ActionListener{
	//Hay q tengo q cambiar
	
	public void actionPerformed(ActionEvent e) {
		String nombre = vista.getPanelMain().getPanelRegistro().getLabelNombre().getText();
		String apellido= vista.getPanelMain().getPanelRegistro().getLabelApellido().getText();
		String mail = vista.getPanelMain().getPanelRegistro().getLabelEmail().getText();
		String contra = vista.getPanelMain().getPanelRegistro().getLabelApellido().getText();
		String contra2 = vista.getPanelMain().getPanelRegistro().getLabelContra2().getText();
		boolean terminos= vista.getPanelMain().getPanelRegistro().getTermsCheckBox().isSelected();
		
		
			if (nombre.isEmpty() || apellido.isEmpty() || mail.isEmpty() || contra.isEmpty() || contra2.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No se han completado todos los campos.", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(!terminos) {
				JOptionPane.showMessageDialog(null, "No se aceptaron los temrinos y condiciones de la aplicacion.", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Usuario user = modelo.getUsuarioDao(mail);
			
			if( user == null) {
				JOptionPane.showMessageDialog(null, "El email referenciado ya esta en uso.", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			
			modelo.insertarUsuario(nombre,apellido,mail,contra,terminos);
			JOptionPane.showConfirmDialog(
		            null, 
		            "Usuario creado exitosamente, se le ha enviado un mail de confirmacion de cuenta para verficar su persona", 
		            "Creación de usuario", 
		            JOptionPane.OK_OPTION, 
		            JOptionPane.INFORMATION_MESSAGE
		        );
			vista.cambiarCarta("login");

			
			
		}
	}
	
	

	public  void iniciarMenu(Usuario user) {
		vista.cambiarCarta("activos");
		vista.getPanelMain().getPanelActivos().getLabelNombre().setText(user.getNombre()+ ""+ user.getApellido());
		
	
	
		
} 
	

public class  Boton_exportar implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		
		
		
	}
	
	
}

}