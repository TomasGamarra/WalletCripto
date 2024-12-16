package vista;

import java.awt.CardLayout;

import javax.swing.JFrame;

public class Vista extends JFrame {
	
	private PanelPrincipal panelMain;
	
	
        public Vista () {
        	
        setTitle("CryptoWallet-HODL");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 1000);
     
        
        //Panel principal
        panelMain = new PanelPrincipal (); //Usa CardLayout
    

        add(panelMain);
        setVisible(true);
        }

		public PanelPrincipal getPanelMain() {
			return panelMain;
		}
		

		public void cambiarCarta(String nombreCarta) {
	        // Obtener el layout del PanelPrincipal (que es un CardLayout)
	        CardLayout layout = (CardLayout) panelMain.getLayout();
	        // Cambiar a la carta especificada
	        layout.show(panelMain, nombreCarta);
	    }
}
