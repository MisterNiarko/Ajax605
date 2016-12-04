/**
 * 
 */
package data;

/**
 * @author Wiwi
 *
 */
public class PariHttp {

    private int pariID = 0; 
    private int matchID = 0;
    private String nomEquipe = null;
    private byte[] adresseIPClient = new byte[4];
    private float montantPari = 0;
    
    public PariHttp(int matchID, String nomEquipe, float montantPari) {
       this.matchID = matchID;
       this.nomEquipe = nomEquipe;
       this.montantPari = montantPari;
    }
    
    public void setPariID(int pariID) {
        this.pariID = pariID;
    }
    
    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public void setAdresseIPClient(byte[] adresseIPClient) {
        this.adresseIPClient = adresseIPClient;
    }
    
    public void setMontantPari(float montantPari) {
        this.montantPari = montantPari;
    }

    public int getPariID() {
        return pariID;
    }

    public int getMatchID() {
        return matchID;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public byte[] getAdresseIPClient() {
        return adresseIPClient;
    }
    
     public float getMontantPari() {
        return montantPari;
    }
}
