package main.java.fr.eni.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.java.fr.eni.bll.BLLFactory;
import main.java.fr.eni.bll.ManagerUtilisateurs;


@WebServlet({ "/pageAccueil", "" })
public class PageAccueilServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ManagerUtilisateurs mgr;

	public PageAccueilServlet() {
		mgr = BLLFactory.getUtilisateursManager();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer affichageConnexion = (Integer) req.getAttribute("modeConnecte");
		if (affichageConnexion==null) {
			req.setAttribute("modeConnecte", affichageConnexion);
			req.getRequestDispatcher("/WEB-INF/pageAccueil.jsp").forward(req, resp);
		}else {
			req.setAttribute("modeConnecte", affichageConnexion);
			req.getRequestDispatcher("/WEB-INF/pageAccueil.jsp").forward(req, resp);
		}
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//dopost login servlet envoie les infos dans le dopost pageaccueil et ducoup rappel de doget pour afficher les valeurs
		doGet(req, resp);
	}

}
