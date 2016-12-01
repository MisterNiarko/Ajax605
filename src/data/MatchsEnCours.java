/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Wiwi
 */
public class MatchsEnCours implements Serializable {
    private static final long serialVersionUID = 1325493603684452749L;
    private HashMap<Integer,MatchSimple> listeMatchSimple = new HashMap<Integer,MatchSimple>();
    
    public MatchsEnCours(Match[] allMatch) {
        for (int i = 0; i < allMatch.length; i++) {
            if(allMatch[i] != null) {
                Match currentMatch = allMatch[i];
                MatchSimple matchSentence = new MatchSimple();
                matchSentence.setId(currentMatch.getId());
                matchSentence.setEquipeA(currentMatch.getDomicile().getNom());
                matchSentence.setEquipeB(currentMatch.getVisiteur().getNom());
                matchSentence.setTime(currentMatch.getTemps());
                listeMatchSimple.put(currentMatch.getId(),matchSentence);
            }
        }
    }
    
    @SuppressWarnings("rawtypes")
    public HashMap<Integer, String> getNomMatch() {
            HashMap<Integer,String> nomMatch = new HashMap<Integer,String>();
            int i = 0;
            Iterator<Map.Entry<Integer, MatchSimple>> it = listeMatchSimple.entrySet().iterator();
        while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    nomMatch.put(i, pair.toString());
                    i++;
        }
        return nomMatch;
    }
    
    @Override
    public String toString() {
            return "ListMatchName [nomMatch = " + getNomMatch() + "]";
    }

    public HashMap<Integer,MatchSimple> getList(){
            return this.listeMatchSimple;
    }
    
}