package business;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Componente {
	private int id;
	private String designacao;
	private double preco;
	private int tipo;
	public List<Componente> complementares;
	public List<Componente> incompativeis;

	public Componente(){
	    this.id = -1;
	    this.designacao = "";
	    this.preco = -1;
	    this.tipo = -1;
	    this.complementares = new ArrayList<>();
	    this.incompativeis = new ArrayList<>();
    }


    public int getID() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getDesignacao() {
        return this.designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public double getPreco() {
        return this.preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getTipo() {
        return this.tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public List<Componente> getComplementares() {
        return this.complementares;
    }

    public void setComplementares(List<Componente> complementares) {
        this.complementares = complementares;
    }

    public List<Componente> getIncompativeis() {
        return this.incompativeis;
    }

    public void setIncompativeis(List<Componente> incompativeis) {
        this.incompativeis = incompativeis;
    }
}