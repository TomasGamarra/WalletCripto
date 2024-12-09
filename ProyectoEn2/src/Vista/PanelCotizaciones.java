package Vista;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelCotizaciones extends JPanel {
	private JPanel panelCentral;
	private JLabel labelValorUsd;
	private JLabel labelNombreCripto;
//	public PanelCotizaciones() {
//	    setLayout(new BorderLayout());
//	    panelCentral = new JPanel();
//		panelCentral.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//
//	    
//	    JPanel header = new JPanel(new GridLayout(1, 4));
//	    
//	    labelValorUsd = new JLabel("Valor (USD)", SwingConstants.CENTER);
//	    labelNombreCripto = new JLabel("Criptomoneda", SwingConstants.CENTER);
//	    
//	    header.add(new JLabel("", SwingConstants.CENTER));
//	    header.add(labelNombreCripto);
//	    header.add(labelValorUsd);
//	    header.setOpaque(false);
//	    
//	    header.setBackground(Color.LIGHT_GRAY);
//	    add(header);
//
//	    // Consulta y carga inicial
//	    String[][] criptos = Sistema.ServicioCotizaciones.consultarPreciosDesdeAPI();
//	    if (criptos != null) {
//	  
//	    } else {
//	        JLabel errorLabel = new JLabel("Error al cargar las cotizaciones.");
//	        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
//	        add(errorLabel);
//	    }
//	}

	@Override
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(new GradientPaint(0, 0, new Color(47,224,189), getWidth(), getHeight(),new Color (255,127,172)));
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
	
	
}
