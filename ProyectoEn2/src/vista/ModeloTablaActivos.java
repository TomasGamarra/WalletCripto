package vista;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

public class ModeloTablaActivos extends DefaultTableModel {
		private static final String [] columnas = {"","Moneda","Monto (USD)"};
		
		 public ModeloTablaActivos(Object [][] datos) {
		        super(datos, columnas);
		    }
		 		

		    public ModeloTablaActivos() {
		    	super(null,columnas);
		    }


			@Override
		    public Class<?> getColumnClass(int columnIndex) {
			   if (this.getRowCount() > 0)
				   return this.getValueAt(0,columnIndex).getClass();
			   else
				 return String.class;
		    }

		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false; //Ninguna columna es editable
		    }
		    
		    public void cambiarColumna(String nuevaColumna) {
		
		        String[] nuevasColumnas = columnas.clone();

		        nuevasColumnas[2] = nuevaColumna;

		        this.setColumnIdentifiers(nuevasColumnas);
		    }
}

		

