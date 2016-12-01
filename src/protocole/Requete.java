/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocole;

import java.net.InetAddress;

/**
 *
 * @author Wiwi
 */
public class Requete extends Message{
    private static final long serialVersionUID = 4219936234935667187L;
	public enum methodes {
		liste, details, infoPari
	};
	
	private static final int MAX_NUM = 50000;
	private Object[] argument;
	private methodes methode = null;
	private static int numRequest = 0;
	
	public Requete(){
		type = Message.REQUEST;
	}
	
	static synchronized public Requete newMatchList(InetAddress adresse, int port) {
		Requete requete = new Requete();
		requete.setMethode(methodes.liste);
		requete.setNumero(numRequest);
		requete.setDestinationPort(port);
		requete.setDestination(adresse);
		incrementNumRequest();
		return requete;
	}

	static synchronized public Requete newMatchDetails(InetAddress adress, int port,int idMatch) {
		Requete requete = new Requete();
		requete.setMethode(methodes.details);
		Object[] arg = {idMatch};
		requete.setArgument(arg);
		requete.setDestinationPort(port);
		requete.setDestination(adress);
		requete.setNumero(numRequest);
		incrementNumRequest();
		return requete;
	}
        
        static synchronized public Requete newInfoPari(InetAddress adress, int port,int idMatch, String idBet) {
		Requete requete = new Requete();
		requete.setMethode(methodes.infoPari);
		Object[] arg = {idMatch,idBet};
		requete.setArgument(arg);
		requete.setDestinationPort(port);
		requete.setDestination(adress);
		requete.setNumero(numRequest);
		incrementNumRequest();
		return requete;
	}
	
	static void incrementNumRequest(){
		if(numRequest == MAX_NUM){
			numRequest = 0;
		}
		numRequest ++;
	}

	public Object[] getArgument() {
		return argument;
	}
	public void setArgument(Object[] argument) {
		this.argument = argument;
	}
	public methodes getMethode() {
		return methode;
	}
	public void setMethode(methodes methode) {
		this.methode = methode;
	}
}