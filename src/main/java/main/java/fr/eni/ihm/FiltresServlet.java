package main.java.fr.eni.ihm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import main.java.fr.eni.bll.BLLException;
import main.java.fr.eni.bll.BLLFactory;
import main.java.fr.eni.bll.ManagerArticles;
import main.java.fr.eni.bll.ManagerCategorie;
import main.java.fr.eni.bll.ManagerEnchere;
import main.java.fr.eni.bll.ManagerUtilisateurs;
import main.java.fr.eni.bo.Articles;
import main.java.fr.eni.bo.Categorie;
import main.java.fr.eni.bo.Enchere;
import main.java.fr.eni.bo.Utilisateur;
import main.java.fr.eni.dal.DALException;

@WebServlet("/filtresServlet")
public class FiltresServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
     
	List<Articles> articles;
	List<Enchere> encheres;
	private ManagerEnchere mgr;
	private ManagerUtilisateurs mgrUser;
	private ManagerArticles mgrArt;
	private ManagerCategorie mgrCat;
	private Integer idUser;
	
	public FiltresServlet () {
		mgr = BLLFactory.getEnchereManager();
		mgrUser = BLLFactory.getUtilisateursManager();
		mgrArt = BLLFactory.getArticlesManager();
		mgrCat = BLLFactory.getCategorieManager();
	}
	
	@Override
	public void init() throws ServletException {
		articles = new ArrayList<>();
		encheres = new ArrayList<>();
	}
 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// rappel id page accueil ? :
	
		Utilisateur user = new Utilisateur();
		idUser = Integer.parseInt(req.getParameter("param1"));
	
		try {
			user = mgrUser.selectById(idUser);
			req.setAttribute("profil", user);
			req.getRequestDispatcher("/WEB-INF/pageAccueil.jsp").forward(req, resp);
		} catch (DALException e) {
			e.printStackTrace();
		} catch (BLLException e) {
			e.printStackTrace();
		}	
		
		Enchere enchere = new Enchere();
		
		/*
		if ("default".equals(req.getParameter("filtreCategorie")) || req.getParameter("filtreCategorie")==null) {
			int filtreCategorie = Integer.parseInt(req.getParameter("filtreCategorie"));
			articles = mgrArt.getAllArticles(1, 1);
		}
		*/
		/*
		if ("checked".equals(req.getParameter("radioAchats"))) {
			if ("checked".equals(req.getParameter("encheresOpen"))) {
				articles = mgrArt.getAllEncheresOpen();
			}
			
			if ("checked".equals(req.getParameter("UserEncheres"))) {
				articles = mgrArt.getAllUserEnchere(user, enchere);
			}
			if ("checked".equals(req.getParameter("EncheresWon"))) {
				articles = mgrArt.getAllUserEnchereRemportees(user, encheres);
			}
		}
		if ("checked".equals(req.getParameter("radioVentes"))) {

			if ("checked".equals(req.getParameter("mesVentesEnCours"))) {
				articles = mgrArt.getAllVentesUtilisateur(user, 0);
			}
			if ("checked".equals(req.getParameter("ventesNonDebutees"))) {
				articles = mgrArt.getAllVentesUtilisateur(user, 1);
			}
			if ("checked".equals(req.getParameter("ventesTerminees"))) {
				articles = mgrArt.getAllVentesUtilisateur(user, 2);
			}
		}
		*/
		
		req.getRequestDispatcher("/pageAccueil").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String filtreNom = req.getParameter("filtreNom") ;
		articles = mgrArt.getArticleByName(filtreNom);
	//	System.out.println("articlesByNom " + articles.get(0));
		for(Articles articles : articles) {
			System.out.println(articles);
		}
			
		req.getRequestDispatcher("/pageAccueil").forward(req, resp);

}
}
