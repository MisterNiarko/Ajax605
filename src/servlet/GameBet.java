package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.ListePariHttp;
import data.PariHttp;

public class GameBet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1720406452110603666L;

	public GameBet() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int matchID = Integer.parseInt(request.getParameter("matchID"));
        String nomEquipe = request.getParameter("nomEquipe");
        float montantPari = Float.parseFloat(request.getParameter("montantPari"));
        int nbrPari = ListePariHttp.getInstance().parier(new PariHttp(matchID, nomEquipe, montantPari));
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"ID\": " + nbrPari + "}");
    }
}
