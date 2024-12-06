package Vista;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class PanelPrincipal extends JPanel {
	
	private PanelLogin panelLogin;	
	private PanelMenu panelMenu;
	private PanelRegistro panelRegistro;
	
	public PanelPrincipal () {
		this.setLayout(new CardLayout());
        // Agregar paneles al CardLayout
		panelLogin = new PanelLogin();
		panelMenu = new PanelMenu();
		panelRegistro = new PanelRegistro();
        //add(panelLogin, "login");
        //add(panelMenu, "menu");
        add(panelRegistro, "registro");
       // add(new , "activos");
	}
	public PanelLogin getPanelLogin() {
		return panelLogin;
	}
	public void setPanelLogin(PanelLogin panelLogin) {
		this.panelLogin = panelLogin;
	}
	public PanelMenu getPanelMenu() {
		return panelMenu;
	}
	public void setPanelMenu(PanelMenu panelMenu) {
		this.panelMenu = panelMenu;
	}
}
