package Vista;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PanelActivos extends JPanel {
	
	private JPanel panelCentral ;
	private JTable tablaActivos;
	private ModeloTablaActivos tablaModelo;
	private Object [][] datos ={ {new ImageIcon("Bitcoin.png"),"Bitcoin",0} , {new ImageIcon("Ethereum.png"),"Ethereum",0}, {new ImageIcon("Tether.png"),"Tether",0}};
	private JScrollPane scrollPane ;
	public PanelActivos () {
		setLayout(new BorderLayout());
		panelCentral = new JPanel();
		
	 
		tablaModelo = new ModeloTablaActivos(datos);
		tablaActivos = new JTable(tablaModelo);
		tablaActivos.setRowHeight(64);
		scrollPane = new JScrollPane(tablaActivos);
				
		panelCentral.add(scrollPane);
		
		add(panelCentral,BorderLayout.CENTER);
		
		
		
	}

}
