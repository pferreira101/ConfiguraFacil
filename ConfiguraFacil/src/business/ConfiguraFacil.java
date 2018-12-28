package business;

import data.ComponenteDAO;
import data.ClienteDAO;
import data.FuncionarioDAO;
import data.EncomendaDAO;
import data.PacoteDAO;
import business.gFabrica.*;
import business.gConta.*;
import business.gConfig.*;

import java.sql.SQLException;
import java.util.*;

public class ConfiguraFacil {
	public Map<Integer, Cliente> clientes;
    public Map<Integer, Componente> componentes;
	public Map<Integer, Funcionario> funcionarios;
	public Map<Integer, Encomenda> encomendas;
	public Fabrica fabrica;
	public ComponenteDAO componenteDAO;
	public ClienteDAO clienteDAO;
	public FuncionarioDAO funcionarioDAO;
	//public EncomendaDAO encomendaDAO;
	public PacoteDAO pacoteDAO;


	public ConfiguraFacil(){
	    this.clientes = new HashMap<>();
	    this.funcionarios = new HashMap<>();
	    this.clienteDAO = new ClienteDAO();
	    this.funcionarioDAO = new FuncionarioDAO();
	    this.componenteDAO = new ComponenteDAO();
	    this.fabrica = new Fabrica();
	    this.pacoteDAO = new PacoteDAO();
        //this.encomendaDAO = new EncomendaDAO();
    }



    public int logIn(int id, String password) {
	    if(id == 0 && password.equals("admin")) return 3;

	    int r = -1;

	    try{
	        Funcionario f = this.funcionarioDAO.get(id);
            r = f.authenticate(password);
        }
        catch (Exception e){
	        r = -1;
        }

        return r;
    }

    public void registaCliente(String nome, int telemovel, String email) throws SQLException, ClassNotFoundException {
	    int id = getNextClienteID();

	    Cliente c = new Cliente(id, nome, telemovel, email);

	    this.clienteDAO.put(id, c);
    }

    public void registaFuncionario(Funcionario f) throws SQLException, ClassNotFoundException {
        this.funcionarioDAO.put(f.getID(), f);
    }

    public List<Componente> getComponentes() throws Exception {
        return this.componenteDAO.list();
    }

    public int getNextClienteID() throws SQLException {
        return this.clienteDAO.size() + 1;
    }

    public List<Cliente> getClientes() throws Exception {
        return this.clienteDAO.list();
    }

    public void removeFuncionario(int id) throws SQLException {
        this.funcionarioDAO.remove(id);
    }

    public Cliente getCliente(int id) throws Exception {
        return this.clienteDAO.get(id);
    }

    public List<Funcionario> getFuncionarios() throws Exception {
        return this.funcionarioDAO.list();
    }

    public Funcionario getFuncionario(int id) throws Exception {
        return this.funcionarioDAO.get(id);
    }

    public int getNextFuncionarioID() throws SQLException {
        return this.funcionarioDAO.size() + 1;
    }

    /**
     * Método para registar um cliente no sistema.
     * @param nome Nome do cliente
     * @param tel Número de telefone do cliente.
     * @param mail Email do cliente
     */

    /*public void registaCliente(String nome, int tel, String mail){
	    int id = this.clientes.size() + 1;
	    Cliente c = new Cliente(id, nome, tel, mail);
	    this.clientes.put(id,c);
    }*/


    /**
     * Método para atualizar a informação relativa a um funcionário.
     * @param id Id do funcionário a atualizar.
     * @param nome Novo nome do funcionário.
     * @param password Nova password do funcionário.
     * @param tipo Novo tipo de funcionário.
     * @param telemovel Novo número de telemóvel do funcionário.
     * @param email Novo email do funcionário.
     */


    public void alteraFuncionario(int id, String nome, String password, int tipo, int telemovel, String email) throws SQLException, ClassNotFoundException {
        /* // FIXME: 12/28/2018 versão normal
	    Funcionario f = this.funcionarios.get(id);


	    f.setALL(nome, password, tipo, telemovel, email);*/

	    // com dao isto vai ter que mudar e o DSI tb!!!1
        Funcionario f = new Funcionario(id, nome, password, tipo, telemovel, email);
        this.funcionarioDAO.put(id, f);
    }

    /**
     * Método para verificar se um dado funcionário existe no sistema.
     * @param id Id do funcionário a verificar.
     * @return
     */


    public boolean existeFuncionario(int id){
	    return this.funcionarios.containsKey(id);
    }

    /**
     * Método para verificar se um dado cliente existe no sistema.
     * @param codClient Id do cliente a verificar.
     * @return Cliente pretendido caso existe.
     */


    public Cliente existeCliente(int codClient){
        return this.clientes.get(codClient);
    }

    /**
     * Método do facade para atualizar os campos de um cliente.
     * @param id
     * @param nome
     * @param tel
     * @param mail
     */

    public void alteraCliente(int id,String nome,int tel,String mail){
	    Cliente c = this.clientes.get(id);
	    //c.setALL(nome, tel, mail);

	    //isto com daos vai mudar e o DSI tb !!!!
    }

    /**
     * Método para dado um orçamento e uma prioridade calcular a configuração ótima correspondente.
     * @param orcamento Valor do orçamento dado.
     * @param prio Prioridade das escolhas.
     * @return
     */

    public Configuracao calculaConfig(double orcamento,int prio){
         List<Componente> sgd = new ArrayList<>();
         List<Componente> prim = new ArrayList<>();

         for(Componente c : componentes.values()){
             if (c.temComplementares())
                 prim.add(c);
             else sgd.add(c);
         }

         Collections.sort(prim,new SortRazao()); // ordPrecoAcum
         Collections.sort(sgd,new SortBaixo()); // ordPreco

         double sum = 0;
         double sum2 = 0;

         List<Componente> list;
         boolean todos,valid1,rep,valid;

         Configuracao config = new Configuracao();

         for(Componente c : prim){
             if (sum >= orcamento)
                 break;
             list = c.getComplementares();
             valid1 = config.compativel(c);
             todos = true;

             for(Componente i : list){
                 if (sum < orcamento && valid1){
                     sum2 = i.getPreco();
                     valid = config.compativel(i);
                     rep = config.incluido(i);
                 }
                 else break;

                 if (valid && (sum + sum2 < orcamento) && !rep){
                     config.addComponente(i);
                     sum += sum2;
                 }
                 else if (valid == false || (sum+sum2 > orcamento))
                     todos = false;
             }

             sum2 = c.getPreco();
             if (todos && (sum2 + sum < orcamento) && valid1){
                 config.addComponente(c);
             }

         }
        for(Componente c : sgd){
             valid = config.compativel(c);
             sum2 = c.getPreco();
             rep = config.incluido(c);
             if (valid && sum+sum2 < orcamento && !rep){
                 sum += sum2;
                 config.addComponente(c);
             }
        }

         return config;
    }

    /**
     * Método para devolver todas as encomendas até ao momento.
     * @return Coleção com todas as encomendas.
     */


    public List<Encomenda> getEncomendas() throws Exception {
       //return this.encomendas.values();
        return this.fabrica.getEncomendas();
    }

    /**
     * Método para devolver uma dada encomenda.
     * @param cod Identificador da encomenda.
     * @return Encomenda pretendida.
     */

    public Encomenda getEncomenda(int cod) throws Exception {
        //return  this.encomendas.get(cod);
        return this.fabrica.getEncomenda(cod);
    }


    /**
     * Método para devolver um lista com o stock atual das componentes.
     * @return List com o stock das componentes.
     */

    public List<Stock> getStockList(){
        return this.fabrica.getStockList();
    }

    /**
     * Método para verificar uma dada componente já se encontra no sistema.
     * @param cod Chave do componente a procurar.
     * @return Boolean que representa a existência de um elemento no sistema.
     */

    public boolean existeStock(int cod){
        return this.fabrica.existeStock(cod);
    }

    /**
     * Método para adicionar um nova componente ao sistema
     * @param nome  Designação da nova componente.
     * @param preco Preço da nova componente.
     * @param tipo Tipo da nova componente.
     * @param comp Lista com as componentes complementares.
     * @param incomp Lista com as componente incompatíveis.
     */

    public void adicionarComponente(String nome,double preco,int tipo,List<Componente> comp,List<Componente> incomp){
        int id = this.componentes.size();
        Componente c = new Componente(id,nome,preco,tipo,comp,incomp);

        this.componentes.put(id,c);

        this.fabrica.adicionarStockNovo(c);
    }

    /**
     * Método para encomendar e atualizar stock de uma componente.
     * @param idcomp Id da componente a atualizar.
     * @param quantidade Quantidade nova a introduzir.
     */

    public void encomendar(int idcomp,int quantidade) throws SQLException, ClassNotFoundException {
        this.fabrica.atualizarStock(idcomp, quantidade);
    }

    /**
     * Método que devolve uma lista com as encomendas que estão na fábrica.
     * @return Lista com encomendas.
     */

    public List<Encomenda> getEncomendasQueue(){
        return this.fabrica.getQueue();
    }

    /**
     * Método que, dada a posição de uma encomenda na queue, calcula que componentes dessa encomenda não se encontram em stock.
     * @param i Posição da encomenda
     * @return Lista com as componentes em falta.
     */

    public List<Componente> checkStock(int i){
        Encomenda e = this.fabrica.getEncomendaQueue(i);

        return this.fabrica.stockEmFalta(e.getComponentes());
    }

    /**
     * Método para despachar uma dada encomenda.
     * @param i Posição da encomenda na queue.
     */

    public void processaEncomenda(int i){
        this.fabrica.processaEncomenda(i);
    }


    /**
     * Método para registar uma encomenda no sistema.
     * @param cliente Cliente que originou a encomenda.
     * @param config Configuração da encomenda.
     * @param funcionario Funcionário que realizou a encomenda.
     */

    public void registaEncomenda(Configuracao config, int cliente, int funcionario) throws SQLException, ClassNotFoundException {
        /*int id = this.encomendas.size() + 1;

        Encomenda e = new Encomenda(id, cliente, funcionario, config);
        this.encomendas.put(id,e);
        this.fabrica.adicionarEncomenda(e);*/ // FIXME: 12/28/2018 normal

        this.fabrica.registaEncomenda(config, cliente, funcionario);
    }

    /**
     * Método para registar um funcionário no sistema.
     * @param nome Nome do funcionário a inserir.
     * @param pass Password de acesso do funcionário
     * @param tel Número de telefone.
     * @param email Email do funcionário.
     * @param tipo Permissões.
     */

    public void registaFuncionario(String nome, String pass, int tel, String email, int tipo){
        int id = this.funcionarios.size() + 1;

        Funcionario f = new Funcionario(id, nome, pass, tipo, tel, email);

        this.funcionarios.put(id, f);

    }

    public void atualizarStock(int id_comp, int quantidade) throws SQLException, ClassNotFoundException {
        this.fabrica.atualizarStock(id_comp, quantidade);
    }

    /**
     * Método para verificar se um componente é compatível com uma Configuracao
     * @param config Configuracao onde se verifica se o componente tem incompatibilidades
     * @param comp Componente a testar
     * @return Lista com os componentes da configuração que são incompatíveis com o componente argumento
     */
    public List<Componente> checkIncompativeis(Configuracao config, Componente comp){
        return config.incompativeis(comp);
    }

    /**
     * Método para verificar se um dado componente obriga à instalação de outros
     * @param comp Componente a verificar
     * @return Lista dos componentes complementares ao componente argumento
     */
    public List<Componente> checkComplementares(Componente comp){
        return comp.getComplementares();
    }


    /**
     * Método para adicionar componente a uma configuração
     * @param config Configuração em que se adiciona componente
     * @param comp Componente a adicionar
     */
    public void addComponente(Configuracao config, Componente comp){
        config.addComponente(comp);
    }

    /**
     * Método para adicionar vários componentes a uma configuração
     * @param config Configuração em que se adiciona componente
     * @param comps Lista de componentes a adicionar
     */
    public void addComponentes(Configuracao config, List<Componente> comps){
        for(Componente c : comps)
            config.addComponente(c);
    }

    /**
     * Método para remover componentes de uma configuração
     * @param config Configuração de onde vão ser retirados os componentes
     * @param comps Componentes a eliminar da configuração
     */
    public void removeComponentes(Configuracao config, List<Componente> comps){
        config.rmComponentes(comps);
    }

    //
    public List<Pacote> getPacotes() throws Exception {
        return this.pacoteDAO.list();
    }

    /**
     * Método para verificar se um Pack é compatível com uma configuração
     * @param config Configuracao a testar
     * @param pack Pacote a testar
     * @return Lista dos componentes da configuração que sejam incompatíveis com alguma componente do pack
     */
    public List<Componente> checkIncompativeis(Configuracao config, Pacote pack){
           return config.incompativeis(pack);
    }

    /**
     * Método para verifcar se instação de um pacote obriga à instalação de outros componentes
     * @param pack Pacote a testar
     * @return Lista dos componentes necessários para se poder instalar o pacote
     */
    public List<Componente> checkComplementares(Pacote pack){
        return pack.getComplementares();
    }
}

