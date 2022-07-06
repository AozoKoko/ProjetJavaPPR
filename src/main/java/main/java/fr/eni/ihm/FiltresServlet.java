package main.java.fr.eni.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.fr.eni.bll.BLLFactory;
import main.java.fr.eni.bll.ManagerArticles;
import main.java.fr.eni.bll.ManagerCategorie;
import main.java.fr.eni.bll.ManagerEnchere;
import main.java.fr.eni.bll.ManagerUtilisateurs;
import main.java.fr.eni.bo.Articles;
import main.java.fr.eni.bo.Categorie;
import main.java.fr.eni.bo.Enchere;
import main.java.fr.eni.bo.Utilisateur;

@WebServlet("/filtresServlet")
public class FiltresServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	private ManagerEnchere mgr;
	private ManagerUtilisateurs mgrUser;
	private ManagerArticles mgrArt;
	private ManagerCategorie mgrCat;
	
	public FiltresServlet () {
		mgr = BLLFactory.getEnchereManager();
		mgrUser = BLLFactory.getUtilisateursManager();
		mgrArt = BLLFactory.getArticlesManager();
		mgrCat = BLLFactory.getCategorieManager();
	}
 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Utilisateur user = new Utilisateur();
		Articles article = new Articles();
		Categorie cat = new Categorie();
		Enchere enchere = new Enchere();
		
		req.getRequestDispatcher("/WEB-INF/pageAccueil.jsp").forward(req, resp);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
		
		req.getRequestDispatcher("/WEB-INF/pageAccueil.jsp").forward(req, resp);
	}

}
