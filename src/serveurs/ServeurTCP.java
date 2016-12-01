/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveurs;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Wiwi
 */
public class ServeurTCP implements Runnable {
    
    private ServerSocket mySocket = null;
    private Socket connectionSocket = null;
    private ExecutorService pool;
    private int serverPort = 0;
    
    public ServeurTCP(int port,int poolSize){ 
        this.serverPort = port;
        this.pool = Executors.newFixedThreadPool(poolSize);
    }
    
    public void start() {
        
        System.out.println("Serveur TCP demarré sur le port : " + String.valueOf(serverPort));
        
        try {
            mySocket = new ServerSocket(serverPort); // port convenu avec les clients
            while (true) {
                setConnectionSocket( mySocket.accept() ); //// réception bloquante
                
                System.out.println("La connexion est ouverte " + getConnectionSocket().toString());
                
                pool.execute(new GestionPari(getConnectionSocket()));					
            }
        } catch (SocketException e) {
                System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
                System.out.println("IO: " + e.getMessage());
        }
        finally {
                System.out.println("Fin de récéption");
                stop();
        }
    }
    public void stop() {
        pool.shutdown(); 
        try {
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow();
                if (!pool.awaitTermination(60, TimeUnit.SECONDS)) 
                    System.out.println("Pool non terminé");
            }
        } catch (InterruptedException ie) {
                pool.shutdownNow();
                Thread.currentThread().interrupt();
        }
        finally
        {
            if (connectionSocket != null) {
                try {
                    connectionSocket.close();
                }
                catch (final IOException e) {
                    e.printStackTrace();
                }                    
            }                    
        }
    } 
    
    @Override
    public void run() {
        start();
    }
    
    public ServerSocket getMySocket() {
        return mySocket;
    }

    public Socket getConnectionSocket() {
        return connectionSocket;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setMySocket(ServerSocket mySocket) {
        this.mySocket = mySocket;
    }

    public void setConnectionSocket(Socket connectionSocket) {
        this.connectionSocket = connectionSocket;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }
}