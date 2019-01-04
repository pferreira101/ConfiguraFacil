package business.gFabrica;

import java.sql.SQLException;
import java.util.*;

import business.gConfig.Componente;
import business.gConfig.Configuracao;
import data.EncomendaDAO;
import data.StockDAO;

public class Fabrica {
	private StockDAO stockDAO;
	private EncomendaDAO encomendaDAO;

    /**
     * Contrutor sem parametros da classe Fábrica.
     */

    public Fabrica(){
        this.stockDAO = new StockDAO();
        this.encomendaDAO = new EncomendaDAO();

    }

    /**
     * Método para obter uma lista com o stock atual de cada componente.
     * @return Lista com os stocks componentes.
     */

    public List<Stock> getStockList() throws Exception{
        return this.stockDAO.list();
    }

    /**
     * Método para verificar uma dada componente já se encontra no sistema.
     * @param cod Chave do componente a procurar.
     * @return Boolean que representa a existência de um elemento no sistema.
     */


    public boolean existeStock(int cod){
	    return this.stockDAO.containsKey(cod);
    }

    /**
     * Método para adicionar o stock de uma nova componente.
     * @param id Id da componente nova a adicionar.
     */

    public void adicionarStockNovo(int id) throws ClassNotFoundException,SQLException{
        Stock s = new Stock(id,0);
        this.stockDAO.put(id, s);
    }

    /**
     * Método para atualizar o stock de uma dada componente.
     * @param id_comp Id da componente a atualizar.
     * @param quant Quantidade nova a adicionar.
     */

    public void atualizarStock(int id_comp, int quant) throws Exception{
        Stock st = this.stockDAO.get(id_comp);
        st.add(quant);
        this.stockDAO.put(id_comp,st);
    }


    /**
     * Método que dada uma lista de componentes verifica que componentes não estão em stock.
     * @param componentes Lista de componentes a verificar
     * @return Lista com os componentes em falta.
     */
    public List<Componente> stockEmFalta(List<Componente> componentes) throws Exception{
        List<Componente> listfalta = new ArrayList<>();
        int id,k;
        Stock s;

        for(Componente c: componentes){
            id = c.getID();
            s = this.stockDAO.get(id);

            k = s.getQuantidade();
            if (k <= 0)
                listfalta.add(c);
        }

        return listfalta;
    }

    /**
     * Método para despachar uma dada encomenda.
     * @param i Id da encomenda na queue.
     */

    public void processaEncomenda(int i) throws Exception{
        Encomenda e = this.encomendaDAO.get(i);
        e.setStatus(true);

        this.encomendaDAO.put(i,e);

        int id;
        Stock s;

        for(Componente c : e.getAllComponentes()) {
            id = c.getID();
            s = this.stockDAO.get(id);
            s.remove(1);
            this.stockDAO.put(id,s);
        }
    }


    /**
     * Método para obter as encomendas que ainda não foram processadas
     * @return
     * @throws Exception
     */


    public List<Encomenda> getEncomendas() throws Exception {
        List<Encomenda> encs = new ArrayList<>();
        for(Encomenda e : this.encomendaDAO.list()){
            if (e.getStatus() == false){
                encs.add(e);
            }
        }
        return encs;
    }

    /**
     * Método para obter uma encomenda.
     * @param id Id da encomenda pretendida.
     * @return Encomenda pretendida.
     * @throws Exception
     */

    public Encomenda getEncomenda(int id) throws Exception {
        return this.encomendaDAO.get(id);
    }

    /**
     * Método para criar uma nova encomenda no sistema.
     * @param config Configuração associada à encomenda.
     * @param cliente Cliente que fez a encomenda.
     * @param funcionario Funcionário que vendeu a configuração.
     * @throws SQLException
     * @throws ClassNotFoundException
     */


    public void registaEncomenda(Configuracao config, int cliente, int funcionario) throws SQLException, ClassNotFoundException {
        int id = this.encomendaDAO.size() + 1;

        Encomenda e = new Encomenda(id, cliente, funcionario, config);

        this.encomendaDAO.put(id, e);
    }
}