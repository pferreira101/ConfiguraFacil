package business.gFabrica;

import business.gConfig.Configuracao;

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

    public void setConfig(Configuracao config) {
        this.config = config;
    }
}