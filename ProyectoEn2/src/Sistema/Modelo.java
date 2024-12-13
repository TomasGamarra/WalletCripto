package Sistema;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import Excepciones.RequestException;
import gestores.FactoryDAO;
import interfaces_DAO.ActivoCriptoDAO;
import interfaces_DAO.ActivoFiatDAO;
import interfaces_DAO.CriptoDAO;
import interfaces_DAO.FiatDAO;
import interfaces_DAO.PersonaDAO;
import interfaces_DAO.StockDAO;
import interfaces_DAO.TransaccionDAO;
import interfaces_DAO.UsuarioDAO;

public class Modelo {
	private ActivoCriptoDAO activoCriptoDAO ;
	private ActivoFiatDAO activoFiatDAO;
	private PersonaDAO personaDAO;
	private StockDAO stockDAO;
	private TransaccionDAO transaccionDAO;
	private UsuarioDAO usuarioDAO;
	private CriptoDAO criptoDAO;
	private FiatDAO fiatDAO;
	
	public Modelo () {
		activoCriptoDAO = FactoryDAO.getActivoCriptoDAO();
		usuarioDAO = FactoryDAO.getUsuarioDAO();
		personaDAO=FactoryDAO.getPersonaDAO();
		stockDAO =FactoryDAO.getStockDAO();
		transaccionDAO=FactoryDAO.getTransaccionDAO();
		activoFiatDAO=FactoryDAO.getActivoFiatDAO();
		criptoDAO =FactoryDAO.getCriptoDAO();
		fiatDAO = FactoryDAO.getFiatDAO();
		
		
		
		//Todo lo de map es para solucionar error 409 por acceder muchas veces seguidas 
        LinkedList<String> lista = new LinkedList<>();
        
        for (CriptomonedaEnum cripto : CriptomonedaEnum.values()) 
  		   lista.add(cripto.getClaveApi());
        
        Map <String,Float> map;
        
        try {
  
		 map= ServicioCotizaciones.obtenerPrecios(lista);
		
        
		
		Random ran = new Random();
	
		
		for (CriptomonedaEnum cripto : CriptomonedaEnum.values()) {
		    criptoDAO.create(new Criptomoneda(
		        cripto.getNombre(), 
		        cripto.getNomenclatura(), 
		        map.get(cripto.getClaveApi()), 
		        1, 
		        cripto.getRutaIcono()));
		    
		    stockDAO.create(criptoDAO.obtenerIdCripto(cripto.getNomenclatura()), ran.nextFloat() * 3 + 8);
		}
		
		

		for (MonedaFiatEnum fiat : MonedaFiatEnum.values()) {
		    fiatDAO.create(new MonedaFiat(
		        fiat.getNombre(), 
		        fiat.getNomenclatura(), 
		        fiat.getCotizacion(), 
		        fiat.getRutaIcono()));
		}
		
        }catch (RequestException e) {
        	System.out.println(e.getMessage());
        }
		
		
	}
	
	
	public UsuarioDAO getUsuarioDao() {
		return usuarioDAO;
	}
	public ActivoCriptoDAO getActivoCriptoDao() {
		return activoCriptoDAO;
	}
	
	public ActivoFiatDAO getActivoFiatDao() {
		return activoFiatDAO;
	}
	
	public PersonaDAO getPersonaDao() {
		return personaDAO;
	}
	
	public StockDAO getStockDao() {
		return stockDAO;
	}
	
	public TransaccionDAO getTransaccionDao() {
		return transaccionDAO;
	}


	public ActivoCriptoDAO getActivoCriptoDAO() {
		return activoCriptoDAO;
	}




	public ActivoFiatDAO getActivoFiatDAO() {
		return activoFiatDAO;
	}




	public PersonaDAO getPersonaDAO() {
		return personaDAO;
	}




	public StockDAO getStockDAO() {
		return stockDAO;
	}




	public TransaccionDAO getTransaccionDAO() {
		return transaccionDAO;
	}


	public void setTransaccionDAO(TransaccionDAO transaccionDAO) {
		this.transaccionDAO = transaccionDAO;
	}


	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}




	public CriptoDAO getCriptoDAO() {
		return criptoDAO;
	}



	public FiatDAO getFiatDAO() {
		return fiatDAO;
	}


	
	
	
	
	
	
	
	
	
	


}
