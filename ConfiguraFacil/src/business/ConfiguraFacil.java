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
    public Fabrica fabrica;
    public ComponenteDAO componenteDAO;
    public ClienteDAO clienteDAO;
    public FuncionarioDAO funcionarioDAO;
    public PacoteDAO pacoteDAO;


    public ConfiguraFacil() {
        this.clienteDAO = new ClienteDAO();
        this.funcionarioDAO = new FuncionarioDAO();
        this.componenteDAO = new ComponenteDAO();
        this.fabrica = new Fabrica();
        this.pacoteDAO = new PacoteDAO();
    }

    /**
     * Método para fazer log in na aplicação.
     * @param id Identificador do utilizador.
     * @param password Password corresponde.
     * @return Permissões dadas
     */


    public int logIn(int id, String password) {
        if (id == 0 && password.equals("admin")) return 3;

        int r = -1;

        try {
            Funcionario f = this.funcionarioDAO.get(id);
            r = f.authenticate(password);
        } catch (Exception e) {
            r = -1;
        }

        return r;
    }

    /**
     * Método para registar um cliente no sistema.
     * @param nome Nome do cliente.
     * @param telemovel Número de telemóvel do cliente.
     * @param email Email do cliente.
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    public void registaCliente(String nome, int telemovel, String email) throws SQLException, ClassNotFoundException {
        int id = this.clienteDAO.size() + 1;

        Cliente c = new Cliente(id, nome, telemovel, email);

        this.clienteDAO.put(id, c);
    }

    /**
     * Método para obter uma lista com todos os componentes que o sistema tem.
     * @return Lista com todos os componentes do sistema.
     * @throws Exception
     */


    public List<Componente> getComponentes() throws Exception {
        return this.componenteDAO.list();
    }

    /**
     * Método para obter uma lista com todos os cliente que o sistema tem.
     * @return Lista com todos os clientes do sistema.
     * @throws Exception
     */


    public List<Cliente> getClientes() throws Exception {
        return this.clienteDAO.list();
    }

    /**
     * Método para remover um funcionário.
     * @param id Identificador do funcionário.
     * @throws SQLException
     */

    public void removeFuncionario(int id) throws SQLException {
        this.funcionarioDAO.remove(id);
    }

    /**
     * Método para obter um dado cliente.
     * @param id Identificador do cliente.
     * @return Cliente pretendido.
     * @throws Exception
     */

    public Cliente getCliente(int id) throws Exception {
        return this.clienteDAO.get(id);
    }

    /**
     * Método para obter uma lista com todos os funcionários que o sistema tem.
     * @return Lista com todos os funcionários do sistema.
     * @throws Exception
     */


    public List<Funcionario> getFuncionarios() throws Exception {
        return this.funcionarioDAO.list();
    }

    /**
     * Método para obter um dado funcionário.
     * @param id Identificador do funcionário.
     * @return Cliente pretendido.
     * @throws Exception
     */

    public Funcionario getFuncionario(int id) throws Exception {
        return this.funcionarioDAO.get(id);
    }


    /**
     * Método para atualizar a informação relativa a um funcionário.
     *
     * @param id        Id do funcionário a atualizar.
     * @param nome      Novo nome do funcionário.
     * @param password  Nova password do funcionário.
     * @param tipo      Novo tipo de funcionário.
     * @param telemovel Novo número de telemóvel do funcionário.
     * @param email     Novo email do funcionário.
     */


    public void alteraFuncionario(int id, String nome, String password, int tipo, int telemovel, String email) throws SQLException, ClassNotFoundException {

        Funcionario f = new Funcionario(id, nome, password, tipo, telemovel, email);
        this.funcionarioDAO.put(id, f);
    }


    /**
     * Método do facade para atualizar os campos de um cliente.
     *
     * @param id
     * @param nome
     * @param tel
     * @param mail
     */
    public void alteraCliente(int id, String nome, int tel, String mail) throws SQLException, ClassNotFoundException {
        Cliente c = new Cliente(id, nome, tel, mail);
        this.clienteDAO.put(id, c);
    }

    /**
     * Método para dado um orçamento e uma prioridade calcular a configuração ótima correspondente.
     *
     * @param orcamento Valor do orçamento dado.
     * @return
     */

    public Configuracao calculaConfig(double orcamento) throws Exception {
        List<Componente> sgd = new ArrayList<>();
        List<Componente> prim = new ArrayList<>();

        for (Componente c : componenteDAO.list()) {
            if (c.temComplementares())
                prim.add(c);
            else sgd.add(c);
        }

        Collections.sort(prim, new SortRazao()); // ordPrecoAcum
        Collections.sort(sgd, new SortBaixo()); // ordPreco

        double sum = 0;
        double sum2 = 0;

        List<Componente> list;
        boolean todos, valid1, rep, valid;

        Configuracao config = new Configuracao();

        for (Componente c : prim) {
            if (sum >= orcamento)
                break;
            list = c.getComplementares();
            valid1 = config.compativel(c);
            todos = true;


            for (Componente i : list) {
                if (sum < orcamento && valid1) {
                    sum2 = i.getPreco();
                    valid = config.compativel(this.componenteDAO.get(i.getID()));
                    rep = config.incluido(i);
                } else break;

                if (valid && (sum + sum2 < orcamento) && !rep) {
                    config.addComponente(i);
                    sum += sum2;
                } else if (valid == false || (sum + sum2 > orcamento))
                    todos = false;
            }

            sum2 = c.getPreco();
            if (todos && (sum2 + sum < orcamento) && valid1) {
                config.addComponente(c);
            }

        }
        for (Componente c : sgd) {
            valid = config.compativel(c);
            sum2 = c.getPreco();
            rep = config.incluido(c);
            if (valid && sum + sum2 < orcamento && !rep) {
                sum += sum2;
                config.addComponente(c);
            }
        }

        return config;
    }

    /**
     * Método para devolver todas as encomendas até ao momento.
     *
     * @return Coleção com todas as encomendas.
     */


    public List<Encomenda> getEncomendas() throws Exception {
        return this.fabrica.getEncomendas();
    }

    /**
     * Método para devolver uma dada encomenda.
     *
     * @param cod Identificador da encomenda.
     * @return Encomenda pretendida.
     */

    public Encomenda getEncomenda(int cod) throws Exception {
        return this.fabrica.getEncomenda(cod);
    }


    /**
     * Método para devolver um Map com o stock atual das componentes.
     *
     * @return Map com o stock das componentes.
     */

    public Map<String,Stock> getStockList() {
        List<Stock> aux;
        try {
            aux = this.fabrica.getStockList();
        } catch (Exception e) {
            aux = new ArrayList<>();
        }

        Map<String, Stock> res = new HashMap<>();

        for(Stock k : aux){
            try{
                int id = k.getID();
                Componente c = this.componenteDAO.get(id);
                res.put(c.getDesignacao(), k);
            }
            catch (Exception e){}
        }
        return res;
    }

    /**
     * Método para verificar uma dada componente já se encontra no sistema.
     *
     * @param cod Chave do componente a procurar.
     * @return Boolean que representa a existência de um elemento no sistema.
     */

    public boolean existeStock(int cod) {
        return this.fabrica.existeStock(cod);
    }

    /**
     * Método para adicionar um nova componente ao sistema
     *
     * @param nome   Designação da nova componente.
     * @param preco  Preço da nova componente.
     * @param tipo   Tipo da nova componente.
     * @param comp   Lista com as componentes complementares.
     * @param incomp Lista com as componente incompatíveis.
     * @return id do novo componente
     */

    public int adicionarComponente(String nome, double preco, int tipo, List<Componente> comp, List<Componente> incomp) throws SQLException, ClassNotFoundException {
        int id = this.componenteDAO.size() + 1;
        Componente c = new Componente(id, nome, preco, tipo, comp, incomp);

        this.componenteDAO.put(id, c);
        this.fabrica.adicionarStockNovo(id);

        return id;
    }

    /**
     * Método para encomendar e atualizar stock de uma componente.
     *
     * @param idcomp     Id da componente a atualizar.
     * @param quantidade Quantidade nova a introduzir.
     */

    public void encomendar(int idcomp, int quantidade) throws SQLException, ClassNotFoundException, Exception {
        this.fabrica.atualizarStock(idcomp, quantidade);
    }


    /**
     * Método que, dada a posição de uma encomenda na queue, calcula que componentes dessa encomenda não se encontram em stock.
     *
     * @param i Posição da encomenda
     * @return Lista com as componentes em falta.
     */

    public List<Componente> checkStock(int i) {
        try {
            Encomenda e = this.fabrica.getEncomenda(i);
            return this.fabrica.stockEmFalta(e.getAllComponentes());
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * Método para despachar uma dada encomenda.
     *
     * @param i Id da encomenda na queue.
     */

    public void processaEncomenda(int i) throws Exception {
        this.fabrica.processaEncomenda(i);
    }


    /**
     * Método para registar uma encomenda no sistema.
     *
     * @param cliente     Cliente que originou a encomenda.
     * @param config      Configuração da encomenda.
     * @param funcionario Funcionário que realizou a encomenda.
     */

    public void registaEncomenda(Configuracao config, int cliente, int funcionario) throws SQLException, ClassNotFoundException {
        this.fabrica.registaEncomenda(config, cliente, funcionario);
    }

    /**
     * Método para registar um funcionário no sistema.
     *
     * @param nome  Nome do funcionário a inserir.
     * @param pass  Password de acesso do funcionário
     * @param tel   Número de telefone.
     * @param email Email do funcionário.
     * @param tipo  Permissões.
     */

    public void registaFuncionario(String nome, String pass, int tel, String email, int tipo) throws SQLException, ClassNotFoundException {
        int id = this.funcionarioDAO.size() + 1;

        Funcionario f = new Funcionario(id, nome, pass, tipo, tel, email);


        this.funcionarioDAO.put(id, f);

    }



    /**
     * Método para verificar se um componente é compatível com uma Configuracao
     *
     * @param config Configuracao onde se verifica se o componente tem incompatibilidades
     * @param comp   Componente a testar
     * @return Lista com os componentes da configuração que são incompatíveis com o componente argumento
     */
    public List<Componente> checkIncompativeis(Configuracao config, Componente comp) {
        return config.incompativeis(comp);
    }

    /**
     * Método para verificar se um dado componente obriga à instalação de outros
     *
     *
     * @param config
     * @param comp Componente a verificar
     * @return Lista dos componentes complementares ao componente argumento
     */
    public List<Componente> checkComplementares(Configuracao config, Componente comp) {
        return config.complementares(comp);
    }


    /**
     * Método para adicionar componente a uma configuração
     *
     * @param config Configuração em que se adiciona componente
     * @param comp   Componente a adicionar
     */
    public void addComponente(Configuracao config, Componente comp) {
        config.addComponente(comp);
    }

    /**
     * Método para adicionar vários componentes a uma configuração
     *
     * @param config Configuração em que se adiciona componente
     * @param comps  Lista de componentes a adicionar
     */
    public void addComponentes(Configuracao config, List<Componente> comps) {
            config.addComponentes(comps);
    }

    /**
     * Método para remover componentes de uma configuração
     *
     * @param config Configuração de onde vão ser retirados os componentes
     * @param comps  Componentes a eliminar da configuração
     */
    public void removeComponentes(Configuracao config, List<Componente> comps) {
        config.rmComponentes(comps);
    }

    //
    public List<Pacote> getPacotes() throws Exception {
        return this.pacoteDAO.list();
    }

    /**
     * Método para verificar se um Pack é compatível com uma configuração
     *
     * @param config Configuracao a testar
     * @param pack   Pacote a testar
     * @return Lista dos componentes da configuração que sejam incompatíveis com alguma componente do pack
     */
    public List<Componente> checkIncompativeis(Configuracao config, Pacote pack) {
        return config.incompativeis(pack);
    }

    /**
     * Método para verifcar se instação de um pacote obriga à instalação de outros componentes
     *
     * @param pack Pacote a testar
     * @return Lista dos componentes necessários para se poder instalar o pacote
     */
    public List<Componente> checkComplementares(Configuracao config, Pacote pack) {
        return config.complementares(pack);
    }


    /**
     * Método para adicionar um pacote a uma configuração, adicionando apenas os componentes do pacote cuja instalação seja possível
     * @param config Configuração na qual se adiciona o pacote
     * @param pack Pacote a adicionar
     */
    public void updateConfig(Configuracao config, Pacote pack) {
        config.addPacote(pack);
    }


    /**
     * Método para remover uma componente duma configuração
     * @param config Configuração para remover componente
     * @param componente Componente a remover
     */
    public void removeComponente(Configuracao config, Componente componente) {
        config.rmComponente(componente);
    }


    /**
     * Método que verifica a existência de componentes selecionadas que formam um pacote
     * @param config Configuração a verificar
     * @param pacotes Lista de todos os pacotes existentes
     */
    public void componentesToPacote(Configuracao config, List<Pacote> pacotes){
        config.componentesToPacote(pacotes);
    }

    /**
     * Método para ir buscar um componente especifico.
     * @param id Id do componente a ir buscar.
     * @return Componente procurado.
     */

    public Componente getComponente(int id){
        Componente c;
        try{
            c =  this.componenteDAO.get(id);
        }
        catch (Exception e){
            c = null;
        }
        return c;
    }
}