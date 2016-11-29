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
public class MatchSimple implements Serializable{
    private static final long serialVersionUID = -790805962088102648L;
    private String equipeA;
    private String equipeB;
    private int temps = 0;
    private int id = 0;

    public String getEquipeA() {
        return equipeA;
    }
    public void setEquipeA(String equipeA) {
        this.equipeA = equipeA;
    }
    public String getEquipeB() {
        return equipeB;
    }
    public void setEquipeB(String equipeB) {
        this.equipeB = equipeB;
    }
    public int getTemps() {
        return temps;
    }
    public void setTime(int temps) {
        this.temps = temps;
    }
    public int getId() {
    return id;
    }
    public void setId(int id) {
        this.id = id;
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
	
    public String toString(){
        String echo = getEquipeA() + " VS " + getEquipeB() + " Timer :  " + getStringTemps(); 
        return echo;
    }
}