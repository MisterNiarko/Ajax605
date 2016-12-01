/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocole;

import java.io.Serializable;
import java.net.InetAddress;

/**
 *
 * @author Wiwi
 */
public class Message implements Serializable {

	private static final long serialVersionUID = -8258560361717235626L;

	final static public int REQUEST = 0;
	final static public int REPLY = 1;

	protected int type;	//request or reply
	protected int id; // id
	protected InetAddress destination; // destinataire
	protected int destinationPort; // port du destinataire

	public boolean isRequest(){
		return (type == REQUEST);
	}

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getNumero() {
		return id;
	}
	public void setNumero(int numero) {
		this.id = numero;
	}
	public InetAddress getDestination() {
		return destination;
	}
	public int getDestinationPort() {
		return destinationPort;
	}
	public void setDestinationPort(int destinationPort) {
		this.destinationPort = destinationPort;
	}
	public void setDestination(InetAddress destination) {
		this.destination = destination;
	}
	@Override
	public String toString() {
		String output = "Message [type=" + type + ", numero=" + id 
				+ ", destination=" + destination + ", destinationPort=" + destinationPort; 
		return output;	
	}
}