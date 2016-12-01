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
public class PariReponse implements Serializable {
    private static final long serialVersionUID = -7322334310148958036L;
    private String pariID = null;
    private int matchID = 0;
    private int status = 0; // 0(perdu) , 1(gagné) , 2(en cours)
    private float montantPari = 0;
    private float montantGagne = 0; 
    
    public PariReponse(String betID, int matchID, int status) {
        this.pariID = betID;
        this.matchID = matchID;
        this.status = status;      
    }
    
    public PariReponse(String pariID, int matchID, int status, float montantPari, float montantGagne) {
        this.pariID = pariID;
        this.matchID = matchID;
        this.status = status; 
        this.montantPari = montantPari;
        this.montantGagne = montantGagne;
    }

    @Override
    public String toString() {
        return "PariReponse{" + "pariID = " + pariID + ", matchID = " + matchID + ", status = " + status + ", montantPari = " + montantPari + ", montantGagne = " + montantGagne + '}';
    }

    public String getPariID() {
        return pariID;
    }
    public void setPariID(String pariID) {
        this.pariID = pariID;
    }
    public int getMatchID() {
        return matchID;
    }
    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public float getMontantPari() {
        return montantPari;
    }
    public void setMontantPari(float montantPari) {
        this.montantPari = montantPari;
    }
    public float getmontantGagne() {
        return montantGagne;
    }
    public void setMontantGagne(float montantGagne) {
        this.montantGagne = montantGagne;
    }
}
