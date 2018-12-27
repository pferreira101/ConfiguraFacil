package business.gFabrica;

import business.gConfig.Componente;

public class Stock {
	private int id;
	private int quantidade;
	private Componente componente;

	public Stock() {
		this.id = -1;
		this.quantidade = -1;
		this.componente = null;
	}

	public Stock(int id, int quantidade, Componente componente) {

		this.id = id;
		this.quantidade = quantidade;
		this.componente = componente;
	}

	public int getId() {

		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Componente getComponente() {
		return componente;
	}

	public void setComponente(Componente componente) {
		this.componente = componente;
	}

	public void add(int quantidade){
		this.quantidade += quantidade;
	}
}