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

     public float getMontantPari() {
        return montantPari;
    }
     
     @Override
     public String toString() {
         return "{\"Pari n°\":" + pariID + ",\"Equipe\":\"" + nomEquipe + ",\"Montant\":\"" + montantPari + "\" }";
     }
}
