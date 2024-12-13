package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import Sistema.ActivoCripto;
import Sistema.ActivoFiat;
import Sistema.Criptomoneda;
import Sistema.Modelo;
import Sistema.Moneda;
import Sistema.MonedaFiat;
import Sistema.Persona;
import Sistema.Usuario;
import Vista.ModeloTablaActivos;
import Vista.PanelesEnumerativos;
import Vista.Vista;
import gestores.GestorDeUsuarioActual;

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
		
		
		vista.getPanelMain().getPanelActivos().getBotonPrueba().addActionListener(new Boton_generar_datos());
		vista.getPanelMain().getPanelActivos().getBotonExportar().addActionListener(new Boton_exportar()); 
		
		vista.getPanelMain().getPanelHistorial().getBotonVolver().addActionListener(new Boton_salir_trans());
		
		
		vista.getPanelMain().getPanelCotizaciones().getBotonVolver().addActionListener(new Boton_salir_cotizacion());
		//vista.getPanelMain().getPanelCotizaciones().getLogOutButton().addActionListener(new Boton_Logout_Cotizacion());
		//new Boton_Logout_Cotizacion()
		//Falta boton comprar 
		
		
		
		//vista.getPanelMain().getPanelCompra().getCancelarButton().addActionListener(new Boton_cancelar_compra());
		//Falta boton conversion, lista de monedas, boton de compra
	
	}
	
	public class Boton_vista_registrar implements ActionListener{
		
		
		public void actionPerformed(ActionEvent e) {
			vista.cambiarCarta(PanelesEnumerativos.REGISTRO.getNombre());
		}
	}
	
	
	public class Boton_cancelar_registro implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			vista.cambiarCarta(PanelesEnumerativos.LOGIN.getNombre());
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
		vista.cambiarCarta(PanelesEnumerativos.COTIZACIONES.getNombre());
	}
}

public class Boton_trans implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		vista.cambiarCarta(PanelesEnumerativos.HISTORIAL.getNombre());
		
		
	}
}

public class Boton_salir_trans implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		vista.cambiarCarta(PanelesEnumerativos.ACTIVOS.getNombre());
	}
}


public class Boton_salir_cotizacion implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		vista.cambiarCarta(PanelesEnumerativos.ACTIVOS.getNombre());
	}
}

public class Boton_cancelar_compra implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		vista.cambiarCarta(PanelesEnumerativos.COTIZACIONES.getNombre());
	}
}



public class Boton_login implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		
		if (vista.getPanelMain().getPanelLogin().extraerUser().isBlank() || (vista.getPanelMain().getPanelLogin().extraerPasswr().isBlank())) {
			JOptionPane.showMessageDialog(vista.getPanelMain().getPanelLogin(), "Por favor complete todos los campos.");
			
			return;
		}
		else {
			Usuario user = modelo.getUsuarioDao().findByEmail(vista.getPanelMain().getPanelLogin().extraerUser()); // Obtengo el usuario de la DB
			if ( user == null ) {
				JOptionPane.showMessageDialog(vista.getPanelMain().getPanelLogin(), "Email no asociado a ninguna cuenta.");
			}else 
				if (!(user.getPassword().equals(vista.getPanelMain().getPanelLogin().extraerPasswr())))
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
		vista.getPanelMain().getPanelActivos().actualizarNombre(nombre+ " "+apellido );
		
		//Actualizo iniciales
		String iniciales = nombre.toUpperCase().charAt(0) +""+apellido.toUpperCase().charAt(0);
		vista.getPanelMain().getPanelActivos().actualizarIniciales(iniciales); 
		
		//Actualizo tabla de activos
		List <ActivoCripto> listaCripto = modelo.getActivoCriptoDao().obtenerActivosCriptoPorUsuario(GestorDeUsuarioActual.getUser().getIdUsuario());
		List <ActivoFiat> listaFiat= modelo.getActivoFiatDao().obtenerActivosFiatPorUsuario(GestorDeUsuarioActual.getUser().getIdUsuario());
		
		
		if (!listaCripto.isEmpty() && !listaFiat.isEmpty()) {
			cargarActivosEnTabla(listaCripto,listaFiat,vista.getPanelMain().getPanelActivos().getTablaActivos());
			
		}
		
		float balance =0;
		for (ActivoCripto actCripto : listaCripto) {
			balance+=actCripto.getAmount() * actCripto.getCripto().getValorUsd();
		}
		
		for (ActivoFiat actFiat : listaFiat) {
			balance+=actFiat.getAmount() * actFiat.getMonedaFiat().getValorUsd();
		}
		
		vista.getPanelMain().getPanelActivos().actualizarBalance(balance);;		
		vista.getPanelMain().getPanelActivos().repaint();
		
	}
	
	private void cargarActivosEnTabla(List<ActivoCripto> activosCripto, List<ActivoFiat> activosFiat, JTable tabla) {
	    ModeloTablaActivos modelo = new ModeloTablaActivos(new Object[0][3]);
	    tabla.setModel(modelo);    
	    // Cargar activos cripto
	    for (ActivoCripto activo : activosCripto) {
	        Object[] fila = new Object[3];
	        fila[0] = new ImageIcon(getClass().getResource(activo.getCripto().getNombreIcono()));
	        fila[1] = activo.getCripto().getNombre();
	        fila[2] = String.format("$%.2f",  activo.getAmount() * activo.getCripto().getValorUsd() );
	        modelo.addRow(fila);
	    }

	    // Cargar activos fiduciarios
	    for (ActivoFiat activo : activosFiat) {
	        Object[] fila = new Object[3];
	        fila[0] = new ImageIcon(getClass().getResource(activo.getMonedaFiat().getNombreIcono()));
	        fila[1] = activo.getMonedaFiat().getNombre();
	        fila[2] = String.format("$%.2f", activo.getAmount() * activo.getMonedaFiat().getValorUsd());
	        modelo.addRow(fila);
	    }
	}
	
 
	

 


public class Boton_registrar implements ActionListener{
	
	
	public void actionPerformed(ActionEvent e) {
		String nombre = vista.getPanelMain().getPanelRegistro().extraerNombre();
		String apellido= vista.getPanelMain().getPanelRegistro().extraerApellido();
		String mail = vista.getPanelMain().getPanelRegistro().extraerEmail();
		String contra = String.valueOf(vista.getPanelMain().getPanelRegistro().extraerContra());
		String contra2 = String.valueOf(vista.getPanelMain().getPanelRegistro().extraerContra2());
		boolean terminos= vista.getPanelMain().getPanelRegistro().extraerTerminos();
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
	        
	        modelo.getActivoCriptoDao().eliminarActivosCriptoPorUsuario(GestorDeUsuarioActual.getUser().getIdUsuario());;
	        modelo.getActivoFiatDao().eliminarActivosFiatPorUsuario(GestorDeUsuarioActual.getUser().getIdUsuario());;
	        
	        List<Criptomoneda> criptomonedas = modelo.getCriptoDAO().obtenerCriptomonedas();
	        List<MonedaFiat> monedasFiduciarias = modelo.getFiatDAO().obtenerFiats();

	      
	        
	        for (Criptomoneda cripto : criptomonedas) {
	            float cantidad = random.nextFloat() * 20;  
	            modelo.getActivoCriptoDao().create(GestorDeUsuarioActual.getUser().getIdUsuario(),modelo.getCriptoDAO().obtenerIdCripto(cripto.getNomenclatura()),cantidad); //Tendria que ser update y crearlos la primera vez (iniciarMenu)
	        }

	        for (MonedaFiat moneda : monedasFiduciarias) {
	            float cantidad = random.nextFloat() * 20;  
	            modelo.getActivoFiatDao().create(GestorDeUsuarioActual.getUser().getIdUsuario(),modelo.getFiatDAO().obtenerIdFiat(moneda.getNomenclatura()), cantidad);//Tendria que ser update y crearlos la primera vez (iniciarMenu)
	        }
	        
	    
	        iniciarMenu();
	        // Mensaje de confirmación
	        JOptionPane.showMessageDialog(null, "Datos de prueba generados correctamente.");
	    }
	
}	

	
	

public class  Boton_exportar implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
	
		
		String tituloscsv= "Nombre,Nomenclatura,Tipo,Cantidad,ValorEnUsd";
		
		List <ActivoCripto> listaCripto = modelo.getActivoCriptoDao().obtenerActivosCriptoPorUsuario( GestorDeUsuarioActual.getUser().getIdUsuario());
		List <ActivoFiat> listaFiat= modelo.getActivoFiatDao().obtenerActivosFiatPorUsuario( GestorDeUsuarioActual.getUser().getIdUsuario());
		
		try {
		BufferedWriter writer = new BufferedWriter(new FileWriter("archivo.csv",false));
		
		writer.write(tituloscsv + "\n");
		
		for (ActivoCripto a : listaCripto) {
			String linea = a.getCripto().getNombre() + ", " + a.getCripto().getNomenclatura() + ", " + "Cripto" + ", " + a.getAmount() + ", " + a.getAmount() * a.getCripto().getValorUsd() + ", "+"\n";
			writer.write(linea);
		}
		
		for (ActivoFiat f : listaFiat) {
			String linea = f.getMonedaFiat().getNombre() + ", " + f.getMonedaFiat().getNomenclatura() + ", " + "Fiat"   + ", " + f.getAmount() + ", " + f.getAmount() * f.getMonedaFiat().getValorUsd() + ", " + "\n";
			writer.write(linea);
		}
		
		writer.close();
		
		JOptionPane.showMessageDialog(null, "Archivo csv correctamente creado en la carpeta asociada a la aplicacion.");
		
		}catch (IOException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(vista.getPanelMain().getPanelLogin(), "Ha ocurrido un error a la hora de generar el archivo csv.");
			
		}
	}
		
		
	}
	
	
}

