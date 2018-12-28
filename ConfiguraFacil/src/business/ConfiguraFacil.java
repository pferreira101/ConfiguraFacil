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
	public EncomendaDAO encomendaDAO;
	public PacoteDAO pacoteDAO;


	public ConfiguraFacil(){
	    this.clientes = new HashMap<>();
	    this.funcionarios = new HashMap<>();
	    this.clienteDAO = new ClienteDAO();
	    this.funcionarioDAO = new FuncionarioDAO();
	    this.componenteDAO = new ComponenteDAO();
    }



    public int logIn(int id, String password) {
	    if(id == 0 && password.equals("admin")) return 3;

	    try{
	        Funcionario f = this.funcionarioDAO.get(id);
            return f.authenticate(password);
        }
        catch (Exception e){
	        return -1;
        }

    }

    public void registaCliente(Cliente c) throws SQLException, ClassNotFoundException {
	    this.clienteDAO.put(c.getID(), c);
    }

    public void registaFuncionario(Funcionario f) throws SQLException, ClassNotFoundException {
	    this.funcionarioDAO.put(f.getID(), f);
    }

    /**
     * Método para atualizar a informação relativa a um funcionário.
     * @param id Id do funcionário a atualizar.
     * @param nome Novo nome do funcionário.
     * @param password Nova password do funcionário.
     * @param tipo Novo tipo de funcionário.
     * @param telemovel Novo número de telemóvel do funcionário.
     * @param email Novo email do funcionário.
     */


    public void alteraFuncionario(int id, String nome, String password, int tipo, int telemovel, String email){
	    Funcionario f = this.funcionarios.get(id);

	    f.setALL(nome,password,tipo,telemovel,email);

	    // com dao isto vai ter que mudar e o DSI tb!!!1
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
     * @param id Id do cliente a verificar.
     * @return
     */


    public boolean existeCliente(int id){
        return this.clientes.containsKey(id);
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
	    c.setALL(nome,tel,mail);

	    //isto com daos vai mudar e o DSI tb !!!!
    }

    /**
     * Método para dado um orçamento e uma prioridade calcular a configuração ótima correspondente.
     * @param orcamento Valor do orçamento dado.
     * @param prio Prioridade das escolhas.
     * @return
     */

    public Configuracao calculaConfigO(double orcamento,int prio){
         List<Componente> sgd = new ArrayList<>();
         List<Componente> prim = new ArrayList<>();

         for(Componente c : componentes.values()){
             if (c.temComplementares())
                 prim.add(c);
             else sgd.add(c);
         }

         Collections.sort(prim,new SortRazao());
         Collections.sort(prim,new SortBaixo());

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


    public Collection<Encomenda> getEncomendas(){
        return this.encomendas.values();
    }

    /**
     * Método para devolver uma dada encomenda.
     * @param cod Identificador da encomenda.
     * @return Encomenda pretendida.
     */

    public Encomenda getEncomenda(int cod){
        return  this.encomendas.get(cod);
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
     * @param incomp Lsita com as componente incompatíveis.
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

    public void encomendar(int idcomp,int quantidade){
        this.fabrica.atualizarStock(idcomp,quantidade);
    }

    public void registaEncomenda(Encomenda e) {

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
}