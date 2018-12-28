package business.gFabrica;

import business.gConfig.Componente;
import business.gConfig.Configuracao;
import business.gConfig.Pacote;

import java.util.ArrayList;
import java.util.List;

public class Encomenda {

    private int id;
    private int cliente;
    private int funcionario;
    private boolean status;
    private Configuracao config;


    public int getID() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getCliente() {
        return this.cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public int getFuncionario() {
        return this.funcionario;
    }

    public void setFuncionario(int funcionario) {
        this.funcionario = funcionario;
    }

    public Configuracao getConfig() {
        return this.config;
    }

    public boolean getStatus(){
        return this.status;
    }

    public void setStatus(boolean status){
        this.status = status;
    }

    public List<Componente> getComponentes(){
        return this.config.getComponentes();

    }

    public List<Componente> getAllComponentes(){
        List<Componente> aux = new ArrayList<>(this.config.getComponentes());
        for(Pacote p : this.config.getPacotes()){
            aux.addAll(p.getComponentes());
        }

        return aux;
    }

    public void setConfig(Configuracao config) {
        this.config = config;
    }

    public Encomenda(){
        this.id = -1;
        this.cliente = -1;
        this.funcionario = -1;
        this.status = false;
        this.config = new Configuracao();
    }

    public Encomenda(int id, int cliente, int funcionario, Configuracao config) {
        this.id = id;
        this.cliente = cliente;
        this.status = false;
        this.funcionario = funcionario;
        this.config = config;
    }
}