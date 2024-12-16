package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelCompra extends JPanel {
	    private JLabel labelStockDisponible, labelPrecioCompra;
	    private JComboBox<String> comboMonedasFiat;
	    private JTextField fieldCantidad;
	    private JButton btnRealizarCompra, btnCancelar, btnConvertir;
	    private JLabel labelSeleccione;
	    private JLabel labelCantidad;
	    private JLabel labelEquivale;
	    private JPanel panelCentral;
	    private JPanel panelSouth;
	    private JLabel labelValorCripto;
	    private JLabel labelStock;
	    private JLabel labelConversion;
	    
	    public PanelCompra() {
	        
	        setLayout(new BorderLayout());
	        setOpaque(false);
	     
	        panelCentral = new JPanel();
	        
	        panelCentral.setLayout(new GridBagLayout());  // 4 filas, 2 columnas
	        panelCentral.setOpaque(false);
	      
	        
	        // Añadir componentes al panel central
	        
	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.insets= new Insets(10,10,10,10);
	        
	      
	        
	        labelStockDisponible = new JLabel("Stock Disponible: ");
	        labelStockDisponible.setFont(new Font("Arial", Font.BOLD, 14));
	        gbc.gridx=0;
	        gbc.gridy=0;
	        labelStockDisponible.setOpaque(false);
	        panelCentral.add(labelStockDisponible,gbc);
	        
	        
	        
	        labelPrecioCompra = new JLabel("Precio de compra: ");
	        labelPrecioCompra.setFont(new Font("Arial", Font.BOLD, 14));
	        labelPrecioCompra.setOpaque(false);
	        gbc.gridy=1;
	        panelCentral.add(labelPrecioCompra,gbc);

	        gbc.gridy=2;
	        
	        labelSeleccione = new JLabel("Selecciona Moneda Fiat:");
	        labelSeleccione.setFont(new Font("Arial", Font.BOLD, 14));
	        panelCentral.add(labelSeleccione,gbc);
	        
	        
	        labelCantidad=new JLabel("Cantidad:");
	        labelCantidad.setFont(new Font("Arial", Font.BOLD, 14));
	        gbc.gridy=3;
	        
	        panelCentral.add(labelCantidad,gbc);
	        
	        comboMonedasFiat = new JComboBox<>();
	        
	        gbc.gridx=1;
	        gbc.gridy=2;
	        
	        panelCentral.add(comboMonedasFiat,gbc);  
	        
	        fieldCantidad = new JTextField(20);
	       
	        gbc.gridx=1;
	        gbc.gridy=3;
	        
	        panelCentral.add(fieldCantidad,gbc);
	        
	        
	        btnConvertir = new JButton("Convertir");
	      
	        gbc.gridx=3;
	        gbc.gridy=3;
	        
	        panelCentral.add(btnConvertir,gbc);
	        
	        labelEquivale = new JLabel("Equivale a : ");
	        labelEquivale.setFont(new Font("Arial", Font.BOLD, 14));
	        gbc.gridy=4;
	        gbc.gridx=0;
	        
	        panelCentral.add(labelEquivale,gbc);
	    
	        panelSouth = new JPanel();
	        panelSouth.setOpaque(false);
	        
	        panelSouth.setLayout(new FlowLayout(FlowLayout.CENTER));
	        
	        btnRealizarCompra = new JButton("Realizar Compra");
	        btnCancelar = new JButton("Cancelar");
	        
	        
	        labelValorCripto = new JLabel("");
	        
	        gbc.gridx=1;
	        gbc.gridy=1;
	        
	        panelCentral.add(labelValorCripto,gbc);
	       
	        labelStock = new JLabel("");
	        gbc.gridx=1;
	        gbc.gridy=0;
	        
	       
	        
	        panelCentral.add(labelStock,gbc);
	        
	        labelConversion= new JLabel("");
	        gbc.gridx=1;
	        gbc.gridy=4;
	        labelConversion.setFont(new Font("Arial", Font.BOLD, 14));
	        panelCentral.add(labelConversion,gbc);
	        
	        panelSouth.add(btnRealizarCompra);
	        panelSouth.add(btnCancelar);
	        
	       
	        add(panelCentral, BorderLayout.CENTER);
	        add(panelSouth, BorderLayout.SOUTH);
	    }
	    

	 @Override
	 protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	        
	    Graphics2D g2d = (Graphics2D) g;
	    g2d.setPaint(new GradientPaint(0, 0, new Color(47,224,189), getWidth(), getHeight(),new Color (255,127,172)));
	     g2d.fillRect(0, 0, getWidth(), getHeight());
	}
		    
	    
    public void actualizarMonedasFiat(List<String> monedas) {
	    for (String moneda : monedas) 
	       comboMonedasFiat.addItem(moneda);
	   
   }
    
    public JButton getBotonConvertir() {
    	return btnConvertir;
    }
    
    public JButton getBotonCompra() {
    	return btnRealizarCompra;
    }
    
    public JButton getCancelarBoton() {
    	return btnCancelar;
    }

	public void setPrecioCripto(float valor) {
		labelValorCripto.setText("$"+ String.format("%.3f", valor));
	}
	
	public void setStockDisponible (float stock, String nomenclatura) {
		labelStock.setText( String.format("%.4f ",stock ) + nomenclatura);
	}
	
	public float getCantidadFiat () {
		return Float.parseFloat(fieldCantidad.getText());
	}
	
	public void setConversion(float cantidad,String nomenclatura) {
		labelConversion.setText(String.format("%.4f ", cantidad) + nomenclatura);
	}
	
	public String getFiat() {
		String seleccion = (String) comboMonedasFiat.getSelectedItem();
		return seleccion;
	}
}
