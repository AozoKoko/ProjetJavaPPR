package main.java.fr.eni.ihm;

import java.io.IOException;
import java.time.LocalDate;

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

@WebServlet("/detailVente")
public class DetailVenteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ManagerEnchere mgr;
	private ManagerUtilisateurs mgrUser;
	private ManagerArticles mgrArt;
	private ManagerCategorie mgrCat;


	private Utilisateur user = new Utilisateur();
	private Utilisateur utilisateur = new Utilisateur();
	//rajout pour recup pseudo
	private String pseudo =null;
	private Articles article = new Articles();
	private Categorie cat = new Categorie();
	private Enchere enchere = new Enchere();
	private Utilisateur formerUser = new Utilisateur();

	public DetailVenteServlet () {
		mgr = BLLFactory.getEnchereManager();
		mgrUser = BLLFactory.getUtilisateursManager();
		mgrArt = BLLFactory.getArticlesManager();
		mgrCat = BLLFactory.getCategorieManager();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		// recup idUser
		Integer idUser= Integer.parseInt(req.getParameter("param1"));
		System.out.println("idUserSuiteBtnEncherir " + idUser);
		
		// recup idArticle
		Integer idArticle = Integer.parseInt(req.getParameter("param2"));
		System.out.println("idArticleSuiteBtnEncherir " + idArticle);
		
		// recup montant de la derniere enchere (montantEnchere)
		//Integer montantEnchere = Integer.parseInt(req.getParameter("montantEnchere"));
		
		// recup id de la dernière enchere de l'article correspondant
		//int idEnchere = Integer.parseInt(req.getParameter("param3"));
		//System.out.println("idEnchereSuiteBtnPrecEnchereTiers" + idEnchere);
		
		try {

			utilisateur = mgrUser.selectById(idUser);
			cat = mgrCat.selectById(idArticle);
			article = mgrArt.selectParId(idArticle);
			user = mgrUser.selectById(idArticle);
			enchere = mgr.getEnchereByNumArticle(idArticle);
			System.out.println(enchere.toString());
			formerUser = mgrUser.selectById(enchere.getNoEncherisseur());
			//rajout pour recup pseudo
			pseudo = mgrArt.getPseudoByIdArticle(idArticle);
			req.setAttribute("user", user);
			//renvoi de la donnee dans la jsp
			req.setAttribute("userPseudo", pseudo);
			req.setAttribute("utilisateur", utilisateur);
			req.setAttribute("article", article);
			req.setAttribute("cat", cat);
			req.getRequestDispatcher("/WEB-INF/detailVente.jsp").forward(req, resp);
		} catch (DALException e) {
			e.printStackTrace();
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		/*Integer creditUser = user.getCredit();
		System.out.println("creditUserDebut" + creditUser);
		
		
		enchere = mgr.selectById(idEnchere);
		Integer montantEnchereDebut = enchere.getMontantEnchere();
		System.out.println("montantEnchereDebut" + montantEnchereDebut);
		
		if (article.getMiseAPrix()>=montantEnchere && creditUser>=montantEnchere) {
			System.out.println("");
			Enchere enchereModify = new Enchere(idEnchere, LocalDate.parse(req.getParameter("dateEnchere")),
					Integer.parseInt(req.getParameter("nouveauMontant")), idUser, idArticle, 
					Integer.parseInt(req.getParameter("noEncherisseur")));
			
			int creditAcheteur = Integer.parseInt(req.getParameter("quantity"));
			mgr.updateEnchere(enchereModify, idUser, creditAcheteur);
			req.setAttribute("visible", true);
			req.getRequestDispatcher("/WEB-INF/detailVente.jsp").forward(req, resp);
		} else {
			req.setAttribute("visible", false);
			req.getRequestDispatcher("/WEB-INF/detailVente.jsp").forward(req, resp);
		}*/
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		
		Integer montantEnchereProposition = Integer.parseInt( req.getParameter("quantity"));

		if (formerUser.getNoUtilisateur() != user.getNoUtilisateur()){
			formerUser.setCredit(enchere.getMontantEnchere());
			mgrUser.updateUser(formerUser);
		}


		utilisateur.setCredit(utilisateur.getCredit() - montantEnchereProposition);
		mgrUser.updateUser(utilisateur);

		enchere.setMontantEnchere(montantEnchereProposition);
		enchere.setNoEncherisseur(utilisateur.getNoUtilisateur());
		article.setPrixVente(montantEnchereProposition);
		mgrArt.updateArticle(article, user, cat);
		try {
			mgr.updateEnchere(enchere, enchere.getNoEncherisseur(), enchere.getMontantEnchere());
		} catch (BLLException e) {
			throw new RuntimeException(e);
		}

		System.out.println("montant" + montantEnchereProposition);

		req.setAttribute("enchere", montantEnchereProposition);

		req.getRequestDispatcher("/WEB-INF/detailVente.jsp").forward(req, resp);
	}
}
