package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class PanelActivos extends JPanel {
	
	private JPanel panelCentral ;
	private JPanel panelTop;
	private JPanel panelWestTop;
	private JPanel panelWest;
	private JPanel panelEast;
	private JButton botonPrueba;
	private JButton botonExportar;
	private JButton botonHistorial;
	private JButton botonCotizaciones;
	private ComponenteCircular componenteIniciales;
	private JButton botonLogout;
	private JLabel labelBalance;
	private JTable tablaActivos;
	private ModeloTablaActivos tablaModelo;
	private Object [][] datos ={ {new ImageIcon("Bitcoin.png"),"Bitcoin",0} , {new ImageIcon("Ethereum.png"),"Ethereum",0}, {new ImageIcon("Tether.png"),"Tether",0}};
	private JScrollPane scrollPane ;
	
	public PanelActivos () {
		setLayout(new BorderLayout());
		setOpaque(false);
		
		panelCentral = new JPanel();
		panelCentral.setOpaque(false);
	 
		tablaModelo = new ModeloTablaActivos(datos);
		tablaActivos = new JTable(tablaModelo);
		tablaActivos.setRowHeight(64);
		scrollPane = new JScrollPane(tablaActivos);
				
		panelCentral.add(scrollPane);
		
		add(panelCentral,BorderLayout.CENTER);
		
        // Configurar panel superior (NORTH)
        panelTop = new JPanel(new BorderLayout());
        panelTop.setOpaque(true);
        panelTop.setForeground(Color.WHITE);
        panelTop.setPreferredSize(new Dimension(0, 80));
        
        // Crear JLabel para el balance
        labelBalance = new JLabel("Balance: $0.00");
        labelBalance.setFont(new Font("Arial", Font.BOLD, 18));
        labelBalance.setForeground(Color.BLACK);
        labelBalance.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Alinear JLabel a la derecha
        panelTop.add(labelBalance, BorderLayout.EAST);
        
        //Panel WestTop
        panelWestTop = new JPanel(new FlowLayout());
        
        componenteIniciales = new ComponenteCircular("MG"); // Iniciales del usuario
        panelWestTop.add(componenteIniciales);
        
        botonLogout = new JButton ("Cerrar Sesion");
        botonLogout.setBackground(new Color(189, 0, 3));  // Color de fondo
        botonLogout.setForeground(Color.WHITE);  // Color del texto
        botonLogout.setFont(new Font("Arial", Font.BOLD, 14));  // Fuente y tamaño del texto
        botonLogout.setFocusPainted(false);  // Quitar el borde de enfoque cuando se hace clic
        botonLogout.setBorderPainted(false);  // Quitar el borde por defecto


        panelWestTop.add(botonLogout);
        panelTop.add(panelWestTop,BorderLayout.WEST);
        
        //Panel West
        panelWest = new JPanel (new FlowLayout()) ;
        panelWest.setOpaque(false);
        //Boton de GenerarDatosDePrueba
        botonPrueba = new JButton("Generar stock aleatorio");
        botonPrueba.setBackground(new Color(30,20,10));
        botonPrueba.setForeground(Color.WHITE);
        botonPrueba.setFont(new Font("Arial", Font.BOLD, 12));
        botonPrueba.setFocusPainted(false);
        botonPrueba.setBorderPainted(false);
        
        panelWest.add(botonPrueba);
        
        //Panel East
        panelEast = new JPanel(new FlowLayout());
        panelEast.setOpaque(false);
        botonExportar = new JButton("Exportar como CSV");
        botonExportar.setBackground(new Color(20,225,20));
        botonExportar.setForeground(Color.WHITE);
        botonExportar.setFont(new Font("Arial", Font.PLAIN, 12));
        botonExportar.setFocusPainted(false);
        botonExportar.setBorderPainted(false);
        panelEast.add(botonExportar);
        
        
        JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Alineación centrada con margen
        panelSouth.setOpaque(false);
        //Boton Historial
       
        botonHistorial = new JButton("Historial");
        botonHistorial.setBackground(new Color(143,219,241));
        botonHistorial.setForeground(Color.WHITE);
        botonHistorial.setFont(new Font("Arial", Font.BOLD, 16));
        botonHistorial.setFocusPainted(false);
        botonHistorial.setBorderPainted(false);
        botonHistorial.setIcon(new ImageIcon("Historial.png"));
        botonHistorial.setVerticalTextPosition(SwingConstants.TOP); // Texto arriba
        botonHistorial.setHorizontalTextPosition(SwingConstants.CENTER); // Texto centrado horizontalmente
        botonHistorial.setIconTextGap(5); // Espaciado entre el texto y el icono
        
        //Boton Cotizaciones
        
        botonCotizaciones = new JButton("Cotizaciones");
        botonCotizaciones.setBackground(new Color(143,219,241));
        botonCotizaciones.setForeground(Color.WHITE);
        botonCotizaciones.setFont(new Font("Arial", Font.BOLD, 16));
        botonCotizaciones.setFocusPainted(false);
        botonCotizaciones.setBorderPainted(false);
        botonCotizaciones.setIcon(new ImageIcon("Cotizaciones.png"));
        botonCotizaciones.setVerticalTextPosition(SwingConstants.TOP); // Texto arriba
        botonCotizaciones.setHorizontalTextPosition(SwingConstants.CENTER); // Texto centrado horizontalmente
        botonCotizaciones.setIconTextGap(5); // Espaciado entre el texto y el icono
        
        
      
        panelSouth.add(botonHistorial);
   
        panelSouth.add(botonCotizaciones);
   
        add(panelSouth,BorderLayout.SOUTH);
        add(panelEast,BorderLayout.EAST);
        add(panelWest,BorderLayout.WEST);
        add(panelTop, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);
        
    }

   
    public void actualizarBalance(double nuevoBalance) {
        labelBalance.setText(String.format("Balance: $%.2f", nuevoBalance));
    }

	@Override
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(new GradientPaint(0, 0, new Color(47,224,189), getWidth(), getHeight(),new Color (255,127,172)));
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
	
	private static class ComponenteCircular extends JComponent {
        private String iniciales;
        private final int diameter = 75; 

        public ComponenteCircular(String iniciales) {
            this.iniciales = iniciales;
            setPreferredSize(new Dimension(diameter, diameter));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

          
            g2d.setPaint(new GradientPaint(0, 0, new Color(47,224,189), getWidth(), getHeight(),new Color (255,127,172)));
            g2d.fillOval(0, 0, diameter, diameter);

            // Dibujar las iniciales en el centro
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 20));
            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(iniciales);
            int textHeight = fm.getAscent();
            int x = (diameter - textWidth) / 2;
            int y = (diameter + textHeight) / 2 - 2;
            g2d.drawString(iniciales, x, y);
        }
        
        public void setIniciales(String nuevasIniciales) {
            this.iniciales = nuevasIniciales;
            repaint(); // Vuelve a dibujar el componente
        }
	}

	public JPanel getPanelCentral() {
		return panelCentral;
	}


	public void setPanelCentral(JPanel panelCentral) {
		this.panelCentral = panelCentral;
	}


	public JPanel getPanelTop() {
		return panelTop;
	}


	public void setPanelTop(JPanel panelTop) {
		this.panelTop = panelTop;
	}


	public JPanel getPanelWestTop() {
		return panelWestTop;
	}


	public void setPanelWestTop(JPanel panelWestTop) {
		this.panelWestTop = panelWestTop;
	}


	public JPanel getPanelWest() {
		return panelWest;
	}


	public void setPanelWest(JPanel panelWest) {
		this.panelWest = panelWest;
	}


	public JPanel getPanelEast() {
		return panelEast;
	}


	public void setPanelEast(JPanel panelEast) {
		this.panelEast = panelEast;
	}


	public JButton getBotonPrueba() {
		return botonPrueba;
	}


	public void setBotonPrueba(JButton botonPrueba) {
		this.botonPrueba = botonPrueba;
	}


	public JButton getBotonExportar() {
		return botonExportar;
	}


	public void setBotonExportar(JButton botonExportar) {
		this.botonExportar = botonExportar;
	}


	public JButton getBotonHistorial() {
		return botonHistorial;
	}


	public void setBotonHistorial(JButton botonHistorial) {
		this.botonHistorial = botonHistorial;
	}


	public JButton getBotonCotizaciones() {
		return botonCotizaciones;
	}


	public void setBotonCotizaciones(JButton botonCotizaciones) {
		this.botonCotizaciones = botonCotizaciones;
	}


	public ComponenteCircular getComponenteIniciales() {
		return componenteIniciales;
	}


	public void setComponenteIniciales(ComponenteCircular componenteIniciales) {
		this.componenteIniciales = componenteIniciales;
	}


	public JButton getBotonLogout() {
		return botonLogout;
	}


	public void setBotonLogout(JButton botonLogout) {
		this.botonLogout = botonLogout;
	}


	public JLabel getLabelBalance() {
		return labelBalance;
	}


	public void setLabelBalance(JLabel labelBalance) {
		this.labelBalance = labelBalance;
	}


	public JTable getTablaActivos() {
		return tablaActivos;
	}


	public void setTablaActivos(JTable tablaActivos) {
		this.tablaActivos = tablaActivos;
	}


	public ModeloTablaActivos getTablaModelo() {
		return tablaModelo;
	}


	public void setTablaModelo(ModeloTablaActivos tablaModelo) {
		this.tablaModelo = tablaModelo;
	}


	public Object[][] getDatos() {
		return datos;
	}


	public void setDatos(Object[][] datos) {
		this.datos = datos;
	}


	public JScrollPane getScrollPane() {
		return scrollPane;
	}


	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}
	
	
}
