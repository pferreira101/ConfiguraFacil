package business.gConta;

public class Funcionario {
	private int id;
	private String nome;
	private String password;
	private int telemovel;
	private String email;
	private int tipo;

    /**
     * Construtor sem parametros da class Funcionário
     */

	public Funcionario(){
        this.id = -2;
        this.nome = "";
        this.password = "";
        this.telemovel = -1;
        this.email = "";
        this.tipo = -1;
    }

    /**
     * Construtor com parametros da class Funcionário
     * @param id Id do funcionáro.
     * @param nome Nome do funcionário.
     * @param password Password do funcionário.
     * @param tipo Tipo do funcionário.
     * @param telemovel Número de telemóvel do funcionário.
     * @param email Email do funcionário.
     */

    public Funcionario(int id, String nome, String password, int tipo, int telemovel, String email) {
        this.id = id;
        this.nome = nome;
        this.password = password;
        this.telemovel = telemovel;
        this.email = email;
        this.tipo = tipo;
    }

    /**
     * Método get para o Id do funcionário.
     * @return Valor do id do funcionário.
     */

    public int getID(){
	    return this.id;
    }

    /**
     * Método get para o nome do funcionário.
     * @return Nome do funcionário.
     */

	public String getNome(){
	    return this.nome;
	}

    /**
     * Método get para a password do funcionário.
     * @return Password do funcionário.
     */


    public String getPassword() {
        return this.password;
    }

    /**
     * Método get para o número de telemóvel do funcionário.
     * @return Número de telemóvel do funcionário.
     */


    public int getTelemovel() {
        return this.telemovel;
    }

    /**
     * Método get para o email do funcionário.
     * @return Email do funcionário.
     */


    public String getEmail() {
        return this.email;
    }

    /**
     * Método get para o tipo do funcionário.
     * @return Tipo do funcionário.
     */


    public int getTipo(){
        return this.tipo;
    }

    /**
     * Método set para o id do funcionário.
     */


    public void setID(int id){
	    this.id = id;
    }

    /**
     * Método set para o nome do funcionário.
     */

    public void setNome(String n){
        this.nome = n;
    }

    /**
     * Método set para a password do funcionário.
     */

    public void setPassword(String p) {
        this.password = p;
    }

    /**
     * Método set para o número de telemóvel do funcionário.
     */

    public void setTelemovel(int t){
        this.telemovel = t;
    }

    /**
     * Método set para o email do funcionário.
     */

    public void setEmail(String e){
        this.email = e;
    }

    /**
     * Método set para o tipo de telemóvel do funcionário.
     */

    public void setTipo(int t){
        this.tipo = t;
    }


    /**
     * Método que dado a password calcula as permissões do funcionário na aplicação.
     * @param password Password introduzida.
     * @return Permissões atribuidas.
     */

    public int authenticate(String password) {
        int r = -1;

        if (this.password.equals(password)) {
            r = this.tipo;
        }

        return r;
    }


    /**
     * Método que verifica se dois funcionarios são iguais
     * @param o objeto a comparar
     * @return true se forem iguais
     */
    public boolean equals(Object o){
        if(this == o) return true;
        if(o.getClass() != this.getClass()) return false;

        Funcionario temp = (Funcionario)o;

        return this.id == temp.getID() &&
               this.nome.equals(temp.getNome()) &&
               this.password.equals(temp.getPassword()) &&
               this.tipo == temp.getTipo() &&
               this.telemovel == temp.getTelemovel() &&
               this.email.equals(temp.getEmail());
    }
}