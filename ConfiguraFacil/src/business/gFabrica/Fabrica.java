package business.gFabrica;

import java.sql.SQLException;
import java.util.*;

import business.gConfig.Componente;
import business.gConfig.Configuracao;
import data.EncomendaDAO;
import data.StockDAO;

public class Fabrica {
	private Map<Integer, Stock> stocks;
	private List<Encomenda> queue;
	private StockDAO stockDAO;
	private EncomendaDAO encomendaDAO;

    /**
     * Construtor parameterizado para a classe da Fábrica.
     * @param stocks
     * @param queue
     */

    public Fabrica(Map<Integer, Stock> stocks, List<Encomenda> queue) {
        this.stocks = stocks;
        this.queue = queue;
        this.stockDAO = new StockDAO();
        this.encomendaDAO = new EncomendaDAO();

    }

    /**
     * Contrutor sem parametros da classe Fábrica.
     */

    public Fabrica(){
        this.stocks = new HashMap<>();
        this.queue = new ArrayList<>();
        this.stockDAO = new StockDAO();
        this.encomendaDAO = new EncomendaDAO();

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
            this.stocks.put(s.getID(),s);
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
	    return this.stocks.containsKey(cod) && this.stocks.get(cod).getQuantidade() > 0;
    }

    /**
     * Método para adicionar o stock de uma nova componente.
     * @param id Id da componente nova a adicionar.
     */

    public void adicionarStockNovo(int id){
        Stock s = new Stock(id,0);
        this.stocks.put(id, s);
    }

    /**
     * Método para atualizar o stock de uma dada componente.
     * @param id_comp Id da componente a atualizar.
     * @param quant Quantidade nova a adicionar.
     */

    public void atualizarStock(int id_comp, int quant){
        Stock st = this.stocks.get(id_comp);
        st.add(quant);

<<<<<<< HEAD
=======

>>>>>>> fd071cf622bf43baf0dbb9dcf127fc03a541eae9
        //throws SQLException, ClassNotFoundException
        //Stock st = this.stockDAO.get(id_comp);
        //            st.add(quant);
        //this.stockDAO.put(id_comp, st);
    }


    /**
     * Método que dada uma lista de componentes verifica que componentes não estão em stock.
     * @param componentes Lista de componentes a verificar
     * @return Lista com os componentes em falta.
     */
    public List<Componente> stockEmFalta(List<Componente> componentes){
        List<Componente> listfalta = new ArrayList<>();
        int id,k;
        Stock s;

        for(Componente c: componentes){
            id = c.getID();
            s = this.stocks.get(id);

            k = s.getQuantidade();
            if (k <= 0)
                listfalta.add(c);
        }

        return listfalta;
    }

    /**
     * Método para despachar uma dada encomenda.
     * @param i Posição da encomenda na queue.
     */

    public void processaEncomenda(int i){
        Encomenda e = this.queue.get(i);
        this.queue.remove(i);
        int id;
        Stock s;

        for(Componente c : e.getComponentes()) {
            id = c.getID();
            s = this.stocks.get(id);
            s.remove(1);
        }
    }

    /**
     * Método que adiciona uma encomenda à queue de encomendas.
     * @param e Encomenda a adicionar.
     */

    public void adicionarEncomenda(Encomenda e){
        this.queue.add(e);
    }


    public List<Encomenda> getEncomendas() throws Exception {
        return this.encomendaDAO.list();
    }

    public Encomenda getEncomenda(int id) throws Exception {
        return this.encomendaDAO.get(id);
    }


    public void registaEncomenda(Configuracao config, int cliente, int funcionario) throws SQLException, ClassNotFoundException {
        int id = this.getNextEncomendaID();

        Encomenda e = new Encomenda(id, cliente, funcionario, config);

        this.encomendaDAO.put(id, e);
    }

    private int getNextEncomendaID() throws SQLException {
        return this.encomendaDAO.size() + 1;
    }
}