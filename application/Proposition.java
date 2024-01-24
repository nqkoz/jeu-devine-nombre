package application;

public class Proposition {
    private int valeur;
    private int valMin;
    private int valMax;


    Proposition(int valeur,int valMin,int valMax){
        this.valeur = valeur;
        this.valMax = valMax;
        this.valMin = valMin;
    }

    String afficher(){
        return("Proposition : "+valeur+" | Borne Min : "+valMin+" ; Borne Max : "+valMax);

    }


}