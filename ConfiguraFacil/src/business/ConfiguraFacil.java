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
	public Encomenda _encomendas;
	public ComponenteDAO componenteDAO;
	public ClienteDAO clienteDAO;
	public FuncionarioDAO funcionarioDAO;
	public EncomendaDAO _unnamed_EncomendaDAO_;
	public PacoteDAO _unnamed_PacoteDAO_;


	public ConfiguraFacil(){
	    this.clientes = new HashMap<>();
	    this.funcionarios = new HashMap<>();
	    this.clienteDAO = new ClienteDAO();
	    this.funcionarioDAO = new FuncionarioDAO();
	    this.componenteDAO = new ComponenteDAO();
    }


    public void loadClientes(){
        try{
            List<Cliente> l = this.clienteDAO.list();

            for(Cliente c : l){
                this.clientes.put(c.getID(), c);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void loadFuncionarios() {
        try{
            List<Funcionario> l = this.funcionarioDAO.list();

            for(Funcionario f : l){
                this.funcionarios.put(f.getID(), f);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int logIn(int id, String password){
	    if(id == 0 && password.equals("admin")) return 3;

	    Funcionario f = this.funcionarios.get(id);
	    if(f != null) {
	        return f.getTipo();
        }
	    else return 0;
    }

    public void registaCliente(Cliente c) throws SQLException, ClassNotFoundException {
	    this.clientes.put(c.getID(), c);
	    this.clienteDAO.put(c.getID(), c);
    }

    public void registaFuncionario(Funcionario f) throws SQLException, ClassNotFoundException {
	    this.funcionarios.put(f.getID(), f);
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

         return null;
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
}