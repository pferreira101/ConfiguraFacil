package business.gConfig;

import business.ConfiguraFacil;

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
	public void addComponente(Componente c){
	    this.componentes.add(c);
    }

    public void addPacote(Pacote p){
	    this.pacotes.add(p);
    }


    public List<Componente> getComponentes() {
        return this.componentes;
    }

    public void setComponentes(List<Componente> componentes) {
        this.componentes = componentes;
    }

    public List<Pacote> getPacotes() {
        return this.pacotes;
    }

    public void setPacotes(List<Pacote> pacotes) {
        this.pacotes = pacotes;
    }


    public double calculaPreco() {
	    double r = 0;

	    for(Componente c : this.componentes) r += c.getPreco();
	    for(Pacote p : this.pacotes){
	        for(Componente c : p.getComponentes()) r += c.getPreco();
	    }

	    return r;
    }

    public double calculaDesconto(){
	    double r = 0;

	    for (Pacote p : this.pacotes) r += p.getDesconto();

	    return r;
    }

}