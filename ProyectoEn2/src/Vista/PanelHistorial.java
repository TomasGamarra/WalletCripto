package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PanelHistorial extends JPanel{

	    private JTable tablaTransacciones;
	    private DefaultTableModel modeloTabla;
	    private JLabel labelTitulo;
	    private JScrollPane scrollPane;
	    private JButton botonVolver;
	    private JTextField campoFiltro;
	    private JButton botonFiltrar;
	    private JPanel panelInferior;
	    private JPanel panelFiltros;
	    

	    public PanelHistorial() {
	        setLayout(new BorderLayout());
	        setOpaque(false);

	        
	        labelTitulo = new JLabel("Historial de Transacciones", JLabel.CENTER);
	        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));
	        labelTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //Se le hace un borde a la label para mas espaciado
	        
	        add(labelTitulo, BorderLayout.NORTH);

	        // Panel central : Tabla
	        modeloTabla = new DefaultTableModel(new Object[]{"Fecha", "Tipo", "Monto", "Moneda"}, 0); //Tendriamos que hacer un modelo especifico
	        tablaTransacciones = new JTable(modeloTabla);
	        tablaTransacciones.setRowHeight(30);

	        scrollPane = new JScrollPane(tablaTransacciones); 
	        
	        add(scrollPane, BorderLayout.CENTER);

	        // Panel inferior: Filtros y botón "Volver"
	        panelInferior = new JPanel(new BorderLayout());
	        panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //Para mayor espaciado
	        

	        // Panel de filtros
	        panelFiltros = new JPanel(new FlowLayout());
	        campoFiltro = new JTextField(15);
	        botonFiltrar = new JButton("Filtrar");
	        
	        panelFiltros.add(new JLabel("Filtrar por moneda:")); 
	        panelFiltros.add(campoFiltro);
	        panelFiltros.add(botonFiltrar);
	        
	        panelInferior.add(panelFiltros, BorderLayout.WEST);
	        

	        // Boton Volver
	        botonVolver = new JButton("Volver");
	        botonVolver.setBackground(new Color(189, 0, 3)); 
	        botonVolver.setForeground(Color.WHITE);
	        botonVolver.setFont(new Font("Arial", Font.BOLD, 14));
	        botonVolver.setFocusPainted(false);
	        
	        
	        panelInferior.add(botonVolver, BorderLayout.EAST);

	        add(panelInferior, BorderLayout.SOUTH);
	    }

	   
	    public JButton getBotonVolver() {
	        return botonVolver;
	    }

	    public JButton getBotonFiltrar() {
	        return botonFiltrar;
	    }

	    public String getTextoFiltro() {
	        return campoFiltro.getText();
	    }
	    
	    @Override
		protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setPaint(new GradientPaint(0, 0, new Color(47,224,189), getWidth(), getHeight(),new Color (255,127,172)));
	        g2d.fillRect(0, 0, getWidth(), getHeight());
	    }


		public JTable getTablaTransacciones() {
			return tablaTransacciones;
		}


		public void setTablaTransacciones(JTable tablaTransacciones) {
			this.tablaTransacciones = tablaTransacciones;
		}


		public DefaultTableModel getModeloTabla() {
			return modeloTabla;
		}


		public void setModeloTabla(DefaultTableModel modeloTabla) {
			this.modeloTabla = modeloTabla;
		}


		public JLabel getLabelTitulo() {
			return labelTitulo;
		}


		public void setLabelTitulo(JLabel labelTitulo) {
			this.labelTitulo = labelTitulo;
		}


		public JScrollPane getScrollPane() {
			return scrollPane;
		}


		public void setScrollPane(JScrollPane scrollPane) {
			this.scrollPane = scrollPane;
		}


		public JTextField getCampoFiltro() {
			return campoFiltro;
		}


		public void setCampoFiltro(JTextField campoFiltro) {
			this.campoFiltro = campoFiltro;
		}


		public JPanel getPanelInferior() {
			return panelInferior;
		}


		public void setPanelInferior(JPanel panelInferior) {
			this.panelInferior = panelInferior;
		}


		public JPanel getPanelFiltros() {
			return panelFiltros;
		}


		public void setPanelFiltros(JPanel panelFiltros) {
			this.panelFiltros = panelFiltros;
		}


		public void setBotonVolver(JButton botonVolver) {
			this.botonVolver = botonVolver;
		}


		public void setBotonFiltrar(JButton botonFiltrar) {
			this.botonFiltrar = botonFiltrar;
		}
	    
	}



