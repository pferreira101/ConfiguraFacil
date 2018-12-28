package business.gFabrica;

import java.sql.SQLException;
import java.util.*;

import business.gConfig.Componente;
import business.gConfig.Configuracao;
import data.EncomendaDAO;
import data.StockDAO;

public class Fabrica {
	private Map<Integer, Stock> stocks;
	private Map<Integer,Encomenda> encomendas;


    /**
     * Construtor parameterizado para a classe da Fábrica.
     * @param stocks
     * @param encomendas
     */

    public Fabrica(Map<Integer, Stock> stocks, Map<Integer,Encomenda> encomendas) {
        this.stocks = stocks;
        this.encomendas = encomendas;


    }

    /**
     * Contrutor sem parametros da classe Fábrica.
     */

    public Fabrica(){
        this.stocks = new HashMap<>();
        this.encomendas = new HashMap<>();

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

    public void atualizarStock(int id_comp, int quant) throws SQLException, ClassNotFoundException {
        if (this.stocks.get(id_comp) != null) {
            Stock st = this.stocks.get(id_comp);
            st.add(quant);
        }
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
        Encomenda e = this.encomendas.get(i);
        e.setStatus(true);
        int id;
        Stock s;

        for(Componente c : e.getComponentes()) {
            id = c.getID();
            s = this.stocks.get(id);
            s.remove(1);
        }
    }

    /**
     * Método para ir buscar uma determinada encomenda.
     * @param cod Id da encomenda a ir buscar.
     * @return
     */

    public Encomenda getEncomenda(int cod){
        return  this.encomendas.get(cod);
    }

    /**
     * Método para ir buscar todas as encomendas que não foram realizadas.
     * @return Lista com as encomendas por realizar.
     */

    public List<Encomenda> getEncomendas(){
        List<Encomenda> encs = new ArrayList<>();

        for (Encomenda e : this.encomendas.values()){
            if (e.getStatus() == false){
                encs.add(e);
            }
        }
        return  encs;
    }

    /**
     * Método para registar uma encomenda no sistema.
     * @param cliente Cliente que originou a encomenda.
     * @param config Configuração da encomenda.
     * @param funcionario Funcionário que realizou a encomenda.
     */

    public void registaEncomenda(Configuracao config,int cliente, int funcionario){
        int id = this.encomendas.size();
        Encomenda e = new Encomenda(id,cliente,funcionario,config);
        this.encomendas.put(id,e);
    }
}