package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import data.ListeDesMatchs;
import data.Match;

public class GameDetail extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private ListeDesMatchs donnees = null;

	public GameDetail() {
		super();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		donnees = ListeDesMatchs.getInstance();
		Match[] listeMatch = donnees.getAllMatch();
		Match matchDetail = listeMatch[1];
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("Message du servlet GqmeDetail -------------- id = "+id);
		for(Match match:listeMatch){
			System.out.println("Message du servlet GqmeDetail -------------- getId = "+match.getId());
			if(match.getId() == id) {
					matchDetail = match;
					break;
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
