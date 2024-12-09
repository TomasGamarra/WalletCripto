package Vista;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

public class ModeloTablaActivos extends DefaultTableModel {
		private static final String [] columnas = {"","Moneda","Monto"};
		
		 public ModeloTablaActivos(Object [][] datos) {
		        super(datos, columnas);
		    }
		 
		 
		

		    @Override
		    public Class<?> getColumnClass(int columnIndex) {
		        switch (columnIndex) {
		            case 0: return ImageIcon.class; // Primera columna para imágenes
		            case 2: return String.class;    // Tercera columna para montos
		            default: return String.class;  // Segunda columna para nombres
		        }
		    }

		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false; //Ninguna columna es editable
		    }
		    
}

		

