package Vista;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

public class ModeloTablaActivos extends DefaultTableModel {
		private static final String [] columnas = {"","Moneda","Monto"};
		
		 public ModeloTablaActivos(Object [][] datos) {
		        super(datos, columnas);
		    }
		 		

		    public ModeloTablaActivos() {
		    	super(null,columnas);
		    }


			@Override
		    public Class<?> getColumnClass(int columnIndex) {
		       return this.getValueAt(0,columnIndex).getClass();
		    }

		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false; //Ninguna columna es editable
		    }
		    
}

		

