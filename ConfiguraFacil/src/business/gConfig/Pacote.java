package business.gConfig;

import java.util.*;

public class Pacote {
	private int id;
	private double desconto;
	private List<Componente> componentes = new ArrayList<>();

    public int getID() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public double getDesconto() {
        return this.desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public List<Componente> getComponentes() {
        return this.componentes;
    }

    public void setComponentes(List<Componente> componentes) {
        this.componentes = componentes;
    }
}