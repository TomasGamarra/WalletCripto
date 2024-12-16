package vista;

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

import enumerativos.CriptomonedaEnum;
import excepciones.RequestException;
import gestores.ServicioCotizaciones;

public class PanelCotizaciones extends JPanel {
	private JPanel panelCentral;
	private JPanel panelSouth;
	private JLabel labelValorUsd;
	private JLabel labelNombreCripto;
	private JButton botonVolver;
	private JPanel panelTop;
	private Map <String, JLabel> cotizaciones = new HashMap <>();
	private Map<String, JButton> botonesCompra = new HashMap<>();
		
		
	public PanelCotizaciones() {
	    setLayout(new BorderLayout());
	    setOpaque(false);
	    
	    //PanelCentral
	    panelCentral = new JPanel();
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
		panelCentral.setOpaque(false);
		botonVolver = new JButton("Volver");
		
		//PanelSouth
		panelSouth = new JPanel();
		panelSouth.add(botonVolver);
		panelSouth.add(botonVolver,SwingConstants.CENTER);	
		panelSouth.setOpaque(false);
					
		add(panelCentral,BorderLayout.CENTER);
		add(panelSouth,BorderLayout.SOUTH);
    
	}

	public void cargarDatos(String [][] tabla) {
		for (String [] fila : tabla) 
			agregarFilaCotizacion(fila[0],fila[1],fila[2],fila[3]);
		
	}

	private void agregarFilaCotizacion(String rutaIcono, String nombre,String nomenclatura , String precio) {
		JPanel fila = new JPanel(new GridLayout(1, 4));
		fila.setOpaque(false);
		        
		        
        JLabel lblIcono = new JLabel(new ImageIcon(getClass().getResource(rutaIcono)));
        JLabel lblNombre = new JLabel(nombre + "(" + nomenclatura + ")");
        JLabel lblPrecio = new JLabel("$"+precio);
        
        lblIcono.setOpaque(false);
        lblNombre.setOpaque(false);
        lblPrecio.setOpaque(false);
		        
        lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
        lblPrecio.setFont(new Font("Arial", Font.BOLD, 16));

        // Espaciado
        lblIcono.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        lblNombre.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        lblPrecio.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Boton y PanelBoton
        JButton btnComprar = new JButton("Comprar");
        btnComprar.setActionCommand(nomenclatura); //Para luego diferenciar los datos en los listeners
        
        btnComprar.setPreferredSize(new Dimension(90, 40));
        btnComprar.setBackground(new Color(20,140,20));
        btnComprar.setFont(new Font("Arial", Font.BOLD, 12));
		        
        JPanel panelBoton = new JPanel(new GridBagLayout());
        panelBoton.setOpaque(false);
		        
        panelBoton.add(btnComprar);  

        
        fila.add(lblIcono);
        fila.add(lblNombre);
        fila.add(lblPrecio);
        fila.add(panelBoton);

        // Guardar el boton y agregar la fila al panel central
        botonesCompra.put(nomenclatura, btnComprar);
        cotizaciones.put(nomenclatura,lblPrecio);
       
        panelCentral.add(fila);
		}
		    
	@Override
	protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setPaint(new GradientPaint(0, 0, new Color(47,224,189), getWidth(), getHeight(),new Color (255,127,172)));
	        g2d.fillRect(0, 0, getWidth(), getHeight());
	    }
			
	public void setCotizacion (String nombre ,float cotizacion) {	
		JLabel label = cotizaciones.get(nombre);
		if (label != null)
			label.setText("$"+cotizacion);
	}
	
	public JButton getBoton (String nombre) {
		return botonesCompra.get(nombre);
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

	
	
}
