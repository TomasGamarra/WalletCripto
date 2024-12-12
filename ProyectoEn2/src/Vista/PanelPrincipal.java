package Vista;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class PanelPrincipal extends JPanel {
	
	private PanelLogin panelLogin;	
	private PanelActivos panelActivos;
	private PanelRegistro panelRegistro;
	private PanelHistorial panelHistorial;
	private PanelCotizaciones panelCotizaciones;
	
	public PanelPrincipal () {
		this.setLayout(new CardLayout());
        // Agregar paneles al CardLayout
		panelLogin = new PanelLogin();
		panelActivos = new PanelActivos();
		panelRegistro = new PanelRegistro();
		panelHistorial = new PanelHistorial();
		panelCotizaciones = new PanelCotizaciones();
		add(panelHistorial,"historial");
        add(panelRegistro, "registro");
        add(panelActivos, "activos");
		add(panelLogin, "login");
		add(panelCotizaciones, "cotizaciones");
		
        
        
       
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
	
	
}