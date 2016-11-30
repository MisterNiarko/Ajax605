package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.*;

import data.Match;
import data.ListeDesMatchs;

/**
 * Servlet implementation class GameDetail
 */
//@WebServlet("/GameDetail")
public class GameDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
		int id = Integer.parseInt(request.getParameter("id"));
		donnees = ListeDesMatchs.getInstance();
		Match match = donnees.getMatch(id);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(match));		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
