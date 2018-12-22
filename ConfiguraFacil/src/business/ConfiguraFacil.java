package business;

import data.ComponenteDAO;
import data.ClienteDAO;
import data.FuncionarioDAO;
import data.EncomendaDAO;
import data.PacoteDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfiguraFacil {
	public Componente _componentes;
	public Pacote _pacotes;
	public Cliente _clientes;
	public Map<Integer, Funcionario> funcionarios;
	public Encomenda _encomendas;
	public ComponenteDAO _unnamed_ComponenteDAO_;
	public ClienteDAO _unnamed_ClienteDAO_;
	public FuncionarioDAO funcionarioDAO;
	public EncomendaDAO _unnamed_EncomendaDAO_;
	public PacoteDAO _unnamed_PacoteDAO_;


	public ConfiguraFacil(){
	    this.funcionarios = new HashMap<>();
	    this.funcionarioDAO = new FuncionarioDAO();
    }


    public void loadFuncionarios() {
        try{
            List<Funcionario> l = this.funcionarioDAO.list();

            for(Funcionario f : l){
                System.out.println(f.getID()); // FIXME: 12/22/2018 DEBUGGING
                this.funcionarios.put(f.getID(), f);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int logIn(int id, String password){
	    Funcionario f = this.funcionarios.get(id);
	    if(f != null) {
            System.out.println("TIPO APOS LOGIN :" + f.getTipo()); // FIXME: 12/22/2018 DEBUGGING
	        return f.getTipo();
        }
	    else return 0;
    }
}