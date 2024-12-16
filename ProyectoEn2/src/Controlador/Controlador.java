package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.spi.FileSystemProvider;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import Excepciones.PasswordException;
import Excepciones.RequestException;
import Sistema.ActivoCripto;
import Sistema.ActivoFiat;
import Sistema.Criptomoneda;
import Sistema.CriptomonedaEnum;
import Sistema.Modelo;
import Sistema.MonedaFiat;
import Sistema.Persona;
import Sistema.ServicioCotizaciones;
import Sistema.Transaccion;
import Sistema.Usuario;
import Vista.ModeloTablaActivos;
import Vista.PanelesEnumerativos;
import Vista.Vista;
import gestores.GestorAplicacion;
import gestores.GestorAplicacion;

public class Controlador {
	private Vista vista;
	private Modelo modelo;
	
	
	public Controlador(Vista vista, Modelo modelo) {
		this.modelo= modelo;
		this.vista= vista;
		//PanelLogin
		vista.getPanelMain().getPanelLogin().getLoginButton().addActionListener(new Boton_login());
		vista.getPanelMain().getPanelLogin().getRegisterButton().addActionListener(new Boton_vista_registrar());
		
		//PanelRegistro
		vista.getPanelMain().getPanelRegistro().getVolverButton().addActionListener(new Boton_cancelar_registro());
		vista.getPanelMain().getPanelRegistro().getRegisterButton().addActionListener(new Boton_registrar());
		
		
		
	
		//PanelActivos
		vista.getPanelMain().getPanelActivos().getBotonLogout().addActionListener(new Boton_Logout_Activos());
		vista.getPanelMain().getPanelActivos().getBotonCotizaciones().addActionListener(new Boton_cotizacion());
		vista.getPanelMain().getPanelActivos().getBotonHistorial().addActionListener(new Boton_trans());
		vista.getPanelMain().getPanelActivos().getBotonPrueba().addActionListener(new Boton_generar_datos());
		vista.getPanelMain().getPanelActivos().getBotonExportar().addActionListener(new Boton_exportar()); 
		
		//PanelHistorial
		vista.getPanelMain().getPanelHistorial().getBotonVolver().addActionListener(new Boton_salir_trans());
		
		//PanelCotizaciones
		vista.getPanelMain().getPanelCotizaciones().getBotonVolver().addActionListener(new Boton_salir_cotizacion());
			
		
		//PanelCompra
		vista.getPanelMain().getPanelCompra().getCancelarBoton().addActionListener(new Boton_cancelar_compra());
		vista.getPanelMain().getPanelCompra().getBotonConvertir().addActionListener(new Boton_Convertir());
		vista.getPanelMain().getPanelCompra().getBotonCompra().addActionListener(new Boton_Realizar_Compra());		
		vista.getPanelMain().getPanelCompra().actualizarMonedasFiat(GestorAplicacion.getListaFiatSoportadas());;
		
		
		//Incializacion de Cotizaciones, Timer y Botones de Compra
		setearCotizaciones();
		iniciarTimer();
		
		asignarBotonesCompraListeners();
		
		
	
	}
	
	private void asignarBotonesCompraListeners() {
		for (CriptomonedaEnum cripto : CriptomonedaEnum.values()) {
			vista.getPanelMain().getPanelCotizaciones().getBoton(cripto.getNomenclatura()).addActionListener(new Boton_Compra());;;
		}
	}
	
	
	private class Boton_Compra implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			vista.cambiarCarta(PanelesEnumerativos.COMPRA.getNombre());
			int idCripto=modelo.getCriptoDao().obtenerIdCripto(e.getActionCommand());		
			GestorAplicacion.setCriptoComprar(modelo.getCriptoDao().find(idCripto)); //Hay que ver
			inicializarMenuCompra(e.getActionCommand(),idCripto);
		}
		
	}
	
	private class Boton_Convertir implements ActionListener {
	
		@Override
		public void actionPerformed(ActionEvent e) {
			
			MonedaFiat mon=traerSeleccion();
			Criptomoneda cripto = GestorAplicacion.getCriptoComprar();
			float cantidadCripto = mon.convertir(vista.getPanelMain().getPanelCompra().getCantidadFiat(),cripto);
			GestorAplicacion.setConversion(cantidadCripto);
			vista.getPanelMain().getPanelCompra().setConversion(cantidadCripto,cripto.getNomenclatura());
			
		}
	}
	
	private MonedaFiat traerSeleccion() {
		String nomenclaturaFiat = vista.getPanelMain().getPanelCompra().getFiat(); //Implementar
		int idMoneda = modelo.getFiatDao().obtenerIdFiat(nomenclaturaFiat);
		MonedaFiat mon = modelo.getFiatDao().find(idMoneda);
		GestorAplicacion.setFiatCompra(mon);
		return mon;
	}
	private class Boton_Realizar_Compra implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			traerSeleccion();

			float cantidadDeseada = vista.getPanelMain().getPanelCompra().getCantidadFiat();
			float cantidadUser = modelo.getActivoFiatDao().find(GestorAplicacion.getUser().getIdUsuario(),GestorAplicacion.getFiatCompra().getNombre()).getAmount();
			if (cantidadUser < cantidadDeseada) {
				JOptionPane.showMessageDialog(null, "No posee la cantidad fiat indicada.", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
			int idCripto= modelo.getCriptoDao().obtenerIdCripto(GestorAplicacion.getCriptoComprar().getNomenclatura());
			float cantidadStock = modelo.getStockDao().find(idCripto).getCantidad();	
			float cantidadCriptoUser = GestorAplicacion.getConversion();
			if (cantidadStock < cantidadCriptoUser) {
				JOptionPane.showMessageDialog(null, "No hay stock suficiente para realizar la compra.", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
			//Se efectua la compra
			int idFiat=modelo.getFiatDao().obtenerIdFiat(GestorAplicacion.getFiatCompra().getNomenclatura());
			Transaccion t = new Transaccion("Compra de "+cantidadCriptoUser + " "+ GestorAplicacion.getCriptoComprar().getNomenclatura() + " por " + cantidadDeseada + " "+ GestorAplicacion.getFiatCompra().getNombre(),"COMPRA",new Timestamp(System.currentTimeMillis()),GestorAplicacion.getUser());
			modelo.getTransaccionDao().create(t);
			modelo.getStockDao().incrementarCantidad(idCripto, -cantidadCriptoUser);
			modelo.getActivoCriptoDao().incrementarCantidad(GestorAplicacion.getUser().getIdUsuario(), idCripto, cantidadCriptoUser);
			modelo.getActivoFiatDao().incrementarCantidad(GestorAplicacion.getUser().getIdUsuario(), idFiat, -cantidadDeseada);

			JOptionPane.showMessageDialog(
		            null, 
		            "Compra realizada exitosamente.", 
		            "Compra", 
		            JOptionPane.NO_OPTION );
			vista.cambiarCarta(PanelesEnumerativos.COTIZACIONES.getNombre());
			iniciarMenu();
		}
		
	}
		
		
	
	
	private void inicializarMenuCompra(String nomenclatura, int idCripto) {	
		vista.getPanelMain().getPanelCompra().setPrecioCripto(GestorAplicacion.getCriptoComprar().getValorUsd());
		vista.getPanelMain().getPanelCompra().setStockDisponible(modelo.getStockDao().find(idCripto).getCantidad(),nomenclatura);
		
	}
	
	
	private void iniciarTimer() {
	        Timer timer = new Timer();
	        timer.schedule(new TimerTask() {
	            public void run() {
	                actualizarCotizacionesApi();
	                actualizarVistaCotizaciones();
	            }
	        }, 5000, 60000);
	}
	
	public void actualizarVistaCotizaciones(){
		try {
		for (CriptomonedaEnum cripto : CriptomonedaEnum.values()) {
			vista.getPanelMain().getPanelCotizaciones().setCotizacion(cripto.getNombre(), ServicioCotizaciones.obtenerPrecio(cripto.getClaveApi()));
		}		
		vista.getPanelMain().getPanelCotizaciones().repaint();
		}catch (RequestException e) {
			JOptionPane.showMessageDialog(vista.getPanelMain().getPanelLogin(), e.getMessage());
		}
	}
	
	private void actualizarCotizacionesApi() {
		try {
			ServicioCotizaciones.obtenerPrecios(GestorAplicacion.getListaCriptoSoportadas());
		} catch (RequestException e) {
			System.out.println(e.getMessage());
		}

	}
	
	public void setearCotizaciones() {
		try {
		String [][] tabla = new String[CriptomonedaEnum.values().length][4];
		int i=0;
		for ( CriptomonedaEnum cripto : CriptomonedaEnum.values())  {
			tabla[i][0]=cripto.getRutaIcono();
			tabla[i][1]=cripto.getNombre();
			tabla[i][2]= cripto.getNomenclatura();
			tabla[i++][3]=String.format("%.2f",ServicioCotizaciones.obtenerPrecio(cripto.getClaveApi())) ;
		}
			
		vista.getPanelMain().getPanelCotizaciones().cargarDatos(tabla);
		} catch (RequestException e) {
			JOptionPane.showMessageDialog(vista.getPanelMain().getPanelLogin(), e.getMessage());
		}
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
		List <Transaccion> t = modelo.getTransaccionDao().find(GestorAplicacion.getUser().getIdUsuario());
		cargarTransEnTablaHistorial(t);	
		vista.getPanelMain().getPanelHistorial().repaint();
		
		
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
					GestorAplicacion.setUser(user);
					iniciarMenu();
				}
             }
		
		
	}
}	
	
	public void iniciarMenu() {
		vista.cambiarCarta("activos");
		//Nombre y apellido
		String nombre = GestorAplicacion.getUser().getPersona().getNombre();
		String apellido =GestorAplicacion.getUser().getPersona().getApellido();
		vista.getPanelMain().getPanelActivos().actualizarNombre(nombre+ " "+apellido );
		
		//Actualizo iniciales
		String iniciales = nombre.toUpperCase().charAt(0) +""+apellido.toUpperCase().charAt(0);
		vista.getPanelMain().getPanelActivos().actualizarIniciales(iniciales); 
		
		//Actualizo tabla de activos
		List <ActivoCripto> listaCripto = modelo.getActivoCriptoDao().obtenerActivosCriptoPorUsuario(GestorAplicacion.getUser().getIdUsuario());
		List <ActivoFiat> listaFiat= modelo.getActivoFiatDao().obtenerActivosFiatPorUsuario(GestorAplicacion.getUser().getIdUsuario());
		
		float balance =0;
		if (!listaCripto.isEmpty() && !listaFiat.isEmpty()) {
			cargarActivosEnTabla(listaCripto,listaFiat);
			for (ActivoCripto actCripto : listaCripto) 
				balance+=actCripto.getAmount() * actCripto.getCripto().getValorUsd();

			for (ActivoFiat actFiat : listaFiat) 
				balance+=actFiat.getAmount() * actFiat.getMonedaFiat().getValorUsd();		
			
		}else 
			vista.getPanelMain().getPanelActivos().limpiarTabla();
		
		
		
		vista.getPanelMain().getPanelActivos().actualizarBalance(balance);;		
		vista.getPanelMain().getPanelActivos().repaint();
		
	}
	
	private void cargarActivosEnTabla(List<ActivoCripto> activosCripto, List<ActivoFiat> activosFiat) {
		
		vista.getPanelMain().getPanelActivos().limpiarTabla();
	    // Cargar activos cripto
	    for (ActivoCripto activo : activosCripto) {
	        Object[] fila = new Object[3];
	        fila[0] = new ImageIcon(getClass().getResource(activo.getCripto().getNombreIcono()));
	        fila[1] = activo.getCripto().getNombre();
	        fila[2] = String.format("$%.4f",  activo.getAmount() * activo.getCripto().getValorUsd() );
	        vista.getPanelMain().getPanelActivos().aniadirFila(fila);
	    }

	    // Cargar activos fiduciarios
	    for (ActivoFiat activo : activosFiat) {
	        Object[] fila = new Object[3];
	        fila[0] = new ImageIcon(getClass().getResource(activo.getMonedaFiat().getNombreIcono()));
	        fila[1] = activo.getMonedaFiat().getNombre();
	        fila[2] = String.format("$%.4f", activo.getAmount() * activo.getMonedaFiat().getValorUsd());
	        vista.getPanelMain().getPanelActivos().aniadirFila(fila);
	    }
	}
	
 

	private void cargarTransEnTablaHistorial(List<Transaccion> transacciones) {
	    vista.getPanelMain().getPanelHistorial().limpiarTabla();  
	    // Cargar transacciones
	    for (Transaccion t : transacciones) {
	        Object[] fila = new Object[3];
	        fila[0] = t.getFecha();
	        fila[1] = t.getTipo();
	        fila[2] = t.getResumen();
	        vista.getPanelMain().getPanelHistorial().aniadirFila(fila);
	    }
	    vista.getPanelMain().getPanelHistorial().repaint();
	}
	

 


	public class Boton_registrar implements ActionListener{
		
		
		public void actionPerformed(ActionEvent e) {
			try {
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
					throw new PasswordException("La contraseña no coincide");
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
				GestorAplicacion.setUser(user);
				iniciarMenu();
				}catch (PasswordException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
					
			
			}
		}
	
public class  Boton_generar_datos implements ActionListener{
	 
		@Override
	    public void actionPerformed(ActionEvent e) {
	        Random random = new Random();
	        
	        modelo.getActivoCriptoDao().eliminarActivosCriptoPorUsuario(GestorAplicacion.getUser().getIdUsuario());;
	        modelo.getActivoFiatDao().eliminarActivosFiatPorUsuario(GestorAplicacion.getUser().getIdUsuario());;
	        
	        List<Criptomoneda> criptomonedas = modelo.getCriptoDao().obtenerCriptomonedas();
	        List<MonedaFiat> monedasFiduciarias = modelo.getFiatDao().obtenerFiats();

	      
	        
	        for (Criptomoneda cripto : criptomonedas) {
	            float cantidad = random.nextFloat() * 20;  
	            modelo.getActivoCriptoDao().create(GestorAplicacion.getUser().getIdUsuario(),modelo.getCriptoDao().obtenerIdCripto(cripto.getNomenclatura()),cantidad); //Tendria que ser update y crearlos la primera vez (iniciarMenu)
	        }

	        for (MonedaFiat moneda : monedasFiduciarias) {
	            float cantidad = random.nextFloat() * 20;  
	            modelo.getActivoFiatDao().create(GestorAplicacion.getUser().getIdUsuario(),modelo.getFiatDao().obtenerIdFiat(moneda.getNomenclatura()), cantidad);//Tendria que ser update y crearlos la primera vez (iniciarMenu)
	        }
	        
	    
	        iniciarMenu();
	        
	        JOptionPane.showMessageDialog(null, "Datos de prueba generados correctamente.");
	    }
	
}	

	
	

public class  Boton_exportar implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
	
		
		String tituloscsv= "Nombre,Nomenclatura,Tipo,Cantidad,ValorEnUsd";
		
		List <ActivoCripto> listaCripto = modelo.getActivoCriptoDao().obtenerActivosCriptoPorUsuario( GestorAplicacion.getUser().getIdUsuario());
		List <ActivoFiat> listaFiat= modelo.getActivoFiatDao().obtenerActivosFiatPorUsuario( GestorAplicacion.getUser().getIdUsuario());
		
		try {
		BufferedWriter writer = new BufferedWriter(new FileWriter("archivo.csv",false));
		
		writer.write(tituloscsv + "\n");
		
		for (ActivoCripto a : listaCripto) {
			String linea = a.getCripto().getNombre() + ", " + a.getCripto().getNomenclatura() + ", " + a.getClass().getName() + ", " + a.getAmount() + ", " + a.getAmount() * a.getCripto().getValorUsd() + ", "+"\n";
			writer.write(linea);
		}
		
		for (ActivoFiat f : listaFiat) {
			String linea = f.getMonedaFiat().getNombre() + ", " + f.getMonedaFiat().getNomenclatura() + ", " + f.getClass().getName()   + ", " + f.getAmount() + ", " + f.getAmount() * f.getMonedaFiat().getValorUsd() + ", " + "\n";
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

