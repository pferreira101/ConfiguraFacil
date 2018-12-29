package business.gConfig;

import java.util.*;

public class Componente {
	private int id;
	private String designacao;
	private double preco;
	private int tipo;
	private List<Componente> complementares;
	private List<Componente> incompativeis;


	/**
	 * Método get para o id da componente.
	 * @return Int com o id da componente.
	 */

	public int getID() {
		return id;
	}

	/**
	 * Método get para a designação da componente.
	 * @return Designação da componente.
	 */

	public String getDesignacao() {
		return designacao;
	}

	/**
	 * Método get para o preço da componente.
	 * @return Preço da componente.
	 */

	public double getPreco() {
		return preco;
	}

	/**
	 * Método get para o tipo da componente.
	 * @return String com o tipo da componente.
	 */

	public int getTipo() {
		return tipo;
	}

	/**
	 * Método get para a Lista de componentes complementares.
	 * @return Lista com as componentes.
	 */

	public List<Componente> getComplementares() {
		return complementares;
		//rever isto
	}

	/**
	 * Método get para a Lista de componentes incompativeis.
	 * @return Lista com as componentes.
	 */

	public List<Componente> getIncompativeis() {
		return incompativeis;
	}

	/**
	 * Método set para o valor do id da componente.
	 */

	public void setID(int id) {
		this.id = id;
	}

	/**
	 * Método set para a designação da componente.
	 */

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	/**
	 * Método set para a designação da componente.
	 */

	public void setPreco(double preco) {
		this.preco = preco;
	}

	/**
	 * Método set para o tipo da componente.
     * @param tipo
     */

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	/**
	 * Método set para a lista de componentes complementares da componente.
	 */

	public void setComplementares(List<Componente> complementares) {
		this.complementares = new ArrayList<>();
		for(Componente c : complementares)
			this.complementares.add(c);
	}

	/**
	 * Método set para a lista de componentes incompativeis da componente.
	 */

	public void setIncompativeis(List<Componente> incompativeis) {
		this.incompativeis = new ArrayList<>();
		for(Componente c : incompativeis)
			this.incompativeis.add(c);
	}

	/**
	 * Construtor parameterizado para a classe de Componente
	 * @param id
	 * @param designacao
	 * @param preco
	 * @param tipo
	 * @param complementares
	 * @param incompativeis
	 */
	public Componente(int id, String designacao, double preco, int tipo, List<Componente> complementares, List<Componente> incompativeis) {
		this.id = id;
		this.designacao = designacao;
		this.preco = preco;
		this.tipo = tipo;
		this.complementares = new ArrayList<>();
		for(Componente c : complementares)
			this.complementares.add(c);

		this.incompativeis = new ArrayList<>();
		for(Componente c : incompativeis)
			this.incompativeis.add(c);
	}

	/**
	 * Método equals para componente.
	 * @param o Objeto a comparar.
	 * @return
	 */

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Componente that = (Componente) o;
		return id == that.id;
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, designacao, preco, tipo);
	}

	/**
	 * Construtor sem parametros da classe Componente.
	 */

	public Componente(){
		this.id = -1;
		this.designacao = "";
		this.preco = -1;
		this.tipo = -1;
		this.complementares = new ArrayList<>();
		this.incompativeis = new ArrayList<>();
	}

	/**
	 * Método que verifica se uma componente tem outras complementares.
	 * @return
	 */

	public boolean temComplementares(){
		boolean fim = this.complementares.size() > 0 ? true : false;
		return  fim;
	}

	/**
	 * Método que verifica se dois componentes sao compatiíveis.
	 * @param comp - objeto a verificar compatibilidade
	 * @return true - se forem compatíveis
	 * @return false - se forem incompatíveis
	 */

	public boolean isCompativel(Componente comp){
		for(Componente c : this.incompativeis){
			if(c.equals(comp))
				return false;
		}
		return true;
	}
}