/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Wiwi
 */
public class GestionEvts implements Runnable {
	
    private ListeDesMatchs donnees;
    private int timerEvt = 0;
    private Random r = new Random();

    public GestionEvts() {
        this.donnees = ListeDesMatchs.getInstance();
        System.out.println(donnees.toString());
        this.timerEvt = 25000;
        System.out.println("Gestionnaire d'evenement créé");
    }

    @Override
    public void run() {
        randomValue();
    }

    private void randomValue(){
        while (true) {	
            Semaphore dataSem = donnees.getSem();
            try {
                dataSem.acquire();
                Match[] listeMatch = donnees.getAllMatch();
                int eventOnMatch = r.nextInt(2);   
                if (listeMatch[eventOnMatch] != null) {
                    if (!listeMatch[eventOnMatch].getPause() && listeMatch[eventOnMatch].getTemps() < Match.TEMPS_MAX ) {
                        Equipe teamEvent;
                        if(r.nextInt(1) == 0){
                            teamEvent = listeMatch[eventOnMatch].getDomicile();
                            listeMatch[eventOnMatch].butLocal();
                        } else {
                            teamEvent = listeMatch[eventOnMatch].getVisiteur();
                            listeMatch[eventOnMatch].butVisiteur();
                        }
                        listeMatch[eventOnMatch].ajouterEvt(new Evenement(Evenement.BUT,"Goal " + teamEvent.toString()));
                        System.out.println("Evenement - But " + teamEvent.toString());
                        //Probabilité d'une pénalité
                        if(r.nextInt(30) < 5){
                            //Si aucune pénalité
                            if( ! listeMatch[eventOnMatch].getDomicile().hasPenality() && (r.nextInt(1) == 0) ){
                                listeMatch[eventOnMatch].getDomicile().setPenalite(1);
                                listeMatch[eventOnMatch].ajouterEvt(new Evenement(Evenement.PENALITE,"Penalité pour " + teamEvent.toString()));
                                System.out.println("Penalité pour les locaux " + listeMatch[eventOnMatch].getDomicile().toString());
                            } 
                            //Sinon
                            else if( ! listeMatch[eventOnMatch].getVisiteur().hasPenality() && (r.nextInt(1) == 0)){
                                listeMatch[eventOnMatch].getVisiteur().setPenalite(1);
                                listeMatch[eventOnMatch].ajouterEvt(new Evenement(Evenement.PENALITE,"Penalité pour " + teamEvent.toString()));
                                System.out.println("Penalité pour les visiteurs " + listeMatch[eventOnMatch].getVisiteur().toString());
                            }
                        }
                    }
                }
                dataSem.release();
                int nextPossibleEvent = r.nextInt(timerEvt)+(timerEvt/2);
                Thread.sleep(nextPossibleEvent);
            } catch (InterruptedException e) {
                System.out.println("Erreur interuption");
            }
            finally {
                dataSem.release();
            }
        }
    }
}
