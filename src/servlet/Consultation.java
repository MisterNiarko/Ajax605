package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Consultation extends HttpServlet {
	private static final long serialVersionUID = -6149650666263762404L;

	public void doGet(HttpServletRequest requete, HttpServletResponse reponse)throws ServletException, IOException{
		this.getServletContext().getRequestDispatcher("/Home.jsp").forward(requete, reponse);
	}
}
