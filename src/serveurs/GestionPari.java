/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveurs;

import data.ListeDesMatchs;
import data.Match;
import data.Pari;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author Wiwi
 */
public class GestionPari implements Runnable {
    
    private Socket connectionSocket = null;
    private ListeDesMatchs donnees = null;

    public GestionPari(Socket connectionSocket) {
            super();
            this.connectionSocket = connectionSocket;		
            System.out.println("Nouveau runnable");
    }

    @Override
    public void run() {
        try{
            donnees = ListeDesMatchs.getInstance();

            //Déclaration des streams que nous allons utiliser
            //On retrouve le pariCourant
            InputStream is = getConnectionSocket().getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            ObjectInputStream ois = new ObjectInputStream(bis);

            //On envoie une confirmation au client
            OutputStream os = getConnectionSocket().getOutputStream();

            //On récupère l'objet Pari
            Pari pariCourant = (Pari) ois.readObject(); //On attend l'objet

            System.out.println("On a le pariCourant : " + pariCourant.getPariID());

            //On s'assure que la période est est <= 2
            Match detailMatch = donnees.getMatch(pariCourant.getMatchID());
            if(detailMatch.getPeriode() <= 2){
                //On sauvegarde le pari
                sauvegardePari(pariCourant);

                //On met à jour la mise totale
                majMiseTotale(pariCourant);                

                System.out.println("Le pari à bien été sauvegardé");

                os.write(1); // Le pari a été sauvegardé

                System.out.println("Le client devrait recevoir un acquittement");
            }
            else
            {
                os.write(0); // Le pari n'est pas sauvegardé et on envoie une erreur au client

                System.out.println("Le client devrait recevoir une erreur : code 0");
            }

        os.flush();
        } catch(Exception e) {
            e.printStackTrace();
            try{
                //Quelque chose s'est mal passé, on prévient le client
                OutputStream os = getConnectionSocket().getOutputStream();  
                os.write(-1); // Quelque chose s'est mal passé
            }catch(Exception e2) {
                e2.printStackTrace();
            }
        }
    }
	
    private synchronized void sauvegardePari(Pari pariCourant) throws IOException{ 
        System.out.println("sauvegardePari : demandé pour le pari : " + pariCourant.getPariID());
        try{	
            //Nouveau fichié nommé "match#TheID" et on set append à true
            List<Pari> listePari = new ArrayList<>();
            String nomFichier = "match#" + String.valueOf(pariCourant.getMatchID());
            System.out.println("sauvegardePari : valide si le fichier existe ou non");
            File f = new File(nomFichier);
            
            if(f.exists()){
                
                System.out.println("sauvegardePari : le fichier existe déja");

                FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);                
                System.out.println("sauvegardePari : ouverture des streams de lecture");
                
                listePari = (List<Pari>) ois.readObject();                
                System.out.println("sauvegardePari : on récupère le l'objet dans le fichier");
                
                ois.close();
                
                System.out.println("sauvegardePari: fermeture des streams de lectures");
                
            }
            listePari.add(pariCourant);
            FileOutputStream fos = new FileOutputStream(nomFichier);            
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            System.out.println("sauvegardePari: ouverture des streams de sortie");
            
            oos.writeObject(listePari);
            System.out.println("sauvegardePari: écriture de l'objet dans le fichier");
            
            oos.flush();
            System.out.println("sauvegardePari: flush()");
            
            oos.close();
            System.out.println("sauvegardePari: fermeture des streams de sortie");            
            
            System.out.println("sauvegardePari: pariCourant: " + pariCourant.getPariID());
                       
            System.out.println("sauvegardePari: l'objet a été sauvegardé");
	}catch(Exception ex){
		   ex.printStackTrace();
	}
        System.out.println("sauvegardePari : a terminé pour le pari : " + pariCourant.getPariID());
    }
    
    private synchronized void majMiseTotale(Pari pariCourant) throws IOException{ 
        try{
            String nomFichier = "miseTotaleMatch#" + String.valueOf( pariCourant.getMatchID() );
            float miseTotale = 0;
            
            //On valide que le fichier existe déja
            File f = new File(nomFichier);
            if(f.exists() && !f.isDirectory()) { 
                //Le fichier existe déja alors on va chercher la valeur
                FileInputStream fis = new FileInputStream(f);
                DataInputStream dis = new DataInputStream(fis);            
                miseTotale = dis.readFloat();            
                dis.close();
            }        

           FileOutputStream fos = new FileOutputStream(nomFichier);
           DataOutputStream dos = new DataOutputStream(fos);     
           miseTotale = miseTotale + pariCourant.getMontantPari();      
           dos.writeFloat(miseTotale);
           dos.close();
           System.out.println("majMiseTotale : la mise totale à été mise à jour : " + String.valueOf(miseTotale) + "$");
	} catch(Exception e) {
		   e.printStackTrace();
	}
    }
    
    static public synchronized float getMiseTotale(int matchID) throws IOException{ 
       float totalBetting = 0;
       String nomFichier = "miseTotaleMatch#" + String.valueOf(matchID);
        try{  
            //On valide que le fichier existe déja
            File f = new File(nomFichier);
            if(f.exists() && !f.isDirectory()) { 
                //Le fichier existe déja alors on va chercher la valeur
                FileInputStream fis = new FileInputStream(f);
                DataInputStream dis = new DataInputStream(fis);            
                totalBetting = dis.readFloat();            
                dis.close();
            }            
           
	}catch(Exception ex){
		   ex.printStackTrace();
	}  
        return totalBetting;
    }
    
    static public synchronized Hashtable<String, Pari> getTableGagnant(Match detailMatch){
        Hashtable<String, Pari> tableGagnant = new Hashtable<String, Pari>();
        int matchID = detailMatch.getId();
        String nomEquipeGagnant = detailMatch.getGagnant();        
        try{  
            //On valide que le fichier existe déja
            File f = new File("match#" + String.valueOf( matchID ));
            if(f.exists() && !f.isDirectory()) { 
                //Le fichier existe déja alors on va chercher la valeur
                FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);            
               
                List<Pari> listePari = (List<Pari>) ois.readObject();
                ois.close();
                for(int i = 0; i< listePari.size(); i++){
                    Pari pariCourant = listePari.get(i);
                    System.out.println("getTableGagnant : pariID : " + pariCourant.getPariID()+ " pour le matchID : #" + String.valueOf(matchID));
                    if(pariCourant.getNomEquipe().equals(nomEquipeGagnant) ){
                        System.out.println("getTableGagnant : pariID : " + pariCourant.getPariID()+ " a été ajouté à tableGagnant" );
                        tableGagnant.put(pariCourant.getPariID(),pariCourant);
                    } 
                }  
            }    
        }catch(Exception ex){
            ex.printStackTrace();
	}
        System.out.println("getTableGagnant : une tableGagnant a été retourné pour le matchID : #" + String.valueOf(matchID));
        return tableGagnant;
    }
    
    public Socket getConnectionSocket() {
        return connectionSocket;
    }
    public void setConnectionSocket(Socket connectionSocket) {
        this.connectionSocket = connectionSocket;
    }
}
