package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import data.Evenement;
import data.ListeDesMatchs;
import data.ListePariHttp;
import data.Match;
import data.PariHttp;

public class GameBet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1720406452110603666L;
	
	private ListePariHttp listePari = null;
	private ListeDesMatchs listeMatch = null;
	

	public GameBet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listePari = ListePariHttp.getInstance();
		listeMatch = ListeDesMatchs.getInstance();
		int idPari = Integer.parseInt(request.getParameter("idPari"));
		
		try{
			PariHttp pari = listePari.getPari(idPari);
			PariHttp listeParis[] = listePari.getAllPari();
			Match listeMatchs[] = listeMatch.getAllMatch();
			Match match = listeMatchs[pari.getMatchID()];
			
			if(match.getEquipeGagnante() != null){
				for(PariHttp ipari : listeParis){
					if(ipari != null){
						if(ipari.getMatchID() == match.getId()){
							if(ipari.getNomEquipe().equals(match.getGagnant())){
								ipari.setStatus(1);
							}else{
								ipari.setStatus(2);
							}
						}
					}
				}
			
				if(pari.getStatus() == 1){
					pari.calculGain();
				}
			}else{
				if (match.getFin() == true){
					System.out.println("Exaeco..........");
				}else{
					System.out.println("Pas d'equipe gagnante pour le moment..........");
				}
			}
			
			System.out.println("Voici les gains de mon pari : " + pari.getGain());
			response.setContentType("application/json"); 
			response.setCharacterEncoding("UTF-8");
	        response.getWriter().write(new Gson().toJson(pari));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nbrPari = -1;
		int matchID = Integer.parseInt(request.getParameter("matchID"));
        String nomEquipe = request.getParameter("nomEquipe");
        float montantPari = Float.parseFloat(request.getParameter("montantPari"));
        if(ListeDesMatchs.getInstance().getMatch(matchID).getPeriode() < 3){
        	nbrPari = ListePariHttp.getInstance().parier(new PariHttp(matchID, nomEquipe, montantPari));
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(nbrPari));
    }
}
