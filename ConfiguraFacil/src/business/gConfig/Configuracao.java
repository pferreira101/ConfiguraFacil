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
		this.pacotes = new ArrayList<>();
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
				return false;
		}

		return true;
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
	    if(c.getID() != -1) this.componentes.add(c);

	    System.out.println(c.getID() + " ADDED"); // FIXME: 12/29/2018
    }

	/**
	 * Método para adicionar uma lista de componentes a uma configuração
	 * @param comps Lista de componentes a adicionar
	 */
	public void addComponentes(List<Componente> comps) {for(Componente c : comps) this.addComponente(c);}

	/**
	 * Método para adicionar um pacote a uma configuração, adicionando apenas os componentes que possam ser instalados.
	 * @param p Pacote a adicionar
	 */
    public void addPacote(Pacote p){
	    List<Componente> compsPack = p.getComponentes();
	    List<Componente> compsConfig = this.getComponentes();
	    Set<Componente> compAux = new HashSet<>();

	    compAux.addAll(compsConfig);

	    for(Componente c : compsPack){
			boolean flag = compAux.contains(c); // teste se item ja se encontra selecionado
			if(!flag) {
				flag = this.compativel(c); // testa se algum incompativel do componente se encontra na config
				if (flag) {
					List<Componente> complmtr = c.getComplementares();
					for (Componente comp : complmtr) { // testa se todos os complementares do componente se encontram na config
						if (!flag) break;
						flag = compAux.contains(comp);
					}
					if (flag)
						this.addComponente(c);
				}
			}
		}
    }


	/**
	 * Método para obter componentes de uma configuração
	 * @return componentes presentes na configuração
	 */
	public List<Componente> getComponentes() {
        return this.componentes;
    }

	/**
	 * Método para estabelecer componentes de uma configuração
	 * @param componentes Componentes a usar
	 */
	public void setComponentes(List<Componente> componentes) {
        this.componentes = componentes;
    }

	/**
	 * Método para obter pacotes usados numa configuração
	 * @return Lista de pacotes usados
	 */
	public List<Pacote> getPacotes() {
        return this.pacotes;
    }

	/**
	 * Método para estabelecer pacotes usados numa configuração
	 * @param pacotes Pacotes a usar
	 */
    public void setPacotes(List<Pacote> pacotes) {
        this.pacotes = pacotes;
    }


	/**
	 * Método para determinar preço de uma configuração
	 * @return preço atual da configuração
	 */
	public double calculaPreco() {
	    double r = 0;

	    for(Componente c : this.componentes) r += c.getPreco();
	    for(Pacote p : this.pacotes){
	        for(Componente c : p.getComponentes()) r += c.getPreco();
	    }

	    return r;
    }

	/**
	 * Método para calcular desconto a aplicar
	 * @return Valor do desconto
	 */
	public double calculaDesconto(){
	    double r = 0;

	    for (Pacote p : this.pacotes) r += p.getDesconto();

	    return r;
    }

	/**
	 * Método para remover um Componente da configuração
	 * @param c Componente a remover
	 */
	public void rmComponente(Componente c){
	    this.componentes.remove(c);

        System.out.println(c.getID() + " REMOVED"); // FIXME: 12/29/2018
	}


	/**
	 * Método para remover uma lista de componentes da configuração
	 * @param comps Componentes a remover
	 */
	public void rmComponentes(List<Componente> comps){for(Componente c : comps) this.rmComponente(c);}


	/**
	 * Método para obter listagam de todos os componentes da configuração que são incompativeis com um dado item
	 * @param comp Componente com o qual se realiza o teste de incompatibilidade
	 * @return Lista dos componentes incompatíveis
	 */
	public List<Componente> incompativeis(Componente comp){
		List<Componente> componentesConfig = this.getComponentes();
		List<Componente> incompt = new ArrayList<>();
		boolean flag;

		for(Componente c : componentesConfig) {
			flag = comp.isCompativel(c);
			if(!flag) incompt.add(c);
		}
		return incompt;
	}

	/**
	 * Método para obter listagem de componentes da configuração que sejam incompativeis com algum componente do pacote
	 * @param pack Pacote a testar
	 * @return Lista dos componentes da configuração incompatíveis com algum componente do pacote
	 */
	public List<Componente> incompativeis(Pacote pack){
		Set <Componente> incomptAux = new HashSet<>();
		List<Componente> comps = pack.getComponentes();

		for(Componente c : comps){
			List<Componente> incomptListAux = this.incompativeis(c);
			incomptAux.addAll(incomptListAux);
		}

		List<Componente> res = new ArrayList<>();
		res.addAll(incomptAux);
		return res;

	}



    /**
     * Método que verifica a existência de componentes da configuração que formam um pacote
     * @param pacotes Lista de todos os pacotes existentes
     */
    public void componentesToPacote(List<Pacote> pacotes){
        for(Pacote p : pacotes){
            List<Componente> p_comps = p.getComponentes();
            if(this.componentes.containsAll(p_comps)){
                for(Componente c : p_comps){
                    this.componentes.remove(c);
                }
                this.pacotes.add(p);
            }
        }
    }

	/**
	 * Método que retorna a lista dos componentes que são necessários instalar para ser possível adicionar o pacote
	 * @param pack Pacote cujas complementaridades vão ser verificadas
	 * @return Lista dos componentes complementares do pacote, ainda por instalar
	 */
	public List<Componente> complementares(Pacote pack) {
    	List <Componente> complmtrP = pack.getComplementares();
    	List <Componente> res = new ArrayList<>();

    	for(Componente c : complmtrP){
    		boolean flag = this.componentes.contains(c);
    		if(!flag)
    			res.add(c);
		}

    	return res;
	}

	/**
	 * Método que retorna a lista dos componentes necessários instalar para concluir a instalação de um certo componente
	 * @param comp Componente a instalar
	 * @return Lista dos componentes necessários instalar
	 */
	public List<Componente> complementares(Componente comp) {
		List<Componente> lComplmtr = comp.getComplementares();
		List<Componente> complmtr = new ArrayList<>();

		for(Componente c : lComplmtr){
			boolean flag = this.componentes.contains(c);
			if(flag == false)
				complmtr.add(c);
		}

		return complmtr;
	}
}