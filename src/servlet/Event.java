package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Observer;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import data.Evenement;
import data.ListeDesMatchs;
import data.Match;

/**
 * Servlet implementation class EventGoal
 */
//@WebServlet("/EventGoal")
public class Event extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Event() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		Match[] listeMatch = ListeDesMatchs.getInstance().getAllMatch();
		HashMap<Integer, Integer> listNbEvent = new HashMap<Integer, Integer>();
		response.setContentType("text/event-stream");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Connection", "keep-alive");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		System.out.println("Taille listeMatch : "+listeMatch.length);
		for(Match match: listeMatch){
			System.out.println("ID : " +match.getId());
			System.out.println("nb Event : "+match.getEvtMatch().size());
			listNbEvent.put(match.getId(), match.getEvtMatch().size());
		}
		while(true){
			for(Match match: listeMatch){
				System.out.println("ID : " +match.getId());
				System.out.println("nb Event : "+match.getEvtMatch().size());
				if(match.getEvtMatch().size() != listNbEvent.get(match.getId()).intValue()){
					String type = new String();
					System.out.println("Changement nombre event match : " + match.getId());
					Evenement evt = match.getEvtMatch().get(match.getEvtMatch().size()-1);
					if(evt.getType() == 0){type = "Goal";}
					else if(evt.getType() == 1){type = "Penalite";}
					else{type = "Pause ";}
					System.out.println(evt.toString());
					listNbEvent.put(match.getId(), match.getEvtMatch().size());
					writer.write("data:{\"id\":" + match.getId() + ",\"type\":" + evt.getType() + ",\"message\":" + evt.toString()+"}\n\n" );
					writer.flush();
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
