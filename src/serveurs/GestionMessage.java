/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveurs;

import protocole.Message;
import protocole.Reponse;
import protocole.Requete;
import data.ListeDesMatchs;
import data.Match;
import data.MatchsEnCours;
import data.Pari;
import data.PariReponse;
import marshallize.Marshallizer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Wiwi
 */
public class GestionMessage implements Runnable {

    private DatagramSocket serverSocket;
    private ListeDesMatchs donnees = null;
    private DatagramPacket packetReceive;

    public GestionMessage(DatagramPacket packetReceive, DatagramSocket serverSocket) {
        super();
        this.packetReceive = packetReceive;
        this.serverSocket = serverSocket;
        System.out.println("Nouveau runnable");
    }

    @Override
    public void run() {
        donnees = ListeDesMatchs.getInstance();
        Message message = (Message) Marshallizer.unmarshall(packetReceive);
        System.out.println("Message recu " + String.valueOf(message.getType()));	
        if (message.isRequest()) {
            System.out.println("Construction de la réponse");
            Reponse reply = construireResponse((Requete)message);
            respond(reply);
        }
        System.out.println("Reponse OK");
    }
    
    private Reponse construireResponse(Requete request) {
        Reponse reponse = new Reponse(packetReceive.getAddress(),packetReceive.getPort(),request.getNumero());
        switch(request.getMethode()) {
        case liste :
            MatchsEnCours listeMatch = donnees.getAllMatchName();
            reponse.setValue(listeMatch);	
        break;
            
        case details:
            Object[] arguments = request.getArgument();
            int matchID = (int) arguments[0];
            System.out.println("Detail recu avec parametre : "+ String.valueOf(matchID));
            Match detailMatch = donnees.getMatch(matchID);
            reponse.setValue(detailMatch);			
        break;
            
        case infoPari:
            Object[] args = request.getArgument();                
            int pariMatchID = (int) args [0];                           
            String pariID = (String) args[1];

            System.out.println("Detail recu avec parametre : MatchID : "+ String.valueOf(pariMatchID + " et PariID : " + pariID.toString() ));  

            PariReponse pariReponse = null;

            try {
                float matchTotalBettingAmount = GestionPari.getMiseTotale(pariMatchID);

                System.out.println("La mise totale pour le match #"+ String.valueOf(pariMatchID + " est : " + String.valueOf(matchTotalBettingAmount) + "$" ));

                Match detailPariMatch = donnees.getMatch(pariMatchID);

                if(detailPariMatch.getGagnant() != null){

                    System.out.println("L'équipe gagnante est : " + detailPariMatch.getGagnant() );
                    Hashtable<String, Pari> winnerTable = GestionPari.getTableGagnant(detailPariMatch);
                    System.out.println("tableGagnant correspond à : " + String.valueOf(winnerTable.size()) );
                    System.out.println("Validation si tableGagnant contient le pariID courant : " + pariID );
                    if(winnerTable.containsKey(pariID)){

                        System.out.println("tableGagnant contient le pari : " + pariID );
                        
                        Pari pariCourant = winnerTable.get(pariID);

                        System.out.println("Le montant du pari courant pour le match #"+ String.valueOf(pariMatchID + " est : " + String.valueOf(pariCourant.getMontantPari()) + "$" ));

                        float montantPariCourant = pariCourant.getMontantPari();
                        float gainTotal = 0; 
                        Iterator<Map.Entry<String, Pari>> it = winnerTable.entrySet().iterator();

                        while (it.hasNext()) {
                            Map.Entry<String, Pari> entry = it.next();
                            Pari b = entry.getValue();
                            gainTotal += b.getMontantPari();
                        }

                        System.out.println("Le gain total pour le match #"+ String.valueOf(pariMatchID) + " est : " + String.valueOf(gainTotal) + "$" );

                        float wonAmount = (float)( (montantPariCourant / gainTotal) * (matchTotalBettingAmount * 0.75) );

                        System.out.println("Le montant gangné est : " + String.valueOf(wonAmount) + "$" );

                        pariReponse = new PariReponse(pariID, pariMatchID, 1, montantPariCourant, wonAmount );
                        reponse.setValue(pariReponse);

                        System.out.println("Le pari était gagné, et notre reponse.setValue contient un objet PariReponse" );

                    }
                    else{
                        pariReponse = new PariReponse(pariID, pariMatchID, 0 );
                        reponse.setValue(pariReponse);

                        System.out.println("Le pari n'était pas gagnant, et notre reponse.setValue contient un objet PariReponse" );
                    }
                }
                else{
                    pariReponse = new PariReponse(pariID, pariMatchID, 2 );
                    reponse.setValue(pariReponse);
                    
                    System.out.println("Le match n'est pas encore terminé");
                }
            } catch (IOException e) {
                    e.printStackTrace();
            }
        break;
        default:
           System.out.println("Cette methode n'existe pas");
            break;
        }								
        System.out.println("Message de réponse construit");		
        return reponse;
    }

    protected void respond(Reponse message) {
        try {
            byte[] reply = Marshallizer.marshallize(message);
            DatagramPacket datagram = new DatagramPacket(reply, reply.length, message.getDestination(), message.getDestinationPort()); 
            serverSocket.send(datagram);
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }
}
