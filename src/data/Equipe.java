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
public class Equipe implements Serializable {
    
    private static final long serialVersionUID = 157508070381090052L;
    private String nom;
    private int penalite = 0;
    private static final int TEMPS_PENALITE = 2*60;
    
    public Equipe(String nom) {
            this.nom = nom;
    }
    
    public String getNom() {
            return nom;
    }
    
    public void setNom(String nom) {
            this.nom = nom;
    }

    public int getPenalite() {
            return penalite;
    }

    public void setPenalite(int penalite) {
            if(penalite >= TEMPS_PENALITE){ this.penalite = 0;
            } else { this.penalite = penalite;
            }
    }
    
    public boolean hasPenality(){
            if(penalite > 0)
                return true;
            else return false;
    }

    @Override
    public String toString() {
            return "Equipe " + nom;
    }
}
