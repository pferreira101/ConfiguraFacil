package business;

import business.gConfig.Componente;
import java.util.Comparator;

public class SortRazao implements Comparator<Componente> {

    public int compare(Componente a,Componente b){
        double razaoA = 0,razaoB = 0;
        int nA = 0,nB = 0;

        for(Componente c : a.getComplementares()){
            razaoA += c.getPreco();
            nA++;
        }
        razaoA += a.getPreco(); nA++;

        razaoA = razaoA / nA;

        for(Componente c : b.getComplementares()){
            razaoB += c.getPreco();
            nB++;
        }
        razaoB += b.getPreco(); nB++;

        razaoB = razaoB / nB;

        int fim;
        fim = razaoA < razaoB ? 1 : -1;

        return fim;
    }
}
