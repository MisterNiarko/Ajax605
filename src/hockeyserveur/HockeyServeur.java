/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hockeyserveur;

import data.ListeDesMatchs;
import data.ListePariHttp;
import serveurs.ServeurTCP;
import serveurs.ServeurUDP;

/**
 *
 * @author Wiwi
 */
public class HockeyServeur {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    ListeDesMatchs listeMatch = ListeDesMatchs.getInstance();
    listeMatch.startThreadUpdate();
    
    ListePariHttp listePari = ListePariHttp.getInstance();

    Thread serveurMatch = new Thread(new ServeurUDP(6780, 4));
    serveurMatch.start();

    Thread serveurPari = new Thread(new ServeurTCP(1248, 10));
    serveurPari.start();
    }
}
