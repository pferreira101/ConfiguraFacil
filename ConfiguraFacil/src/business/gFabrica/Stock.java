package business.gFabrica;

import business.gConfig.Componente;

public class Stock {
	private int id;
	private int quantidade;
	private Componente componente;

    /**
     * Construtor sem parametros para a classe Stock.
     */

	public Stock() {
		this.id = -1;
		this.quantidade = -1;
		this.componente = null;
	}

    /**
     * Construtor com parametros para a class Stock.
     * @param id Id da componente.
     * @param quantidade Quantidade atual da componente.
     * @param componente Componete
     */

	public Stock(int id, int quantidade, Componente componente) {

		this.id = id;
		this.quantidade = quantidade;
		this.componente = componente;
	}

    /**
     * Método get para o id do stock.
     * @return Valor do id do stock.
     */

	public int getId() {

		return id;
	}

    /**
     * Método set para o id do stock.
     * @param id Novo id para a componente.
     */

	public void setId(int id) {
		this.id = id;
	}

    /**
     * Método get para a quantidade atual da componente.
     * @return Quantidade atual da componente.
     */

	public int getQuantidade() {
		return quantidade;
	}

    /**
     * Método set para a quantidade nova da componente.
     * @param quantidade Quantidade atualizada.
     */

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

    /**
     * Método para aceder ao componente correspondente do stock.
     * @return Componente do stock.
     */

	public Componente getComponente() {
		return componente;
	}

    /**
     * Método para alterar a componente do stock.
     * @param componente
     */

	public void setComponente(Componente componente) {
		this.componente = componente;
	}

    /**
     * Método para adicionar quantidade ao stock.
     * @param quantidade Quantidade a adicionar.
     */

	public void add(int quantidade){
		this.quantidade += quantidade;
	}

    /**
     * Método para remover quantidade ao stock.
     * @param quantidade Quantidade a remover.
     */

	public void remove(int quantidade) {
		this.quantidade -= quantidade;
	}
}