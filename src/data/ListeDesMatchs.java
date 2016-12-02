/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Wiwi
 */
public class ListeDesMatchs {
    public final static int MAX_MATCH = 3;
    private static final int INTERVAL_TEMPS = 30;   //10 sec
    private Match ListeMatch[] = new Match[MAX_MATCH];
    public Semaphore sem = null;
    

    // updateplay time every INTERVAL_TIME sec
    private ScheduledExecutorService scheduler =  null;
    private ScheduledFuture<?> timerHandle = null;


    /** Technique du Holder */
    private static class SingletonHolder
    {		
        /** Instance unique non préinitialisée */
        private final static ListeDesMatchs instance = new ListeDesMatchs();
    }

    /** Point d'acces pour l'instance unique du singleton */
    public static ListeDesMatchs getInstance()
    {
        return SingletonHolder.instance;
    }

    //Initialisation	
    private ListeDesMatchs(){
        sem = new Semaphore(1);
        System.out.println("Semaphore initialisé");	
        Equipe e1 =  new Equipe("A");
        Equipe e2 =  new Equipe("B");
        Equipe e3 =  new Equipe("C");
        Equipe e4 =  new Equipe("D");
        Match M1 = new Match(0,e1,e2);
        Match M2 = new Match(1,e3,e4);
        Match M3 = new Match(2,e1,e4);

        //Cas pour un match en période 2 qui serait incrémenté a la période 3 par le systeme
        M3.setPeriode(2);
        M3.setTemps(2100);

        ListeMatch[0] = M1;
        ListeMatch[1] = M2;
        ListeMatch[2] = M3;
        System.out.println("ListDesMatch initialisé");			
    }

    public void startThreadUpdate(){
        scheduler = Executors.newScheduledThreadPool(1);
        startTimer();
        startEventManager();	
    }

    //Get on match detail 
    public Match getMatch(int index) {
        Match match = null;
        try {
            sem.acquire();
            if(ListeMatch[index] != null) {
                match = ListeMatch[index];
            } else {
                System.out.println("Index indefini");
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            sem.release();
        }
        return match;
    }

    //Get all match information
    public Match[] getAllMatch(){
        if (ListeMatch.length < 1) {
            return null;
        }
        return ListeMatch;
    }

    //Get list of match name with associate id 
    public synchronized MatchsEnCours getAllMatchName(){
        if (ListeMatch.length < 1) {
            return null;
        }
        MatchsEnCours matchName = new MatchsEnCours(ListeMatch);
        return matchName;
    }

    private void startEventManager(){
        new Thread(new GestionEvts()).start();
    }

    private void startTimer() {
        Runnable timer = new GestionTemps(INTERVAL_TEMPS);
        timerHandle = scheduler.scheduleAtFixedRate(timer, 1, 1, TimeUnit.SECONDS); 
        System.out.println("Timer scheduler demarré");
    }

    private void stopTimer(){
        if(timerHandle != null ){
            timerHandle.cancel(true);
        }
    }

    public Semaphore getSem() {
            return sem;
    }
    public void setSem(Semaphore sem) {
            this.sem = sem;
    }
}
