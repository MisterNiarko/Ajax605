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
public class Evenement implements Serializable {
    private static final long serialVersionUID = -4081703874193292697L;
    private int temps;
    private String message;
    private int type;
    
    final static public int BUT = 0;
    final static public int PENALITE = 1;
    
    public Evenement(int type, String message){
            this.type = type;
            this.temps = 0;
            this.message = message;
    }
    public Evenement(int type, int time, String message){
            this.type = type;
            this.temps = time;
            this.message = message;
    }
    public int getTemps() {
        return temps;
    }
    public void setTemps(int temps) {
        this.temps = temps;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    @Override
    public String toString() {
        return "{\"temps\":" + temps + ",\"message\":\"" + message + "\"}";
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
}
