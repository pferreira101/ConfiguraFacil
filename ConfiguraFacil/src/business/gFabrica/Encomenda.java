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


    /**
     * Método get para o id de uma encomenda.
     * @return
     */
    public int getID() {
        return this.id;
    }

    /**
     * Método set para o id de uma encomenda.
     * @param id
     */

    public void setID(int id) {
        this.id = id;
    }

    /**
     * Método get para o cliente que fez a encomenda.
     * @return Identificador do cliente.
     */

    public int getCliente() {
        return this.cliente;
    }

    /**
     * Método set para o cliente que fez a encomenda.
     * @param cliente Id do cliente.
     */

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    /**
     * Método get para o funcionário que fez a encomenda.
     * @return Identificador do funcionário.
     */

    public int getFuncionario() {
        return this.funcionario;
    }

    /**
     * Método set para o funcionário que fez a encomenda.
     * @param funcionario Id do funcionário.
     */

    public void setFuncionario(int funcionario) {
        this.funcionario = funcionario;
    }

    /**
     * Método get para a configuração presente na encomenda.
     * @return Configuração da encomenda.
     */

    public Configuracao getConfig() {
        return this.config;
    }

    /**
     * Método get para o estado da encomenda.
     * @return Valor do estado da encomenda.
     */

    public boolean getStatus(){
        return this.status;
    }

    /**
     * Método set para o estado da encomenda.
     * @param status
     */

    public void setStatus(boolean status){
        this.status = status;
    }

    /**
     * Método get para os componentes individuais de uma encomenda.
     * @return Lista com os componentes.
     */

    public List<Componente> getComponentes(){
        return this.config.getComponentes();

    }

    /**
     * Método get para todos os componentes de uma encomenda incluido os presentes nos pacotes.
     * @return Lista com os componentes.
     */

    public List<Componente> getAllComponentes(){
        List<Componente> aux = new ArrayList<>(this.config.getComponentes());
        for(Pacote p : this.config.getPacotes()){
            aux.addAll(p.getComponentes());
        }

        return aux;
    }

    /**
     * Método set para a configuração da encomenda.
     * @param config Configuração a inserir na encomenda.
     */

    public void setConfig(Configuracao config) {
        this.config = config;
    }



    /**
     * Construtor parameterizado de uma encomenda.
     * @param id Id da encomenda.
     * @param cliente Id do cliente da encomenda.
     * @param funcionario Id do funcionario da encomenda.
     * @param config Configuração da encomenda.
     */

    public Encomenda(int id, int cliente, int funcionario, Configuracao config) {
        this.id = id;
        this.cliente = cliente;
        this.status = false;
        this.funcionario = funcionario;
        this.config = config;
    }
}