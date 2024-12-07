package Vista;



import javax.swing.*;

import Controlador.Controlador;
import Controlador.Modelo;

import java.awt.*;

public class PruebaVista {
    public static void main(String[] args) {
    	Vista vista =new Vista();
    	Modelo modelo = new Modelo();
    	Controlador cont = new Controlador(vista,modelo);
    	
    }
}

 