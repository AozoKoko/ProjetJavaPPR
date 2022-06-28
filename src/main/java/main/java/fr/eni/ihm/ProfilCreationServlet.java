package main.java.fr.eni.ihm;

import java.awt.Button;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.java.fr.eni.bll.BLLException;
import main.java.fr.eni.bll.BLLFactory;
import main.java.fr.eni.bll.ManagerUtilisateurs;
import main.java.fr.eni.bo.Utilisateur;

@WebServlet("/creationCompte")
public class ProfilCreationServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private ManagerUtilisateurs mgr ;
	
	public ProfilCreationServlet() {
		mgr = BLLFactory.getUtilisateursManager();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/creationCompte.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Utilisateur user = new Utilisateur();
		Boolean inscription = false;
		user.setPseudo(req.getParameter("pseudo"));
		user.setNom(req.getParameter("nom"));
		user.setPrenom(req.getParameter("prenom"));
		user.setTelephone(req.getParameter("telephone"));
		user.setCodePostal(req.getParameter("codePostal"));
		user.setEmail(req.getParameter("email"));
		user.setRue(req.getParameter("rue"));
		user.setVille(req.getParameter("ville"));
		if (req.getParameter("password").equals(req.getParameter("passwordConfirm"))) {
			user.setMotDePasse(req.getParameter("password"));
		}else {
			
			req.getRequestDispatcher("/WEB-INF/creationCompte.jsp").forward(req, resp);
		}
		try {

			inscription = mgr.ajouterUser(user);
			if (inscription){
				req.getRequestDispatcher("/WEB-INF/pageAccueil.jsp").forward(req, resp);
			}else{
				req.getRequestDispatcher("/WEB-INF/creationCompte.jsp").forward(req, resp);
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}

	}
	
}
