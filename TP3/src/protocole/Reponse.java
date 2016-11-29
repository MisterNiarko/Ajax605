/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocole;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.Arrays;

import data.Match;

/**
 *
 * @author axelg
 */
public class Reponse extends Message{
    private static final long serialVersionUID = 8507765429832335707L;
    private Serializable value;
    
    public Serializable getValue() {
	return value;
    }
    public void setValue(Serializable value) {
	if(value != null)
            this.value = value;
         else 
            System.out.println("Valeur de réponse inconnue");
    }
	
    public Reponse(InetAddress adresse,int port,int ID){
	type = Message.REQUEST;
	destinationPort = port;
	destination = adresse;
	this.id = ID;
    }
	
    @Override
    public String toString() {
	String output = super.toString();
	if (value != null) {
            if (value instanceof Match[]) 			
		output += 	", value=" + Arrays.toString( (Match[])value) + "]";
            else
		output += 	", value=" + value.toString() + "]";
	}
	return output;
    }
}