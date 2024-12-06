package Vista;

import javax.swing.*;
import java.awt.*;

public class Vista extends JFrame {
	private PanelPrincipal panelMain;
	
	
        public Vista () {
        	
        setTitle("CryptoWallet MG");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 800);
     
        
        //Panel principal
        panelMain = new PanelPrincipal ();
    

        add(panelMain);
        setVisible(true);
        }

		public PanelPrincipal getPanelMain() {
			return panelMain;
		}

		public void setPanelMain(PanelPrincipal panelMain) {
			this.panelMain = panelMain;
		}

		
}

