package business.gFabrica;

import java.util.Vector;

import data.StockDAO;

public class Fabrica {
	public Vector<Encomenda> _queue = new Vector<Encomenda>();
	public Stock _stocks;
	public StockDAO _unnamed_StockDAO_;
}