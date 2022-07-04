package main.java.fr.eni.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.fr.eni.bll.BLLException;
import main.java.fr.eni.bll.BLLFactory;
import main.java.fr.eni.bll.ManagerEnchere;
import main.java.fr.eni.bll.ManagerUtilisateurs;
import main.java.fr.eni.bo.Enchere;
import main.java.fr.eni.bo.Utilisateur;
import main.java.fr.eni.dal.DALException;

@WebServlet("/detailVente")
public class DetailVenteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ManagerEnchere mgr;
	private ManagerUtilisateurs mgr2;

	public DetailVenteServlet () {
		mgr = BLLFactory.getEnchereManager();
		mgr2 = BLLFactory.getUtilisateursManager();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Utilisateur user = new Utilisateur();
		Enchere enchere = new Enchere();
		
		// recup idUser
		Integer idUser= Integer.parseInt(req.getParameter("param1"));
		System.out.println("idUserSuiteBtnEncherir " + idUser);
		
		// recup idArticle
		Integer idArticle = Integer.parseInt(req.getParameter("param2"));
		System.out.println("idArticleSuiteBtnEncherir " + idArticle);
		
		// recup montant de la derniere enchere (montantEnchere)
		Integer montantEnchere = Integer.parseInt(req.getParameter("montantEnchere"));
		
		// recup credit de l'utilisateur de la session
		try {
			user = mgr2.selectById(idUser);
			Integer creditUser = user.getCredit();
			System.out.println("creditUserDebut" + creditUser);
			
	//		enchere = mgr.ajouterEnchere(enchere);
			Integer montantEnchereDebut = enchere.getMontantEnchere();
			System.out.println("montantEnchereDebut" + montantEnchereDebut);
			
			if (creditUser<=montantEnchere) {
				System.out.println("");
			}
			
			
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	//	req.getRequestDispatcher("/WEB-INF/detailVente.jsp").forward(req, resp);

	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	/*	
		try {
			// recup des parametres du formulaire (new proposition d'enchère)
			int montantEnchere = (Integer.parseInt(req.getParameter("nouveauMontant")));
			
			if (credit <= prixVente) {
				req.setAttribute("encherir", enchere);
			}else {
				req.setAttribute("", enchere);
			}
		
		} catch (DALException e) {
			e.printStackTrace();
			
		} catch (BLLException e) {
			e.printStackTrace();
		}
				
	*/ 
		req.getRequestDispatcher("/WEB-INF/pageAccueil.jsp").forward(req, resp);
	}

}
