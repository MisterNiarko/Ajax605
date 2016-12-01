/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveurs;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Wiwi
 */
public class ServeurUDP implements Runnable {

    private DatagramSocket monSocket = null;

    private ExecutorService pool;
    private int portServeur = 0;

    public ServeurUDP(int port,int taillePool){ 
        portServeur = port;
        pool = Executors.newFixedThreadPool(taillePool);
    }	

    public void start() {
        monSocket = null;
        System.out.println("Serveur UDP demmaré sur le port : " + String.valueOf(portServeur));
        try {
            monSocket = new DatagramSocket(portServeur);
            byte[] buffer = new byte[1000];
            while (true) {
                DatagramPacket paquet = new DatagramPacket(buffer, buffer.length);
                monSocket.receive(paquet);
                System.out.println("Paquet UDP recu");	
                pool.execute(new GestionMessage(paquet,getMySocket()));					
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
        finally {
            System.out.println("end of reception");
            stop();
        }
    }

    public void stop() {
        pool.shutdown(); 
        try {
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow();
                if (!pool.awaitTermination(60, TimeUnit.SECONDS))
                    System.out.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }
        if (monSocket != null) {
            monSocket.close();
        }
    }

    public int getServerPort() {
        return portServeur;
    }

    public void setServerPort(int serverPort) {
        this.portServeur = serverPort;
    }
    public DatagramSocket getMySocket() {
        return monSocket;
    }

    public void setMySocket(DatagramSocket mySocket) {
        this.monSocket = mySocket;
    }

    @Override
    public void run() {
        start();	
    }
}
