package business;

import data.ComponenteDAO;
import data.ClienteDAO;
import data.FuncionarioDAO;
import data.EncomendaDAO;
import data.PacoteDAO;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfiguraFacil {
	public Componente _componentes;
	public Pacote _pacotes;
	public Map<Integer, Cliente> clientes;
	public Map<Integer, Funcionario> funcionarios;
	public Encomenda _encomendas;
	public ComponenteDAO _unnamed_ComponenteDAO_;
	public ClienteDAO clienteDAO;
	public FuncionarioDAO funcionarioDAO;
	public EncomendaDAO _unnamed_EncomendaDAO_;
	public PacoteDAO _unnamed_PacoteDAO_;


	public ConfiguraFacil(){
	    this.clientes = new HashMap<>();
	    this.funcionarios = new HashMap<>();
	    this.clienteDAO = new ClienteDAO();
	    this.funcionarioDAO = new FuncionarioDAO();
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
}