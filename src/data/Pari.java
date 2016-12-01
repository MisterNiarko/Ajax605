/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;

/**
 *
 * @author Wiwi
 */
public class Pari implements Serializable {
    
    private static final long serialVersionUID = 6755963867201929798L;
    private String pariID = null; 
    private int matchID = 0;
    private String nomEquipe = null;
    private String prenomClient = null;
    private String nomClient = null;
    private String adresseClient = null;
    private byte[] adresseIPClient = new byte[4];
    private float montantPari = 0;
    
    public Pari(String pariID, int matchID, String nomEquipe, float montantPari) {
       this.pariID = pariID;
       this.matchID = matchID;
       this.nomEquipe = nomEquipe;
       this.montantPari = montantPari;        
    }
    
    public void setPariID(String pariID) {
        this.pariID = pariID;
    }
    
    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public void setNomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public void setAdresseClient(String adresseClient) {
        this.adresseClient = adresseClient;
    }

    public void setAdresseIPClient(byte[] adresseIPClient) {
        this.adresseIPClient = adresseIPClient;
    }
    
    public void setBetAmount(float montantPari) {
        this.montantPari = montantPari;
    }

    public String getPariID() {
        return pariID;
    }

    public int getMatchID() {
        return matchID;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public String getNomClient() {
        return nomClient;
    }

    public String getAdresseClient() {
        return adresseClient;
    }

    public byte[] getAdresseIPClient() {
        return adresseIPClient;
    }
    
     public float getMontantPari() {
        return montantPari;
    }
}
