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
	// rajout pour recup pseudo
	private String pseudo = null;
	private Articles article = new Articles();
	private Categorie cat = new Categorie();
	private Enchere enchere = new Enchere();
	private Utilisateur formerUser = new Utilisateur();

	public DetailVenteServlet() {
		mgr = BLLFactory.getEnchereManager();
		mgrUser = BLLFactory.getUtilisateursManager();
		mgrArt = BLLFactory.getArticlesManager();
		mgrCat = BLLFactory.getCategorieManager();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer idUser = null;
		// recup idUser et permet de lire la fiche du produit meme deconnecte
		String test = "";
		if (test.equals(req.getParameter("param1"))) {
			idUser = 0;
			req.setAttribute("disabled", idUser);
		} else {
			idUser = Integer.parseInt(req.getParameter("param1"));
		}

		// recup idArticle
		Integer idArticle = Integer.parseInt(req.getParameter("param2"));

		try {

			utilisateur = mgrUser.selectById(idUser);
			cat = mgrCat.selectById(idArticle);
			article = mgrArt.selectParId(idArticle);
			user = mgrUser.selectById(idArticle);
			enchere = mgr.getEnchereByNumArticle(idArticle);
			formerUser = mgrUser.selectById(enchere.getNoEncherisseur());
			// rajout pour recup pseudo
			pseudo = mgrArt.getPseudoByIdArticle(idArticle);
			req.setAttribute("user", user);
			// renvoi de la donnee dans la jsp
			req.setAttribute("enchere", enchere.getMontantEnchere());
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

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer montantEnchereProposition = Integer.parseInt(req.getParameter("quantity"));

		if (formerUser.getNoUtilisateur() != user.getNoUtilisateur()) {

			formerUser.setCredit(formerUser.getCredit() + enchere.getMontantEnchere());
			mgrUser.updateUser(formerUser);

		}

		if (utilisateur.getNoUtilisateur() == formerUser.getNoUtilisateur()) {
			try {

				utilisateur.setCredit(mgrUser.selectById(formerUser.getNoUtilisateur()).getCredit());
				mgrUser.updateUser(utilisateur);

			} catch (DALException e) {
				throw new RuntimeException(e);
			} catch (BLLException e) {
				throw new RuntimeException(e);
			}
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

		req.getRequestDispatcher("/pageAccueil").forward(req, resp);
	}
}
