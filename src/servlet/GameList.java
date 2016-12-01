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
import data.MatchsEnCours;

/**
 * Servlet implementation class GameList
 */
//@WebServlet("/GameList")
public class GameList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ListeDesMatchs donnees = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		donnees = ListeDesMatchs.getInstance();
		MatchsEnCours listeMatch = donnees.getAllMatchName();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(listeMatch));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
