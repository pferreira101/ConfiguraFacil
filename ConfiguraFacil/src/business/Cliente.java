package business;

public class Cliente {
	private int id;
	private String nome;
	private int telemovel;
	private String email;
	public Encomenda _unnamed_Encomenda_;

    public int getID(){
        return this.id;
    }

    public String getNome(){
        return this.nome;
    }


    public int getTelemovel() {
        return this.telemovel;
    }

    public String getEmail(){
        return this.email;
    }

    public void setID(int id){
        this.id = id;
    }

    public void setNome(String n){
        this.nome = n;
    }


    public void setTelemovel(int t){
        this.telemovel = t;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cliente(){
        this.id = -1;
        this.nome = "";
        this.telemovel = -1;
        this.email = "";
    }

    public Cliente(int id, String nome, int telemovel, String email){
        this.id = id;
        this.nome = nome;
        this.telemovel = telemovel;
        this.email = email;
    }


}