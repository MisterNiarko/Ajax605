/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import notif.*;

/**
 *
 * @author Wiwi
 */
public class Match  implements Serializable, Observable  {
    private static final long serialVersionUID = -2841880765635920039L;
    private int id = 0;
    private int temps = 0;
    private Equipe local = null;
    private int scoreLocal = 0;
    private Equipe visiteur = null;
    private int scoreVisiteur = 0;
    private Equipe gagnant = null;
    
    private List<Evenement> evtMatch = new ArrayList<Evenement>();
    private ArrayList<Observateur> tabObservateur;// Tableau d'observateurs.
    private int periode;
    
    private int debutPeriode = 0;
    private boolean pause = false;
    private static final int TEMPS_PERIODE = 20*60;
    private static final int TEMPS_PAUSE = 15*60;
    public static final int TEMPS_MAX = 3 * TEMPS_PERIODE + 2 * TEMPS_PAUSE;
    
    public Match(int id, Equipe local, Equipe visiteur){		
        this.id = id;
        this.local = local;
        this.visiteur = visiteur;
        this.periode = 1;
        tabObservateur=new ArrayList<Observateur>();
    }
    
    public void ajouterObservateur(Observateur o)
    {
            tabObservateur.add(o); 
    }
    
    // Permet de supprimer (résilier) un observateur écoutant le GPS
    public void supprimerObservateur(Observateur o)
    {
            tabObservateur.remove(o);              
    }

    // Méthode permettant de notifier tous les observateurs lors d'un changement d'état du GPS.
    public void notifierObservateurs()
    {
            for(int i=0;i<tabObservateur.size();i++)
            {
                    Observateur o = tabObservateur.get(i);
                    o.actualiser(this);// On utilise la méthode "tiré".
            }
    }
    
    private void gestionPeriode(){
	switch (periode) {
            case 1:
		if (temps >= TEMPS_PERIODE && pause == false) {
                    pause = true;
                    evtMatch.add( new Evenement(temps, "Première pause !"));
		}
                else if ((temps >= TEMPS_PERIODE + TEMPS_PAUSE) && pause == true) {
                    pause = false;
                    evtMatch.add( new Evenement(temps, "C'est parti pour la seconde periode !"));
                    debutPeriode = temps;
                    periode = 2;
		}
            break;
            case 2:
		if ((temps >= debutPeriode + TEMPS_PERIODE ) && pause == false ) {
                    pause = true;
                    evtMatch.add( new Evenement(temps, "Seconde pause !"));
		}
		else if ((temps >= debutPeriode + TEMPS_PERIODE + TEMPS_PAUSE ) && pause == true) {
                    pause = false;
                    evtMatch.add( new Evenement(temps, "C'est parti pour la dernière periode !"));
                    debutPeriode = temps;
                    periode = 3;
		}
            break;
            case 3:
		if ((temps >= TEMPS_MAX ) && pause == false ) {
                    pause = true;
                    gestionGagnant();
                    evtMatch.add( new Evenement(temps, "Fin du match !"));
		}	
            break;
            default:
            break;
	}
    }
    
    private void gestionGagnant(){
        if( scoreVisiteur > scoreLocal){
                setGagnant(visiteur);
        } 
        else if( scoreLocal > scoreVisiteur) {
                setGagnant(local);
        }
    }
    
    public synchronized void butVisiteur(){
        this.scoreVisiteur += 1;
    }
    public synchronized void butLocal(){
		this.scoreLocal += 1;
	}
    
    public synchronized void ajouterEvt(Evenement evt){
        evt.setTemps(this.temps);
        this.evtMatch.add(evt);
        notifierObservateurs();
    }
    
    public Boolean getPause(){
        return this.pause;
    }
    public void setGagnant(Equipe gagnant) {
	this.gagnant = gagnant;
    }
    public String getGagnant(){
	if(gagnant != null)
            return gagnant.getNom();
	else
            return null;
    }
    Equipe getEquipeGagnante(){
	return gagnant;
    }
    public int getTemps() {
	return temps;
    }
    public synchronized void setTemps(int temps) {
	if(temps <= TEMPS_MAX) {
            this.temps = temps;
            gestionPeriode();
	}	
    }
    public Equipe getDomicile() {
        return local;
    }
    public void setDomicile(Equipe local) {
        this.local = local;
    }
    public Equipe getVisiteur() {
        return visiteur;
    }
    public void setExterieur(Equipe visiteur) {
        this.visiteur = visiteur;
    }
    public List<Evenement> getEvtMatch() {
        return evtMatch;
    }
    public void setMatchEvent(List<Evenement> evtMatch) {
        this.evtMatch = evtMatch;
    }
    public void setPeriode(int periode) {
        this.periode = periode;
    }
    public int getPeriode() {
        return periode;
    }
    public int getId() {
        return id;
    }
    public int getScoreLocal(){
    	return scoreLocal;
    }
    public int getScoreVisiteur(){
    	return scoreVisiteur;
    }
    
    public String getStringTemps() {
        String stringTemps = "";
        int[] tempsActuel = tempsToHMS();
        for( int j = 0; j < 3 ; j++) {	
            stringTemps += tempsActuel[j];
            if(j<2) {
                stringTemps += " : ";
            }
        }
        return stringTemps;
    }

    public int[] tempsToHMS() {
        int h = (int) temps / 3600;
        int memo = (int) temps - h * 3600;
        int m = memo / 60;
        int s = memo - m * 60;

        int[] ints = {h, m, s};
        return ints;
    }
    
    @Override
    public String toString() {
        String echo =  "Match : temps = " + temps + ", local = " + local + ", scoreLocal = " + scoreLocal + ", visiteur = " + visiteur + ", scoreVisiteur = " + scoreVisiteur + ", evtMatch = " ; 
            for (Evenement e : evtMatch){
                echo += e.toString() + "\t";
            }
            echo += ", periode=" + periode + "]";
        return echo;
    }
}
