package business.gConfig;

import java.util.*;

public class Pacote {
	private int id;
	private double desconto;
	private List<Componente> componentes = new ArrayList<>();

    public Pacote(int id, List<Componente> comps, double desconto) {
        this.id = id;
        this.desconto = desconto;
        this.componentes = comps;
    }



    /**
     * Método para obter id do pacote
     * @return Id do pacote
     */
    public int getID() {
        return this.id;
    }

    /**
     * Método para estabelecer id do pacote
     * @param id
     */
    public void setID(int id) {
        this.id = id;
    }

    /**
     * Método para obter valor do desconto
     * @return Valor do desconto
     */
    public double getDesconto() {
        return this.desconto;
    }

    /**
     * Método para estabelecer desconto a aplicar
     * @param desconto Percentagem do preço a descontar
     */
    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    /**
     * Método para obter listagem dos componentes incluídos no pacote
     * @return Componentes englobados pelo pacote
     */
    public List<Componente> getComponentes() {
        return this.componentes;
    }

    /**
     * Método para definir os componentes do pacote
     * @param componentes Componentes a englobar pelo pacote
     */
    public void setComponentes(List<Componente> componentes) {
        this.componentes = componentes;
    }

    /**
     * Método para obter todos os complementares de um pacote
     * @return Lista dos componentes que devem estar instalados para ser possível adicionar o pacote.
     */
    public List<Componente> getComplementares(){
        Set <Componente> complmtrAux = new HashSet<>();

        for(Componente c : this.componentes){
            List<Componente> compl = c.getComplementares();
            complmtrAux.addAll(compl);
        }

        List<Componente> res = new ArrayList<>();
        res.addAll(complmtrAux);
        return res;
    }
}