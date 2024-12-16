package vista;

import javax.swing.table.DefaultTableModel;

public class ModeloTablaHistorial extends DefaultTableModel {


	 public ModeloTablaHistorial() {
	        super(new Object[]{"Fecha", "Tipo", "Resumen"}, 0);
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
