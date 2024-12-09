package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import Sistema.ActivoCripto;
import Sistema.ActivoFiat;
import Sistema.Criptomoneda;
import Sistema.GestorDeUsuarioActual;
import Sistema.MonedaFiat;
import Sistema.Persona;
import Sistema.Usuario;
import Vista.ModeloTablaActivos;
import Vista.Vista;

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
		
		
		vista.getPanelMain().getPanelActivos().getBotonLogout().addActionListener(new Boton_Logout_Activos());
		vista.getPanelMain().getPanelActivos().getBotonCotizaciones().addActionListener(new Boton_cotizacion());
		
	
		
		vista.getPanelMain().getPanelActivos().getBotonHistorial().addActionListener(new Boton_trans());
		
		// Falta boton csv y boton generar datos de prueba
		vista.getPanelMain().getPanelActivos().getBotonPrueba().addActionListener(new Boton_generar_datos());
		vista.getPanelMain().getPanelActivos().getBotonExportar().addActionListener(new Boton_exportar()); //Falta implementar listener
		
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
		
		if (vista.getPanelMain().getPanelLogin().getUserField().getText().isBlank() || (new String(vista.getPanelMain().getPanelLogin().getPasswdField().getPassword()).isBlank())) {
			JOptionPane.showMessageDialog(vista.getPanelMain().getPanelLogin(), "Por favor complete todos los campos.");
			
			return;
		}
		else {
			System.out.println(vista.getPanelMain().getPanelLogin().getUserField().getText());
			Usuario user = modelo.getUsuarioDao().findByEmail(vista.getPanelMain().getPanelLogin().getUserField().getText()); // Obtengo el usuario de la DB
			if ( user == null ) {
				JOptionPane.showMessageDialog(vista.getPanelMain().getPanelLogin(), "Email no asociado a ninguna cuenta.");
			}else 
				if (!(user.getPassword().equals(new String(vista.getPanelMain().getPanelLogin().getPasswdField().getPassword()))))
					JOptionPane.showMessageDialog(vista.getPanelMain().getPanelLogin(), "Contraseña incorrecta.");	
				else {
					GestorDeUsuarioActual.setUser(user);
					iniciarMenu();
				}
             }
		
		
	}
}	
	
	public void iniciarMenu() {
		vista.cambiarCarta("activos");
		//Nombre y apellido
		String nombre = GestorDeUsuarioActual.getUser().getPersona().getNombre();
		String apellido =GestorDeUsuarioActual.getUser().getPersona().getApellido();
		vista.getPanelMain().getPanelActivos().getLabelNombre().setText(nombre+ " "+apellido );
		
		//Actualizo iniciales
		String iniciales = nombre.toUpperCase().charAt(0) +""+apellido.toUpperCase().charAt(0);
		vista.getPanelMain().getPanelActivos().getComponenteIniciales().setIniciales(iniciales); 
		
		//Actualizo tabla de activos
		List <ActivoCripto> listaCripto = modelo.getActivoCriptoDao().obtenerActivosCriptoPorUsuario(GestorDeUsuarioActual.getUser().getIdUsuario());
		List <ActivoFiat> listaFiat= modelo.getActivoFiatDao().obtenerActivosFiatPorUsuario(GestorDeUsuarioActual.getUser().getIdUsuario());
		
		cargarActivosCriptoEnTabla(listaCripto,vista.getPanelMain().getPanelActivos().getTablaActivos());
		cargarActivosFiatEnTabla(listaFiat,vista.getPanelMain().getPanelActivos().getTablaActivos());
		
		//Tendria que inicializar todos los activos vacios para despues hacer update de esos
		
		vista.getPanelMain().getPanelActivos().repaint();
		
	}
	
	private void cargarActivosCriptoEnTabla (List<ActivoCripto> activos, JTable tabla) {
		ModeloTablaActivos modelo =new ModeloTablaActivos(null);
		tabla.setModel(modelo);
			for (ActivoCripto activo : activos) {
				Object[] fila = new Object[3];
				fila[0]=new ImageIcon(activo.getCripto().getNombreIcono());
				fila[1]=activo.getCripto().getNombre();
				fila[2]=String.format("$%.2f", activo.getAmount());;
				modelo.addRow(fila);
			}
	}
	
	private void cargarActivosFiatEnTabla (List<ActivoFiat> activos, JTable tabla) {
		ModeloTablaActivos modelo =new ModeloTablaActivos(null);
		tabla.setModel(modelo);
			for (ActivoFiat activo : activos) {
				Object[] fila = new Object[3];
				fila[0]=new ImageIcon(activo.getMonedaFiat().getNombreIcono());
				fila[1]=activo.getMonedaFiat().getNombre();
				fila[2]=String.format("$%.2f", activo.getAmount());;
				modelo.addRow(fila);
			}
	}
	
	
 
	

 


public class Boton_registrar implements ActionListener{
	
	
	public void actionPerformed(ActionEvent e) {
		String nombre = vista.getPanelMain().getPanelRegistro().getFieldNombre().getText();
		String apellido= vista.getPanelMain().getPanelRegistro().getFieldApellido().getText();
		String mail = vista.getPanelMain().getPanelRegistro().getFieldEmail().getText();
		String contra = String.valueOf(vista.getPanelMain().getPanelRegistro().getFieldContra().getPassword());
		String contra2 = String.valueOf(vista.getPanelMain().getPanelRegistro().getFieldContra2().getPassword());
		boolean terminos= vista.getPanelMain().getPanelRegistro().getTermsCheckBox().isSelected();
		if (nombre.isEmpty() || apellido.isEmpty() || mail.isEmpty() || contra.isEmpty() || contra2.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No se han completado todos los campos.", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(!terminos) {
				JOptionPane.showMessageDialog(null, "No se aceptaron los terminos y condiciones de la aplicacion.", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!(contra.equals(contra2))) {
				JOptionPane.showMessageDialog(null, "La contraseña no coincide", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Usuario user = modelo.getUsuarioDao().findByEmail(mail);
			
			if( user != null) {
				JOptionPane.showMessageDialog(null, "El email ya esta en uso.", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Persona persona = new Persona(nombre,apellido);
			modelo.getPersonaDao().create(persona);
			user = new Usuario(0,mail,contra,terminos,persona);
			modelo.getUsuarioDao().create(user); 
			
			JOptionPane.showMessageDialog(
		            null, 
		            "Usuario creado exitosamente, se le ha enviado un mail de confirmacion.", 
		            "Creación de usuario", 
		            JOptionPane.NO_OPTION );
			GestorDeUsuarioActual.setUser(user);
			iniciarMenu();
				
		
		}
	}
	
public class  Boton_generar_datos implements ActionListener{
	 
		@Override
	    public void actionPerformed(ActionEvent e) {
	        Random random = new Random();

	        List<Criptomoneda> criptomonedas = modelo.getCriptoDAO().obtenerCriptomonedas();
	        List<MonedaFiat> monedasFiduciarias = modelo.getFiatDAO().obtenerFiats();

	        for (Criptomoneda cripto : criptomonedas) {
	            float cantidad = random.nextFloat() * 20;  
	            modelo.getActivoCriptoDao().create(GestorDeUsuarioActual.getUser().getIdUsuario(),modelo.getCriptoDAO().obtenerIdCripto(cripto.getNomenclatura()),cantidad); //Tendria que ser update y crearlos la primera vez (iniciarMenu)
	        }

	        for (MonedaFiat moneda : monedasFiduciarias) {
	            float cantidad = random.nextFloat() * 20;  // Cantidad aleatoria
	            modelo.getActivoFiatDao().create(GestorDeUsuarioActual.getUser().getIdUsuario(),modelo.getFiatDAO().obtenerIdFiat(), cantidad);//Tendria que ser update y crearlos la primera vez (iniciarMenu)
	        }

	        // Mensaje de confirmación
	        JOptionPane.showMessageDialog(null, "Datos de prueba generados correctamente.");
	    }
	
	
}	

	
	

public class  Boton_exportar implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		
		
		
	}
	
	
}

}