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

import data.Evenement;
import data.ListeDesMatchs;
import data.Match;
import notif.Observable;
import notif.Observateur;

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
					System.out.println("Changement nombre event match : " + match.getId());
					Evenement evt = match.getEvtMatch().get(match.getEvtMatch().size()-1);
					System.out.println(evt.toString());
					listNbEvent.put(match.getId(), match.getEvtMatch().size());
					writer.write("data: "+ evt.toString() +"\n\n");
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
		/*response.setContentType("text/event-stream");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Connection", "keep-alive");
		response.setCharacterEncoding("UTF-8");
        final AsyncContext context = request.startAsync();
        for(Match m:ListeDesMatchs.getInstance().getAllMatch()){
        	m.ajouterObservateur(new Observateur() {
				@Override
				public void actualiser(Observable o) {
					Match match = (Match) o;
					System.out.println("ID : " +match.getId());
					System.out.println("nb Event : "+match.getEvtMatch().size());
					Evenement evt = match.getEvtMatch().get(match.getEvtMatch().size()-1);
					System.out.println(evt.toString());
					PrintWriter writer;
					try {
						writer = response.getWriter();
						writer.write("data: "+ evt.toString() +"\n\n");
						writer.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
        }*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
