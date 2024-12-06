package Vista;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class PanelPrincipal extends JPanel {
	
	private PanelLogin panelLogin;	
	private PanelActivos panelActivos;
	private PanelRegistro panelRegistro;
	
	public PanelPrincipal () {
		this.setLayout(new CardLayout());
        // Agregar paneles al CardLayout
		panelLogin = new PanelLogin();
		panelActivos = new PanelActivos();
		panelRegistro = new PanelRegistro();
		//add(panelLogin, "login");
        add(panelActivos, "activos");
        add(panelRegistro, "registro");
       
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
}
