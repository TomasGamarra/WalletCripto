package sistema;

import java.util.LinkedList;
import java.util.Map;

import enumerativos.CriptomonedaEnum;
import excepciones.RequestException;
import gestores.FactoryDAO;
import gestores.ServicioCotizaciones;
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
  
		map = ServicioCotizaciones.obtenerPrecios(lista);
					
		for (CriptomonedaEnum cripto : CriptomonedaEnum.values()) {
		    criptoDAO.create(new Criptomoneda(
		        cripto.getNombre(), 
		        cripto.getNomenclatura(), 
		        map.get(cripto.getClaveApi()), 
		        1, 
		        cripto.getRutaIcono()));
		    
		    stockDAO.create(criptoDAO.obtenerIdCripto(cripto.getNomenclatura()), Math.random() * 3 + 8);
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
	
	

	public ActivoCriptoDAO getActivoCriptoDao() {
		return activoCriptoDAO;
	}
	

	
	
	public StockDAO getStockDao() {
		return stockDAO;
	}
	
	public TransaccionDAO getTransaccionDao() {
		return transaccionDAO;
	}






	public ActivoFiatDAO getActivoFiatDao() {
		return activoFiatDAO;
	}




	public PersonaDAO getPersonaDAO() {
		return personaDAO;
	}




	public void setTransaccionDao(TransaccionDAO transaccionDAO) {
		this.transaccionDAO = transaccionDAO;
	}


	public UsuarioDAO getUsuarioDao() {
		return usuarioDAO;
	}




	public CriptoDAO getCriptoDao() {
		return criptoDAO;
	}



	public FiatDAO getFiatDao() {
		return fiatDAO;
	}


	public PersonaDAO getPersonaDao() {
		return personaDAO;
}
	
	
	
	
	
	
	
	
	


}
