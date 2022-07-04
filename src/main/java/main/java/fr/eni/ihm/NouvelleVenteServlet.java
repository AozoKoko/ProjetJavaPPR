package main.java.fr.eni.ihm;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import main.java.fr.eni.bll.BLLException;
import main.java.fr.eni.bll.BLLFactory;
import main.java.fr.eni.bll.ManagerArticles;
import main.java.fr.eni.bll.ManagerUtilisateurs;
import main.java.fr.eni.bo.Articles;
import main.java.fr.eni.bo.Categorie;
import main.java.fr.eni.bo.Utilisateur;
import main.java.fr.eni.dal.DALException;

@WebServlet("/nouvelleVente")
public class NouvelleVenteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ManagerUtilisateurs mgr;
	private ManagerArticles mgrArticle;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NouvelleVenteServlet() {
    	mgr = BLLFactory.getUtilisateursManager();
    	mgrArticle = BLLFactory.getArticlesManager();
    }


	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Utilisateur user = new Utilisateur();
		Integer idUser = Integer.parseInt(req.getParameter("param1"));
	
		try {
			user = mgr.selectById(idUser);
			req.setAttribute("profil", user);
			req.getRequestDispatcher("/WEB-INF/nouvelleVente.jsp").forward(req, resp);
		} catch (DALException e) {
			e.printStackTrace();
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Articles article = new Articles(req.getParameter("nomArticle"),req.getParameter("desciptionArticle"),req.getParameter("url"),
				Integer.parseInt(req.getParameter("prix")),LocalDate.parse(req.getParameter("debutEnchere")),LocalDate.parse(req.getParameter("finEnchere")));
		Utilisateur user = new Utilisateur();
		Integer idUser = Integer.parseInt(req.getParameter("param1"));
		Categorie cat = new Categorie(req.getParameter("genre"));
		try {
			user = mgr.selectById(idUser);
		} catch (DALException e) {
			e.printStackTrace();
		} catch (BLLException e) {
			e.printStackTrace();
		}
		if (req.getParameter("newEnchere").equals("save")) {
			mgrArticle.insertArticles(article, user, cat);
			req.getRequestDispatcher("/WEB-INF/pageAccueil.jsp").forward(req, resp);
		}
		if (req.getParameter("newEnchere").equals("annule")) {
			req.getRequestDispatcher("/WEB-INF/pageAccueil.jsp").forward(req, resp);
		}
		
	}

}
