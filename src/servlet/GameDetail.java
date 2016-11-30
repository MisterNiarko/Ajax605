package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import data.ListeDesMatchs;
import data.Match;
import data.MatchsEnCours;

/**
 * Servlet implementation class GameDetail
 */
//@WebServlet("/GameDetail")
public class GameDetail extends HttpServlet {
	private static final long serialVersionUID = 2L;
    private ListeDesMatchs donnees = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		donnees = ListeDesMatchs.getInstance();
		Match[] listeMatch = donnees.getAllMatch();
		Match matchDetail = listeMatch[1];
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("Message du servlet GqmeDetail -------------- "+id);
		for(Match match:listeMatch){
			if(match.getId() == id) {
					matchDetail = match;
			}
		}
		response.setContentType("application/json"); 
		response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(matchDetail));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
