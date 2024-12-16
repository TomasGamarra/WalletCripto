package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableRowSorter;

import enumerativos.ImagenesEnum;
import sistema.MonedaFiatEnum;

public class PanelActivos extends JPanel {
	
	private JPanel panelCentral ;
	private JPanel panelTop;
	private JPanel panelWestTop;
	private JPanel panelWest;
	private JPanel panelEast;
	private JPanel panelSouth;
	private JButton botonPrueba;
	private JButton botonExportar;
	private JButton botonHistorial;
	private JButton botonCotizaciones;
	private ComponenteCircular componenteIniciales;
	private JButton botonLogout;
	private JLabel labelBalance;
	private JTable tablaActivos;
	private ModeloTablaActivos tablaModelo;	
	private JScrollPane scrollPane ;
	private JLabel labelNombre;
	private TableRowSorter<ModeloTablaActivos> sorter ;
	private JButton botonPesos;
	private String monedaActual=MonedaFiatEnum.DOLARES.getNomenclatura();
	private JButton botonDolar;
	private JPanel panelEastNorth;
	
	public PanelActivos () {
		setLayout(new BorderLayout());
		setOpaque(false);
		
		panelCentral = new JPanel();
		panelCentral.setOpaque(false);
	 
		tablaModelo = new ModeloTablaActivos();
		tablaActivos = new JTable(tablaModelo);
		tablaActivos.setRowHeight(64);
		
		sorter = new TableRowSorter<>(tablaModelo);
		tablaActivos.setRowSorter(sorter); 
		
		scrollPane = new JScrollPane(tablaActivos);	
		panelCentral.add(scrollPane);
		
		
		
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

        
        panelTop.add(labelBalance, BorderLayout.EAST);
        
        //Panel WestTop
        panelWestTop = new JPanel(new FlowLayout());
        
        componenteIniciales = new ComponenteCircular("MG"); // Iniciales del usuario
        
        panelWestTop.add(componenteIniciales);
        

        
        labelNombre = new JLabel("Nombre Apellido");
        labelNombre.setOpaque(false);
        labelNombre.setFont(new Font("Arial", Font.BOLD, 15));
        labelNombre.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        panelWestTop.add(labelNombre);
        
        panelTop.add(panelWestTop,BorderLayout.WEST);
        
        //Panel West
        panelWest = new JPanel (new FlowLayout()) ;
        panelWest.setOpaque(false);
        
        botonLogout = new JButton ("Cerrar Sesion");
        botonLogout.setBackground(new Color(189, 0, 3));  // Color de fondo
        botonLogout.setForeground(Color.WHITE);  // Color del texto
        botonLogout.setFont(new Font("Arial", Font.BOLD, 14));  // Fuente y tamaño del texto
        botonLogout.setFocusPainted(false);  // Quitar el borde de enfoque cuando se hace clic
       
        
        panelWest.add(botonLogout);
        
        
  
        //Panel East y PanelEastNorth
        panelEast = new JPanel(new BorderLayout());
        panelEast.setOpaque(false);
        
        panelEastNorth = new JPanel(new GridBagLayout());
        panelEastNorth.setOpaque(false);
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.insets=new Insets(5,5,5,5);
        gbc.gridx=0;
        gbc.gridy=0;
        
        botonExportar = new JButton("Exportar como CSV");
        botonExportar.setBackground(new Color(20,225,20));
        botonExportar.setForeground(Color.WHITE);
        botonExportar.setFont(new Font("Arial", Font.PLAIN, 12));
        botonExportar.setFocusPainted(false);
   
        
        
        panelEastNorth.add(botonExportar,gbc);
       
        
        //Boton de GenerarDatosDePrueba
        botonPrueba = new JButton("Generar datos de prueba");
        botonPrueba.setBackground(new Color(30,20,10));
        botonPrueba.setForeground(Color.WHITE);
        botonPrueba.setFont(new Font("Arial", Font.BOLD, 12));
        botonPrueba.setFocusPainted(false);
       
        gbc.gridx++;
        
        panelEastNorth.add(botonPrueba);
        
        botonPesos = new JButton("Cambiar monto a Pesos (ARS)");
        botonPesos.setBackground(new Color(30,20,90));
        botonPesos.setForeground(Color.WHITE);
        botonPesos.setFont(new Font("Arial", Font.BOLD, 12));
        botonPesos.setFocusPainted(false);

        gbc.gridx=0;
        gbc.gridy++;
        
        panelEastNorth.add(botonPesos,gbc);
        
        gbc.gridx++;
        
        botonDolar = new JButton("Cambiar monto a Dolar (USD)");
        botonDolar.setBackground(new Color(20,90,10));
        botonDolar.setForeground(Color.WHITE);
        botonDolar.setFont(new Font("Arial", Font.BOLD, 12));
        botonDolar.setFocusPainted(false);
        
        panelEastNorth.add(botonDolar,gbc);
        
        panelEast.add(panelEastNorth,BorderLayout.NORTH);
        panelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Alineación centrada con margen
        panelSouth.setOpaque(false);
        
        //Boton Historial
       
        botonHistorial = new JButton("Historial");
        botonHistorial.setBackground(new Color(143,219,241));
        botonHistorial.setForeground(Color.WHITE);
        botonHistorial.setFont(new Font("Arial", Font.BOLD, 16));
        botonHistorial.setFocusPainted(false);
        botonHistorial.setIcon(new ImageIcon(getClass().getResource(ImagenesEnum.ICONOHISTORIAL.getRutaFoto())));
        botonHistorial.setVerticalTextPosition(SwingConstants.TOP); // Texto arriba
        botonHistorial.setHorizontalTextPosition(SwingConstants.CENTER); // Texto centrado horizontalmente
        botonHistorial.setIconTextGap(5); // Espaciado entre el texto y el icono
        
        //Boton Cotizaciones
        
        botonCotizaciones = new JButton("Cotizaciones");
        botonCotizaciones.setBackground(new Color(143,219,241));
        botonCotizaciones.setForeground(Color.WHITE);
        botonCotizaciones.setFont(new Font("Arial", Font.BOLD, 16));
        botonCotizaciones.setFocusPainted(false);
        botonCotizaciones.setIcon(new ImageIcon(getClass().getResource(ImagenesEnum.ICONOCOTIZACIONES.getRutaFoto())));
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
	
	
	public void limpiarTabla() {
		this.getTablaModelo().setRowCount(0);
		tablaActivos.setRowSorter(null);
		TableRowSorter<ModeloTablaActivos> sorter = new TableRowSorter<>(tablaModelo);
		tablaActivos.setRowSorter(sorter); 
	}
	
	
	public void cambiarColumna(String columna) {
		tablaModelo.cambiarColumna("Monto ("+columna+")");
		setMonedaActual(columna);
	}
	public void aniadirFila(Object [] fila) {
		tablaModelo.addRow(fila);
	}
	
	public void actualizarNombre(String nombre) {
		 labelNombre.setText(nombre);
	}
	public int getLength() {
		return tablaModelo.getRowCount();
	}
	
	public JButton getBotonDolar() {
		return botonDolar;
	}
	
	public void actualizarValor(int fila,float conversion) {
		if (fila >= 0 && fila < getLength()) {
	        tablaModelo.setValueAt(conversion, fila, 2);  
	    } else {
	        System.out.println("Fila fuera de los límites del modelo.");
	    }
	}
	
	public JButton getBotonPesos() {
		return botonPesos;
	}
	
	
	public float getValorTabla(int i) {
		Object valor = tablaModelo.getValueAt(i, 2);
		 if (valor instanceof Number) {
		        return ((Number) valor).floatValue();  
		    } else if (valor instanceof String) {
		        try {
		            return Float.parseFloat((String) valor);  
		        } catch (NumberFormatException e) {
		            System.out.println("Error al convertir a float: " + e.getMessage());
		        }
		    }
		    
		    return 0.0f;  // Retorna un valor por defecto si la conversion falla
	}
	
	public void actualizarIniciales (String nuevasIniciales) {
		componenteIniciales.setIniciales(nuevasIniciales);
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


	public JScrollPane getScrollPane() {
		return scrollPane;
	}


	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}


	public JPanel getPanelSouth() {
		return panelSouth;
	}


	public void setPanelSouth(JPanel panelSouth) {
		this.panelSouth = panelSouth;
	}


	public JLabel getLabelNombre() {
		return labelNombre;
	}


	public void setLabelNombre(JLabel labelNombre) {
		this.labelNombre = labelNombre;
	}


	public String getMonedaActual() {
		return monedaActual;
	}


	public void setMonedaActual(String monedaActual) {
		this.monedaActual = monedaActual;
	}
	
	
}