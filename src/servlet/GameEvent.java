/**
 * 
 */
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
import data.Match;

/**
 * @author Wiwi
 *
 */
public class GameEvent extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private ListeDesMatchs donnees = null;

	/**
	 * 
	 */
	public GameEvent() {
		super();
		}
		
		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			donnees = ListeDesMatchs.getInstance();
			Match[] listeMatch = donnees.getAllMatch();
			List<Evenement> matchEvent = null;
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("Message du servlet GameEvent -------------- id = "+id);
			for(Match match:listeMatch){
				if(match.getId() == id) {
						matchEvent = match.getEvtMatch();
						break;
				}
			}
			response.setContentType("application/json"); 
			response.setCharacterEncoding("UTF-8");
	        response.getWriter().write(new Gson().toJson(matchEvent));
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
		}

}
