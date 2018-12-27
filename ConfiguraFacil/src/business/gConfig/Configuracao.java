package business.gConfig;

import business.ConfiguraFacil;

import java.util.*;

public class Configuracao {
	private List<Componente> componentes;
	private List<Pacote> pacotes;

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

    public Configuracao(){
	    this.componentes = new ArrayList<>();
	    this.pacotes = new ArrayList<>();
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