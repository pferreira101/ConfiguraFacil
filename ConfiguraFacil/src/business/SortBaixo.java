package business;

import business.gConfig.Componente;
import java.util.Comparator;

/**
 * Classe para ordenar Componentes pelo preço crescente.
 */

public class SortBaixo implements Comparator<Componente>{
    public int compare(Componente a,Componente b){
        if (a.getPreco() < b.getPreco())
            return -1;
        else return 1;
    }
}
