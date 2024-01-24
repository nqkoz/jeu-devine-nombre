package application;

import java.util.ArrayList;

public class jeuNombre {
    private int borneMin;
    private int borneMax;
    private int mystere;
    private int minCourant;
    private int maxCourant;
    private int nbProposition;
    private boolean triche;
    private ArrayList<Proposition>Historique;
    private boolean Trouver;
    
    
    

    public jeuNombre(int borneMin, int borneMax) {
        this.borneMin = borneMin;
        this.borneMax = borneMax;
        mystere = (int) (Math.random() * (borneMax - borneMin + 1)) + borneMin;
        minCourant = borneMin;
        maxCourant = borneMax;
        nbProposition = 0;
        triche = (maxCourant - minCourant) != 0;
        Historique = new ArrayList<Proposition>();
        Trouver = false;
    }
    
    
    public int GetNbPropositions(){
        return nbProposition;
    }
    
    public boolean GetTrouver() {
        return Trouver;
    }


    public int getMinCourant() {
        return minCourant;
    }


    public void setMinCourant(int minCourant) {
        this.minCourant = minCourant;
    }


    public int getMaxCourant() {
        return maxCourant;
    }


    public void setMaxCourant(int maxCourant) {
        this.maxCourant = maxCourant;
    }

    // recommencer le jeu
    
    public void reset(int borneMin, int borneMax) {
        this.borneMin = borneMin;
        this.borneMax = borneMax;
        mystere = (int) (Math.random() * (borneMax - borneMin + 1)) + borneMin;
        minCourant = borneMin;
        maxCourant = borneMax;
        nbProposition = 0;
        Historique = new ArrayList<Proposition>();
    }

    public String Proposition(int proposition, Proposition a) {
        nbProposition++;

        if (proposition < minCourant || proposition > maxCourant) {
            return "Proposition illogique";
        }

        if (proposition < mystere) {
            Historique.add(new Proposition(proposition, minCourant, maxCourant));
            minCourant = proposition + 1;
            triche = (maxCourant - minCourant) != 0;
            return "Trop petit";
        }

        if (proposition > mystere) {
            Historique.add(new Proposition(proposition, minCourant, maxCourant));
            maxCourant = proposition - 1;
            triche = (maxCourant - minCourant) != 0;
            return "Trop grand";
        }

        if (proposition == mystere && triche) {
            do {
            mystere = (int) (Math.random() * (maxCourant - minCourant + 1)) + minCourant;
            }while(mystere == proposition);

            // Répétition de la logique en cas de triche
            if (proposition > mystere) {
                Historique.add(new Proposition(proposition, minCourant, maxCourant));
                maxCourant = proposition - 1;
                triche = (maxCourant - minCourant) != 0;
                return "Trop grand";
            }

            if (proposition < mystere) {
                Historique.add(new Proposition(proposition, minCourant, maxCourant));
                minCourant = proposition + 1;
                triche = (maxCourant - minCourant) != 0;
                return "Trop petit";
            }
        }

        if (proposition == mystere && !triche) {
        	Trouver = true;
            return "Bravo c'est le bon nombre";
            
        }

        
        return "Bravo c'est le "; //sinon ça marche pas
    }

    String getHistorique() {
        String HistoriqueProposition = "";
        for (Proposition proposition : Historique) {
            HistoriqueProposition = HistoriqueProposition + proposition.afficher() + "\n";
        }
        return HistoriqueProposition;
    }
}