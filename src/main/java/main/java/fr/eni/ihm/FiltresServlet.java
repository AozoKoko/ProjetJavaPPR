package main.java.fr.eni.ihm;

import java.io.IOException;
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
 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Utilisateur user = new Utilisateur();
		idUser = Integer.parseInt(req.getParameter("param1"));
	
		try {
			user = mgrUser.selectById(idUser);
			req.setAttribute("profil", user);
			req.getRequestDispatcher("/WEB-INF/pageAccueik.jsp").forward(req, resp);
		} catch (DALException e) {
			e.printStackTrace();
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	/*	
		// créa article pour recup liste
		Articles article = new Articles();

		if (req.getParameter("search")equals("mot-cle")) {
			
		}
		try {
			List<Articles> getArticleByName();
			req.getRequestDispatcher("/WEB-INF/pageAccueil.jsp").forward(req, resp);
		} catch (DALException e) {
			e.printStackTrace();
		} catch (BLLException e) {
			e.printStackTrace();
		}
*/
}
}
