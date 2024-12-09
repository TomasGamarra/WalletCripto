package Vista;

import javax.swing.*;
import java.awt.*;

public class Vista extends JFrame {
	
	private PanelPrincipal panelMain;
	
	
        public Vista () {
        	
        setTitle("CryptoWallet MG");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
     
        
        //Panel principal
        panelMain = new PanelPrincipal (); //Usa CardLayout
    

        add(panelMain);
        setVisible(true);
        }

		public PanelPrincipal getPanelMain() {
			return panelMain;
		}

		public void setPanelMain(PanelPrincipal panelMain) {
			this.panelMain = panelMain;
		}

		public void cambiarCarta(String nombreCarta) {
	        // Obtener el layout del PanelPrincipal (que es un CardLayout)
	        CardLayout layout = (CardLayout) panelMain.getLayout();
	        // Cambiar a la carta especificada
	        layout.show(panelMain, nombreCarta);
	    }
}
