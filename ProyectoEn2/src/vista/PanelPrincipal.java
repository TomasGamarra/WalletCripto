package vista;

import java.awt.CardLayout;

import javax.swing.JPanel;

import enumerativos.PanelesEnumerativos;

public class PanelPrincipal extends JPanel {
	
	private PanelLogin panelLogin;	
	private PanelActivos panelActivos;
	private PanelRegistro panelRegistro;
	private PanelHistorial panelHistorial;
	private PanelCotizaciones panelCotizaciones;
	private PanelCompra panelCompra;
	
	public PanelPrincipal () {
		this.setLayout(new CardLayout());
        // Agregar paneles al CardLayout
		panelLogin = new PanelLogin();
		panelActivos = new PanelActivos();
		panelRegistro = new PanelRegistro();
		panelHistorial = new PanelHistorial();
		panelCotizaciones = new PanelCotizaciones();
		panelCompra =new PanelCompra();
		add(panelLogin, PanelesEnumerativos.LOGIN.getNombre());
		add(panelCompra,PanelesEnumerativos.COMPRA.getNombre());
		add(panelHistorial,PanelesEnumerativos.HISTORIAL.getNombre());
        add(panelRegistro, PanelesEnumerativos.REGISTRO.getNombre());
        add(panelActivos, PanelesEnumerativos.ACTIVOS.getNombre());
		add(panelCotizaciones, PanelesEnumerativos.COTIZACIONES.getNombre());
		
        
        
       
	}
	public PanelLogin getPanelLogin() {
		return panelLogin;
	}
	public void setPanelLogin(PanelLogin panelLogin) {
		this.panelLogin = panelLogin;
	}
	public PanelActivos getPanelActivos() {
		return panelActivos;
	}
	public void setPanelActivos(PanelActivos panelActivos) {
		this.panelActivos = panelActivos;
	}
	public PanelRegistro getPanelRegistro() {
		return panelRegistro;
	}
	public void setPanelRegistro(PanelRegistro panelRegistro) {
		this.panelRegistro = panelRegistro;
	}
	public PanelHistorial getPanelHistorial() {
		return panelHistorial;
	}
	public void setPanelHistorial(PanelHistorial panelHistorial) {
		this.panelHistorial = panelHistorial;
	}
	public PanelCotizaciones getPanelCotizaciones() {
		return panelCotizaciones;
	}
	public void setPanelCotizaciones(PanelCotizaciones panelCotizaciones) {
		this.panelCotizaciones = panelCotizaciones;
	}
	public PanelCompra getPanelCompra() {
		return panelCompra;
	}
	public void setPanelCompra(PanelCompra panelCompra) {
		this.panelCompra = panelCompra;
	}
	
	
}