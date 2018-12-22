package business;

public class Funcionario {
	private int id;
	private String nome;
	private String password;
	private int telemovel;
	private String email;
	private int tipo;

	public int getID(){
	    return this.id;
    }

	public String getNome(){
	    return this.nome;
	}

    public String getPassword() {
        return this.password;
    }

    public int getTelemovel() {
        return this.telemovel;
    }

    public String getEmail() {
        return this.email;
    }

    public int getTipo(){
        return this.tipo;
    }

    public void setID(int id){
	    this.id = id;
    }

    public void setNome(String n){
        this.nome = n;
    }

    public void setPassword(String p) {
        this.password = p;
    }

    public void setTelemovel(int t){
        this.telemovel = t;
    }

    public void setEmail(String e){
        this.email = e;
    }

    public void setTipo(int t){
        this.tipo = t;
    }
}