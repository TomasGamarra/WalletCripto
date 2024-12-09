package Controlador;

import Sistema.Criptomoneda;
import Sistema.MonedaFiat;
import Sistema.ServicioCotizaciones;
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
		//Cargar Criptos y Fiat
		criptoDAO.create(new Criptomoneda("Bitcoin","BTC",ServicioCotizaciones.obtenerPrecio("bitcoin"),1,"/images/Bitcoin.png"));
		criptoDAO.create(new Criptomoneda("Ethereum","ETH",ServicioCotizaciones.obtenerPrecio("ethereum"),1,"/images/Ethereum.png"));
		criptoDAO.create(new Criptomoneda("Dogecoin","DOGE",ServicioCotizaciones.obtenerPrecio("dogecoin"),1,"/images/Dogecoin.png"));
		criptoDAO.create(new Criptomoneda("Usdc","USDC",ServicioCotizaciones.obtenerPrecio("usd-coin"),1,"/images/Usdc.png"));
		criptoDAO.create(new Criptomoneda("Tether","USDT",ServicioCotizaciones.obtenerPrecio("tether"),1,"/images/Tether.png"));
		
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
	
	
	
	
	
	
	
	
	
	


}
