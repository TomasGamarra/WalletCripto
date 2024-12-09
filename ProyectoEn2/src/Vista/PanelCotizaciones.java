package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Sistema.ServicioCotizaciones;

public class PanelCotizaciones extends JPanel {
	private JPanel panelCentral;
	private JPanel panelSouth;
	private JLabel labelValorUsd;
	private JLabel labelNombreCripto;
	private JButton botonVolver;
	private JPanel panelTop;
	
	
	private Map<String, JButton> botonesCompra = new HashMap<>();
	
	public PanelCotizaciones() {
	    setLayout(new BorderLayout());
	    //PanelCentral
	    panelCentral = new JPanel();
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
		botonVolver = new JButton("Volver");
		//PanelSouth
		panelSouth = new JPanel();
		panelSouth.add(botonVolver);
		panelSouth.add(botonVolver,SwingConstants.CENTER);	
		//PanelTop
		//panelTop = new JPanel();
		
		
		add(panelCentral,BorderLayout.CENTER);
		add(panelSouth,BorderLayout.SOUTH);
		
	    cargarDatos();
	    
	    
	}

	   private void cargarDatos() {
	        String[][] datos = ServicioCotizaciones.consultarPreciosParaTabla(); 

	        for (String[] fila : datos) {
	            agregarFilaCotizacion(fila[0], fila[1], fila[2]);
	        }
	    }

	    private void agregarFilaCotizacion(String rutaIcono, String nombre, String precio) {
	        JPanel fila = new JPanel(new GridLayout(1, 4));
	        fila.setBackground(new Color(230, 230, 250));
	        
	        // Crear y configurar componentes
	        JLabel lblIcono = new JLabel(new ImageIcon(getClass().getResource(rutaIcono)));
	        JLabel lblNombre = new JLabel(nombre);
	        JLabel lblPrecio = new JLabel(precio);

	        lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
	        lblPrecio.setFont(new Font("Arial", Font.BOLD, 16));

	        // Aplicar márgenes
	        lblIcono.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	        lblNombre.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	        lblPrecio.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

	        // Crear el botón y su panel
	        JButton btnComprar = new JButton("Comprar");
	        btnComprar.setActionCommand(nombre);
	        btnComprar.setPreferredSize(new Dimension(90, 40));

	        JPanel panelBoton = new JPanel(new GridBagLayout());
	        panelBoton.setOpaque(false);  // Para mantener el color de fondo de la fila
	        panelBoton.add(btnComprar);  // Centra el botón automáticamente

	        // Agregar los componentes a la fila
	        fila.add(lblIcono);
	        fila.add(lblNombre);
	        fila.add(lblPrecio);
	        fila.add(panelBoton);

	        // Guardar el botón y agregar la fila al panel central
	        botonesCompra.put(nombre, btnComprar);
	        panelCentral.add(fila);
	    }
	    
	public JPanel getPanelCentral() {
			return panelCentral;
		}

		public void setPanelCentral(JPanel panelCentral) {
			this.panelCentral = panelCentral;
		}

		public JPanel getPanelSouth() {
			return panelSouth;
		}

		public void setPanelSouth(JPanel panelSouth) {
			this.panelSouth = panelSouth;
		}

		public JLabel getLabelValorUsd() {
			return labelValorUsd;
		}

		public void setLabelValorUsd(JLabel labelValorUsd) {
			this.labelValorUsd = labelValorUsd;
		}

		public JLabel getLabelNombreCripto() {
			return labelNombreCripto;
		}

		public void setLabelNombreCripto(JLabel labelNombreCripto) {
			this.labelNombreCripto = labelNombreCripto;
		}

		public JButton getBotonVolver() {
			return botonVolver;
		}

		public void setBotonVolver(JButton botonVolver) {
			this.botonVolver = botonVolver;
		}

		public JPanel getPanelTop() {
			return panelTop;
		}

		public void setPanelTop(JPanel panelTop) {
			this.panelTop = panelTop;
		}

		public Map<String, JButton> getBotonesCompra() {
			return botonesCompra;
		}

		public void setBotonesCompra(Map<String, JButton> botonesCompra) {
			this.botonesCompra = botonesCompra;
		}

	@Override
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(new GradientPaint(0, 0, new Color(47,224,189), getWidth(), getHeight(),new Color (255,127,172)));
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
	
	
}
