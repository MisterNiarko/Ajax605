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
	
	private ListePariHttp donnees = null;

	public GameBet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		donnees = ListePariHttp.getInstance();
		int idPari = Integer.parseInt(request.getParameter("idPari"));
		PariHttp pari = donnees.getPari(idPari);
		if(pari.getStatus() == 1){
			pari.calculGain();
		}
		System.out.println("Voici les gains de mon pari : " + pari.getGain()); //Pour essai
		response.setContentType("application/json"); 
		response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(pari));
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
        response.getWriter().write(nbrPari);
    }
}
