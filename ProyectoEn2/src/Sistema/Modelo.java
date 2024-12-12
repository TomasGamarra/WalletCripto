package Sistema;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
		
		String[] array = {"bitcoin", "ethereum", "dogecoin","usd-coin","tether"};
		
		//Todo lo de map es para solucionar error 409 por acceder muchas veces seguidas , perdon por ser tan hardcode
        LinkedList<String> lista = new LinkedList<>(Arrays.asList(array));
		Map <String,Float> map = ServicioCotizaciones.obtenerPrecios(lista);
		
		criptoDAO.create(new Criptomoneda("Bitcoin","BTC",map.get("bitcoin"),1,"/images/Bitcoin.png"));
		criptoDAO.create(new Criptomoneda("Ethereum","ETH",map.get("ethereum"),1,"images/Ethereum.png"));
		criptoDAO.create(new Criptomoneda("Dogecoin","DOGE",map.get("dogecoin"),1,"images/Dogecoin.png"));
		criptoDAO.create(new Criptomoneda("Usdc","USDC",map.get("usd-coin"),1,"images/Usdc.png"));
		criptoDAO.create(new Criptomoneda("Tether","USDT",map.get("tether"),1,"images/Tether.png"));
		
		fiatDAO.create(new MonedaFiat("Pesos Argentinos","ARS",1077,"/images/Ars.png"));
		fiatDAO.create(new MonedaFiat("Dolares","USD",1,"/images/Usd.png"));
		
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
