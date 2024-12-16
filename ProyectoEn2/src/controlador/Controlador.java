package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import enumerativos.CriptomonedaEnum;
import enumerativos.PanelesEnumerativos;
import excepciones.PasswordException;
import excepciones.RequestException;
import gestores.GestorAplicacion;
import gestores.ServicioCotizaciones;
import interfaces_DAO.FiatDAO;
import sistema.ActivoCripto;
import sistema.ActivoFiat;
import sistema.Criptomoneda;
import sistema.Modelo;
import sistema.MonedaFiat;
import sistema.MonedaFiatEnum;
import sistema.Persona;
import sistema.Transaccion;
import sistema.Usuario;
import vista.Vista;

public class Controlador {
	private Vista vista;
	private Modelo modelo;
	
	
	public Controlador(Vista vista, Modelo modelo) {
		this.modelo= modelo;
		this.vista= vista;
		//Asignacion de Listeners a la Vista
		
		//PanelLogin
		vista.getPanelMain().getPanelLogin().getLoginButton().addActionListener(new Boton_login());
		vista.getPanelMain().getPanelLogin().getRegisterButton().addActionListener(new Boton_vista_registrar());
		
		//PanelRegistro
		vista.getPanelMain().getPanelRegistro().getVolverButton().addActionListener(new Boton_cancelar_registro());
		vista.getPanelMain().getPanelRegistro().getRegisterButton().addActionListener(new Boton_registrar());
		
		
		
	
		//PanelActivos
		vista.getPanelMain().getPanelActivos().getBotonLogout().addActionListener(new Boton_logout_activos());
		vista.getPanelMain().getPanelActivos().getBotonCotizaciones().addActionListener(new Boton_cotizacion());
		vista.getPanelMain().getPanelActivos().getBotonHistorial().addActionListener(new Boton_trans());
		vista.getPanelMain().getPanelActivos().getBotonPrueba().addActionListener(new Boton_generar_datos());
		vista.getPanelMain().getPanelActivos().getBotonExportar().addActionListener(new Boton_exportar()); 
		vista.getPanelMain().getPanelActivos().getBotonPesos().addActionListener(new Boton_conversion_pesos());
		vista.getPanelMain().getPanelActivos().getBotonDolar().addActionListener(new Boton_conversion_dolares());
		
		//PanelHistorial
		vista.getPanelMain().getPanelHistorial().getBotonVolver().addActionListener(new Boton_salir_trans());
		
		//PanelCotizaciones
		vista.getPanelMain().getPanelCotizaciones().getBotonVolver().addActionListener(new Boton_salir_cotizacion());
			
		
		//PanelCompra
		vista.getPanelMain().getPanelCompra().getCancelarBoton().addActionListener(new Boton_cancelar_compra());
		vista.getPanelMain().getPanelCompra().getBotonConvertir().addActionListener(new Boton_convertir());
		vista.getPanelMain().getPanelCompra().getBotonCompra().addActionListener(new Boton_realizar_compra());		
		vista.getPanelMain().getPanelCompra().actualizarMonedasFiat(GestorAplicacion.getListaFiatSoportadas());;
	
		
		//Incializacion de Cotizaciones, Timer y Botones de Compra
		setearCotizaciones();
		iniciarTimer();
		asignarBotonesCompraListeners();
				
	}
	
	private void asignarBotonesCompraListeners() {
		for (CriptomonedaEnum cripto : CriptomonedaEnum.values()) {
			vista.getPanelMain().getPanelCotizaciones().getBoton(cripto.getNomenclatura()).addActionListener(new Boton_compra());;;
		}
	}
	
	private class Boton_compra implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			vista.cambiarCarta(PanelesEnumerativos.COMPRA.getNombre());
			int idCripto=modelo.getCriptoDao().obtenerIdCripto(e.getActionCommand());		
			GestorAplicacion.setCriptoComprar(modelo.getCriptoDao().find(idCripto)); //Hay que ver
			inicializarMenuCompra(e.getActionCommand(),idCripto);
		}
		
	}
		
	private MonedaFiat traerSeleccion() {
		String nomenclaturaFiat = vista.getPanelMain().getPanelCompra().getFiat(); 
		int idMoneda = modelo.getFiatDao().obtenerIdFiat(nomenclaturaFiat);
		MonedaFiat mon = modelo.getFiatDao().find(idMoneda);
		GestorAplicacion.setFiatCompra(mon);
		return mon;
	}
	
	public class Boton_cancelar_compra implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			vista.cambiarCarta(PanelesEnumerativos.COTIZACIONES.getNombre());
			vista.getPanelMain().getPanelCompra().setConversion(0,""); //Deja vacio el equivalente
		}
	}
	
	private class Boton_realizar_compra implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			traerSeleccion(); //Actualiza el GestorAplicacion con la Fiat seleccionada
			Usuario user =GestorAplicacion.getUser();
			MonedaFiat fiatCompra =GestorAplicacion.getFiatCompra();
			Criptomoneda criptoCompra=GestorAplicacion.getCriptoComprar();
			float cantidadDeseada;
			try {
			cantidadDeseada = vista.getPanelMain().getPanelCompra().getCantidadFiat();
			}catch(NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "La cantidad deseada no es un valor numerico.", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
			float cantidadUser = modelo.getActivoFiatDao().find(user.getIdUsuario(),fiatCompra.getNombre()).getAmount();
			
			if (cantidadUser < cantidadDeseada) {
				JOptionPane.showMessageDialog(null, "No posee la cantidad fiat suficiente.", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			int idCripto= modelo.getCriptoDao().obtenerIdCripto(criptoCompra.getNomenclatura());
			float cantidadStock = modelo.getStockDao().find(idCripto).getCantidad();	
			float cantidadCriptoUser = fiatCompra.convertir(cantidadDeseada, criptoCompra);
			
			if (cantidadStock < cantidadCriptoUser) {
				JOptionPane.showMessageDialog(null, "No hay stock suficiente para realizar la compra.", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			//Se efectua la compra
			int idFiat=modelo.getFiatDao().obtenerIdFiat(fiatCompra.getNomenclatura());
			Transaccion t = new Transaccion("Compra de "+ cantidadCriptoUser + " "+ criptoCompra.getNomenclatura() + " por " + cantidadDeseada + " "+ fiatCompra.getNombre(),"COMPRA",new Timestamp(System.currentTimeMillis()),user);
			
			modelo.getTransaccionDao().create(t);
			modelo.getStockDao().incrementarCantidad(idCripto, -cantidadCriptoUser);
			modelo.getActivoCriptoDao().incrementarCantidad(user.getIdUsuario(), idCripto, cantidadCriptoUser);
			modelo.getActivoFiatDao().incrementarCantidad(user.getIdUsuario(), idFiat, -cantidadDeseada);

			JOptionPane.showMessageDialog(
		            null, 
		            "Compra realizada exitosamente.", 
		            "Compra", 
		            JOptionPane.NO_OPTION );
			
			vista.cambiarCarta(PanelesEnumerativos.COTIZACIONES.getNombre());
			vista.getPanelMain().getPanelCompra().setConversion(0,""); //Deja vacio el equivalente
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
	
	private void setearCotizaciones() {
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

	public class Boton_logout_activos implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		 int respuesta = JOptionPane.showConfirmDialog(
		            null, 
		            "¿Desea salir de esta sesión?", 
		            "Confirmación", 
		            JOptionPane.YES_NO_OPTION, 
		            JOptionPane.WARNING_MESSAGE
		        );

		        if (respuesta == JOptionPane.YES_OPTION) {
		        	vista.cambiarCarta(PanelesEnumerativos.LOGIN.getNombre());
		        } 
	}
	
}
	
	public class Boton_cotizacion implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		vista.cambiarCarta(PanelesEnumerativos.COTIZACIONES.getNombre());
	}
}

	public class Boton_salir_cotizacion implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			vista.cambiarCarta(PanelesEnumerativos.ACTIVOS.getNombre());
			iniciarMenu();
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

	public class Boton_login implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		String userField=vista.getPanelMain().getPanelLogin().getUser();
		String passwordField=vista.getPanelMain().getPanelLogin().getPassword();
		if (userField.isBlank() || (passwordField.isBlank())) {
			JOptionPane.showMessageDialog(vista.getPanelMain().getPanelLogin(), "Por favor complete todos los campos.");			
			return;
		}
		else {
			Usuario user = modelo.getUsuarioDao().findByEmail(userField); // Obtengo el usuario de la DB
			if ( user == null ) {
				JOptionPane.showMessageDialog(vista.getPanelMain().getPanelLogin(), "Email no asociado a ninguna cuenta.");
			}else 
				if (!(user.getPassword().equals(passwordField)))
					JOptionPane.showMessageDialog(vista.getPanelMain().getPanelLogin(), "Contraseña incorrecta.");	
				else {
					GestorAplicacion.setUser(user);
					iniciarMenu();
				}
             }
		
		
	}
}	
	
	public void iniciarMenu() {
		vista.cambiarCarta(PanelesEnumerativos.ACTIVOS.getNombre());
		//Nombre y apellido
		Usuario user=GestorAplicacion.getUser();
		String nombre = user.getPersona().getNombre();
		String apellido = user.getPersona().getApellido();
		
		vista.getPanelMain().getPanelActivos().actualizarNombre(nombre+" "+apellido);
		
		//Actualizo iniciales
		String iniciales = nombre.toUpperCase().charAt(0)+""+apellido.toUpperCase().charAt(0);
		
		vista.getPanelMain().getPanelActivos().actualizarIniciales(iniciales); 
		
		//Actualizo tabla de activos
		List <ActivoCripto> listaCripto = modelo.getActivoCriptoDao().obtenerActivosCriptoPorUsuario(user.getIdUsuario());
		List <ActivoFiat> listaFiat= modelo.getActivoFiatDao().obtenerActivosFiatPorUsuario(user.getIdUsuario());
		
		float balance = 0;
		if (!listaCripto.isEmpty() && !listaFiat.isEmpty()) {
			cargarActivosEnTabla(listaCripto,listaFiat);
			for (ActivoCripto actCripto : listaCripto) 
				balance+=actCripto.getAmount() * actCripto.getCripto().getValorUsd();

			for (ActivoFiat actFiat : listaFiat) 
				balance+=actFiat.getAmount() * actFiat.getMonedaFiat().getValorUsd();		
			
		}else 
			vista.getPanelMain().getPanelActivos().limpiarTabla();
		
		
		
		vista.getPanelMain().getPanelActivos().actualizarBalance(balance);
		
		vista.getPanelMain().getPanelActivos().repaint();
		
	}
	
	private void cargarActivosEnTabla(List<ActivoCripto> activosCripto, List<ActivoFiat> activosFiat) {
		
		vista.getPanelMain().getPanelActivos().limpiarTabla();
	    // Cargar activos cripto
	    for (ActivoCripto activo : activosCripto) {
	        Object[] fila = new Object[3];
	        fila[0] = new ImageIcon(getClass().getResource(activo.getCripto().getNombreIcono()));
	        fila[1] = activo.getCripto().getNombre();
	        fila[2] = activo.getAmount() * activo.getCripto().getValorUsd() ;
	        vista.getPanelMain().getPanelActivos().aniadirFila(fila);
	    }

	    // Cargar activos fiduciarios
	    for (ActivoFiat activo : activosFiat) {
	        Object[] fila = new Object[3];
	        fila[0] = new ImageIcon(getClass().getResource(activo.getMonedaFiat().getNombreIcono()));
	        fila[1] = activo.getMonedaFiat().getNombre();
	        fila[2] = activo.getAmount() * activo.getMonedaFiat().getValorUsd();
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
	        vista.getPanelMain().getPanelActivos().cambiarColumna(MonedaFiatEnum.DOLARES.getNomenclatura());
	        JOptionPane.showMessageDialog(null, "Datos de prueba generados correctamente.");
	    }
	
}	

	public class Boton_conversion_pesos implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String nomenclaturaPesos=MonedaFiatEnum.PESOS_ARGENTINOS.getNomenclatura();
		 if (vista.getPanelMain().getPanelActivos().getMonedaActual().equals(nomenclaturaPesos)) {
			 JOptionPane.showMessageDialog(null, "Ya esta en Pesos, no se realiza conversion.");
		        return;
		    }
		
		String nomenclaturaDolar=MonedaFiatEnum.DOLARES.getNomenclatura();
		vista.getPanelMain().getPanelActivos().cambiarColumna(nomenclaturaPesos);
		int length = vista.getPanelMain().getPanelActivos().getLength();
		FiatDAO fiatDao = modelo.getFiatDao();
		MonedaFiat dolar =fiatDao.find(fiatDao.obtenerIdFiat(nomenclaturaDolar));
		MonedaFiat pesos =fiatDao.find(fiatDao.obtenerIdFiat(nomenclaturaPesos));
		float conversion;
		for (int i =0 ; i<length ; i++) {
			conversion=dolar.convertir(vista.getPanelMain().getPanelActivos().getValorTabla(i), pesos);
			vista.getPanelMain().getPanelActivos().actualizarValor(i,conversion);
		}
	}
	
}

	public class Boton_conversion_dolares implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        String nomenclaturaDolar = MonedaFiatEnum.DOLARES.getNomenclatura();
        
        if (vista.getPanelMain().getPanelActivos().getMonedaActual().equals(nomenclaturaDolar)) {
			 JOptionPane.showMessageDialog(null, "Ya esta en Dolares, no se realiza conversion.");
		     return;
		}
    	
        String nomenclaturaPesos = MonedaFiatEnum.PESOS_ARGENTINOS.getNomenclatura();
        
        vista.getPanelMain().getPanelActivos().cambiarColumna(nomenclaturaDolar);
        
        FiatDAO fiatDao = modelo.getFiatDao();
        
        MonedaFiat pesos = fiatDao.find(fiatDao.obtenerIdFiat(nomenclaturaPesos));
        MonedaFiat dolar = fiatDao.find(fiatDao.obtenerIdFiat(nomenclaturaDolar));
        

        int lengthTabla = vista.getPanelMain().getPanelActivos().getLength();
        float cantidadPesos;
        float cantidadDolar;
        for (int i = 0; i < lengthTabla ; i++) {
        	cantidadPesos = vista.getPanelMain().getPanelActivos().getValorTabla(i);
            cantidadDolar = pesos.convertir(cantidadPesos, dolar);
            vista.getPanelMain().getPanelActivos().actualizarValor(i, cantidadDolar);
           
        }
    }
}
	
	private class Boton_convertir implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
			MonedaFiat mon=traerSeleccion();
			Criptomoneda cripto = GestorAplicacion.getCriptoComprar();
			float cantidadCripto = mon.convertir(vista.getPanelMain().getPanelCompra().getCantidadFiat(),cripto);
			//GestorAplicacion.setConversion(cantidadCripto); No hace falta creo
			vista.getPanelMain().getPanelCompra().setConversion(cantidadCripto,cripto.getNomenclatura());
			}catch(NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "La cantidad deseada no es un valor numerico.", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
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
			String linea = a.getCripto().getNombre() + ", " + a.getCripto().getNomenclatura() + ", " + a.getClass().getSimpleName() + ", " + a.getAmount() + ", " + a.getAmount() * a.getCripto().getValorUsd() + ", "+"\n";
			writer.write(linea);
		}
		
		for (ActivoFiat f : listaFiat) {
			String linea = f.getMonedaFiat().getNombre() + ", " + f.getMonedaFiat().getNomenclatura() + ", " + f.getClass().getSimpleName()   + ", " + f.getAmount() + ", " + f.getAmount() * f.getMonedaFiat().getValorUsd() + ", " + "\n";
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

