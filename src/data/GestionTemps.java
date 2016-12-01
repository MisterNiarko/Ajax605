/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Wiwi
 */
public class GestionTemps implements Runnable {
    private ListeDesMatchs data;
    private int intervale = 0;

    public GestionTemps(int intervale) {
        this.data = ListeDesMatchs.getInstance();
        this.intervale = intervale;
        System.out.println("Gestionnaire de temps créé avec des intervale de "+ String.valueOf(intervale) + "s");
    }
    @Override
    public void run() {
        Match[] listeMatch = data.getAllMatch();
        if(listeMatch != null){
            majTempsMatch(listeMatch);
            System.out.println("Temps mis à jour");
        } else {
            System.out.println("Aucun match à mettre à jour");
        }
    }

    private void majTempsMatch(Match[] listeMatch){
        synchronized (listeMatch) {		
            for(int i=0; i< listeMatch.length; i++) {
                if(listeMatch[i] != null && listeMatch[i].getTemps() < Match.TEMPS_MAX ){
                    Match match = listeMatch[i];
                    match.setTemps(match.getTemps() + intervale);
                    System.out.println("Temps de jeu mis à jour : Match : " + String.valueOf(i) + " Temps : " + String.valueOf(match.getTemps()));        
                }
            }
        }
    }
}
