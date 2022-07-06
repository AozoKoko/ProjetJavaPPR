package main.java.fr.eni.ihm;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import main.java.fr.eni.bll.*;
import main.java.fr.eni.bo.Articles;
import main.java.fr.eni.bo.Categorie;
import main.java.fr.eni.bo.Enchere;
import main.java.fr.eni.bo.Utilisateur;
import main.java.fr.eni.dal.DALException;

@WebServlet("/nouvelleVente")
public class NouvelleVenteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ManagerUtilisateurs mgr;
	private ManagerArticles mgrArticle;
	private Integer idUser;

	private ManagerEnchere mgrE;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NouvelleVenteServlet() {
    	mgr = BLLFactory.getUtilisateursManager();
    	mgrArticle = BLLFactory.getArticlesManager();
		mgrE = BLLFactory.getEnchereManager();
    }


	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Utilisateur user = new Utilisateur();
		idUser = Integer.parseInt(req.getParameter("param1"));
	
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
		Articles article = new Articles(req.getParameter("nomArticle"),req.getParameter("descriptionArticle"),
										LocalDate.parse(req.getParameter("debutEnchere")),
										LocalDate.parse(req.getParameter("finEnchere")),
										Integer.parseInt(req.getParameter("prix")),
										Integer.parseInt(req.getParameter("prix")),
										req.getParameter("url"));
		Utilisateur user = new Utilisateur();
		Categorie cat = new Categorie(Integer.parseInt(req.getParameter("genre")));
		try {
			user = mgr.selectById(idUser);
		} catch (DALException e) {
			e.printStackTrace();
		} catch (BLLException e) {
			e.printStackTrace();
		}
		if (req.getParameter("newEnchere").equals("save")) {
			mgrArticle.insertArticles(article, user, cat);
			Articles articles = mgrArticle.getObjectArticleByName(article.getNomArticle());
			Enchere enchere = new Enchere(articles.getDateDebutEncheres(),articles.getMiseAPrix(),user.getNoUtilisateur(),user.getNoUtilisateur(), article.getNoArticle());
			try {
				mgrE.ajouterEnchere(enchere);
			} catch (BLLException e) {
				throw new RuntimeException(e);
			}
			req.getRequestDispatcher("/WEB-INF/pageAccueil.jsp").forward(req, resp);
		}
		if (req.getParameter("newEnchere").equals("annule")) {
			req.getRequestDispatcher("/WEB-INF/pageAccueil.jsp").forward(req, resp);
		}
		
	}

}
