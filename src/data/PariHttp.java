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
    private float gain = 0;
    private int status = 0;		//0:en attente, 1:gagné, 2:perdu
    
    public PariHttp(int matchID, String nomEquipe, float montantPari) {
       this.matchID = matchID;
       this.nomEquipe = nomEquipe;
       this.montantPari = montantPari;
    }
    
    public void calculGain(){
		float miseTotale = ListePariHttp.getInstance().getMiseMatch(matchID);
		float gainTotal = ListePariHttp.getInstance().getGainMatch(matchID);
		gain = (float)( (montantPari / gainTotal) * (miseTotale * 0.75) );
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
    
    public void setGain(float gain) {
        this.gain = gain;
    }
    
    public void setStatus(int status) {
        this.status = status;
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
     
     public float getGain() {
         return gain;
     }
     
     public int getStatus() {
         return status;
     }
     
     @Override
     public String toString() {
         return "Pari n° " + pariID + " Equipe : " + nomEquipe + " Montant : " + montantPari;
     }
}
