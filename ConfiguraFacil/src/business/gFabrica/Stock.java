package business.gFabrica;


public class Stock {
	private int id;
	private int quantidade;

    /**
     * Construtor sem parametros para a classe Stock.
     */

	public Stock() {
		this.id = -1;
		this.quantidade = -1;
	}

    /**
     * Construtor com parametros para a class Stock.
     * @param id Id da componente.
     * @param quantidade Quantidade atual da componente.
     */

	public Stock(int id, int quantidade) {

		this.id = id;
		this.quantidade = quantidade;
	}

    /**
     * Método get para o id do stock.
     * @return Valor do id do stock.
     */

	public int getID() {
		return id;
	}

    /**
     * Método set para o id do stock.
     * @param id Novo id para a componente.
     */

	public void setID(int id) {
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