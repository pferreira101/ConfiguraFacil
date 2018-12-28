package business.gFabrica;

import java.util.*;

import business.gConfig.Componente;
import data.StockDAO;

public class Fabrica {
	private Map<Integer,Stock> stocks;
	private List<Encomenda> queue;

    /**
     * Construtor parameterizado para a classe da Fábrica.
     * @param stocks
     * @param queue
     */

    public Fabrica(Map<Integer, Stock> stocks, List<Encomenda> queue) {
        this.stocks = stocks;
        this.queue = queue;
    }

    /**
     * Contrutor sem parametros da classe Fábrica.
     */

    public Fabrica(){
        this.stocks = new HashMap<>();
        this.queue = new ArrayList<>();
    }

    /**
     * Método get para o map de Int-Stock.
     * @return Map
     */

    public Map<Integer, Stock> getStocks() {
		return stocks;
	}

    /**
     * Método set para o map de Int-Stock.
     * @param stocks
     */

	public void setStocks(Map<Integer, Stock> stocks) {
		this.stocks = new HashMap<>();
        for(Stock s : stocks.values())
            this.stocks.put(s.getId(),s);
	}

    /**
     * Método get para aceder à queue de encomendas.
     * @return Lista com a queue de encomendas.
     */

	public List<Encomenda> getQueue() {
	    List<Encomenda> aux = new ArrayList<>();
	    aux.addAll(queue);
		return queue;
	}

    /**
     * Método set para alterar a lista de encomendas na fábrica
     * @param queue Lista com a novo queue.
     */


    public void setQueue(List<Encomenda> queue) {
        this.queue = new ArrayList<>();
        for(Encomenda e : queue)
		    this.queue.add(e);
	}

    /**
     * Método para obter uma lista com o stock atual de cada componente.
     * @return Lista com os stocks componentes.
     */

	public List<Stock> getStockList(){
        List<Stock> aux = new ArrayList<>();

        for(Stock s : this.stocks.values())
            aux.add(s);

        return aux;
    }

    /**
     * Método para verificar uma dada componente já se encontra no sistema.
     * @param cod Chave do componente a procurar.
     * @return Boolean que representa a existência de um elemento no sistema.
     */

    public boolean existeStock(int cod){
	    return this.stocks.containsKey(cod);
    }

    /**
     * Método para adicionar o stock de uma nova componente.
     * @param c Compomente nova a adicionar
     */

    public void adicionarStockNovo(Componente c){
        Stock s = new Stock(c.getID(),0,c);
        this.stocks.put(c.getID(),s);
    }

    /**
     * Método para atualizar o stock de uma dada componente.
     * @param idcomp Id da componente a atualizar.
     * @param quant Quantidade nova a adicionar.
     */

    public void atualizarStock(int idcomp,int quant){
        Stock st = this.stocks.get(idcomp);
        st.add(quant);
    }
}