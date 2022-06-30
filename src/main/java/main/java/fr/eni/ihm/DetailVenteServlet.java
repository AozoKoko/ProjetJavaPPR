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

	public DetailVenteServlet () {
		mgr = BLLFactory.getEnchereManager();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	/*	Enchere enchere = new Enchere();
		
		Integer idEnchere = Integer.parseInt(req.getParameter("enchere1"));
		String article = req.getParameter("article");
		String description = req.getParameter("description");	
		
		
		
		try {
			enchere = mgr.updateEnchere(enchere, utilisateur, categorie);
			req.setAttribute("credit", enchere);
			
			if (==null) {
				req.setAttribute("", );
			}else {
				req.setAttribute("", );
			}
			
		} catch (DALException e) {
			e.printStackTrace();
			
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		*/
		req.getRequestDispatcher("/WEB-INF/pageAccueil.jsp").forward(req, resp);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
		
		
		req.getRequestDispatcher("/WEB-INF/pageAccueil.jsp").forward(req, resp);
	}

}
