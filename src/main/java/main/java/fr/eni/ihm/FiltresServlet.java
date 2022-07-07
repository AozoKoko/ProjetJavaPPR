package main.java.fr.eni.ihm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	Enchere enchere = new Enchere();
	Utilisateur user = new Utilisateur();
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
		

		



		
		req.getRequestDispatcher("/pageAccueil").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		// String filtreNom = req.getParameter("filtreNom") ;
		// articles = mgrArt.getArticleByName(filtreNom);

		if ("default".equals(req.getParameter("filtreCategorie")) && req.getParameter("filtreCategorie")!=null) {
			System.out.println("Catégorie");
			Integer filtreCategorie = Integer.parseInt(req.getParameter("filtreCategorie"));
			articles = mgrArt.getAllArticles(1, 1);
		}

		if (req.getParameter("group1") != null) {
			System.out.println("RadioAchat");
			if (req.getParameter("EncheresOuvertes") != null) {
				System.out.println("EncheresOpen");
				for(Articles article :  mgrArt.getAllEncheresOpen()) {
					articles.add(article);
				}
			}

			if (req.getParameter("UtilisateurEncheres") != null) {
				System.out.println("UserEnchers");
				encheres =  mgr.getEncheresByEncherisseur(user);
				for (Enchere enchere1 : encheres){
					System.out.println("ah");
					enchere = enchere1;
					for(Articles article : mgrArt.getAllUserEnchere(user, enchere)){
						System.out.println("beh");
						articles.add(article);
					}
				}


			}
			if (req.getParameter("EncheresGagnees") != null) {
				System.out.println("EncheresWon");
				for(Articles article : mgrArt.getAllUserEnchereRemportees(user, encheres)) {
					articles.add(article);
				}
			}
		}
		if (req.getParameter("radioVentes") != null) {
			System.out.println("RadioVentes");
			if (req.getParameter("VentesEnCours") != null) {
				System.out.println("MesVentesEnCours");
				for(Articles article : mgrArt.getAllVentesUtilisateur(user, 0)){
					articles.add(article);
				}

			}
			if (req.getParameter("ventesNoDebutees") != null) {
				System.out.println("VentesNonDébutées");
				for(Articles article :mgrArt.getAllVentesUtilisateur(user, 1)){
					articles.add(article);
				}

			}
			if (req.getParameter("ventesFinies") != null) {
				System.out.println("VentesTerminées");
				for(Articles article : mgrArt.getAllVentesUtilisateur(user, 2)){
					articles.add(article);
				}

			}
		}

		for(Articles articles : articles) {
			System.out.println("Articles pre hashset" + articles);
		}

		Set<Articles> set = new HashSet<>(articles);
		articles.clear();
		articles.addAll(set);
	//	System.out.println("articlesByNom " + articles.get(0));
		for(Articles articles : articles) {
			System.out.println("Article post hashset" + articles);
		}
			
		req.getRequestDispatcher("/pageAccueil").forward(req, resp);

}
}
