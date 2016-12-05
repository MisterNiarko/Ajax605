/**
 * 
 */
package data;

/**
 * @author Wiwi
 *
 */
public class ListePariHttp {

	/**
	 * 
	 */
	public final static int MAX_PARI = 100;
	private PariHttp ListePari[] = new PariHttp[MAX_PARI];
	private int nbrPari = 0;
	
	/** Technique du Holder */
    private static class SingletonHolder
    {		
        /** Instance unique non préinitialisée */
        private final static ListePariHttp instance = new ListePariHttp();
    }

    /** Point d'acces pour l'instance unique du singleton */
    public static ListePariHttp getInstance()
    {
        return SingletonHolder.instance;
    }
	
	public ListePariHttp() {
		// TODO Auto-generated constructor stub
	}
	
	public int parier(PariHttp pariCourant){
		pariCourant.setPariID(nbrPari);
		ListePari[nbrPari] = pariCourant;
		System.out.println(pariCourant.toString());
		nbrPari += 1;
		return nbrPari - 1;
	}
	
	public PariHttp getPari(int numPari){
		return ListePari[numPari];
	}
	
	public PariHttp[] getAllPari(){
		return ListePari;
	}
	
	public float getMiseMatch(int idMatch){
		float miseTotale = 0;
		for(PariHttp pari : ListePari){
			if(pari != null){
				if(pari.getMatchID() == idMatch){
					miseTotale += pari.getMontantPari();
				}
			}
		}
		return miseTotale;
	}
	
	public float getGainMatch(int idMatch){
		float gainTotale = 0;
		for(PariHttp pari : ListePari){
			if (pari != null) {
				if (pari.getMatchID() == idMatch) {
					if (pari.getStatus() == 1) {
						gainTotale += pari.getMontantPari();
					}
				} 
			}
		}
		return gainTotale;
	}
	
	

}
