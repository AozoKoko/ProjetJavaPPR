package main.java.fr.eni.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.fr.eni.bll.BLLException;
import main.java.fr.eni.bll.BLLFactory;
import main.java.fr.eni.bll.ManagerUtilisateurs;
import main.java.fr.eni.bo.Utilisateur;
import main.java.fr.eni.dal.DALException;

/**
 * Servlet implementation class modifProfil
 */
@WebServlet("/modifProfil")
public class modifProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManagerUtilisateurs mgr;

	public modifProfil() {
		mgr = BLLFactory.getUtilisateursManager();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer idUser = null;
		Utilisateur user = new Utilisateur();
		idUser = Integer.parseInt(req.getParameter("modeConnecte"));
		req.setAttribute("modeConnecte", idUser);
		System.out.println("iduser en cours   "+ idUser);
		
		try {
			user = mgr.selectById(idUser);
			System.out.println("user selectionner   " + user.toString());
			req.setAttribute("profil", user);
			req.getRequestDispatcher("/WEB-INF/affichageProfil.jsp").forward(req, resp);
		} catch (DALException e) {
			e.printStackTrace();
		} catch (BLLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}

}
