package main.java.fr.eni.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.fr.eni.bll.BLLFactory;
import main.java.fr.eni.bll.ManagerUtilisateurs;
import main.java.fr.eni.bo.Utilisateur;

/**
 * Servlet implementation class UserProfilServlet
 */
@WebServlet("/userProfil")
public class UserProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManagerUtilisateurs mgr ;
	
	public UserProfilServlet() {
		mgr = BLLFactory.getUtilisateursManager();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Utilisateur user = new Utilisateur();
		String pseudo = req.getParameter("param");
		user = mgr.selectUserByPseudo(pseudo);
		req.setAttribute("profil", user);
		
		req.getRequestDispatcher("/WEB-INF/userProfile.jsp").forward(req, resp);
	}

}
