package business.gFabrica;

import business.gConfig.Componente;
import business.gConfig.Configuracao;

import java.util.ArrayList;
import java.util.List;

public class Encomenda {

    private int id;
    private int cliente;
    private int funcionario;
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

    public List<Componente> getComponentes(){
        return this.config.getComponentes();
    }

    public void setConfig(Configuracao config) {
        this.config = config;
    }

    public Encomenda(){
        this.id = -1;
        this.cliente = -1;
        this.funcionario = -1;
        this.config = new Configuracao();
    }

    public Encomenda(int id, int cliente, int funcionario, Configuracao config) {
        this.id = id;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.config = config;
    }
}