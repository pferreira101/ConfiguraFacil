package business.gConfig;

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

}