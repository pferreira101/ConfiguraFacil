package business.gConfig;

import java.util.*;

public class Configuracao {
	private List<Componente> componentes;
	private List<Pacote> pacotes;

	/**
	 * Construtor sem parametros da classe configuração.
	 */

	public Configuracao() {
		this.componentes = new ArrayList<>();
		this.componentes = new ArrayList<>();
	}

	/**
	 * Contrutor parameterizado para a classe configuração.
	 * @param componentes
	 * @param pacotes
	 */

	public Configuracao(List<Componente> componentes, List<Pacote> pacotes) {
		this.componentes = new ArrayList<>();
		this.pacotes = new ArrayList<>();

		this.componentes.addAll(componentes);
		this.pacotes.addAll(pacotes);
	}

	/**
	 * Método que verifica se um componente pode ser inserido numa configuração.
	 * @param c Componente a verificar.
	 * @return
	 */

	public boolean compativel(Componente c){
		List<Componente> incompativeis = c.getIncompativeis();

		for(Componente k : incompativeis){
			if (this.componentes.contains(k))
				return true;
		}

		return false;
	}

	/**
	 * Método que verifica se um componente já está na configuração.
	 * @param c Componente a verificar.
	 * @return
	 */

	public boolean incluido(Componente c){
		return this.componentes.contains(c);
	}

	/**
	 * Método para adicionar um componente a uma configuração
	 * @param c Componente a adicionar.
	 */

	public void add(Componente c){
		this.componentes.add(c);
	}
}