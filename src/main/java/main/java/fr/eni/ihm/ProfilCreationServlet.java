package main.java.fr.eni.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.fr.eni.bo.Utilisateur;

@WebServlet("/creationCompte")
public class ProfilCreationServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/creationCompte.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Utilisateur user=null;
		user.setPseudo(req.getParameter("pseudo"));
		
		System.out.printf("pseudo  verif ",user.getPseudo());
		req.getRequestDispatcher("/WEB-INF/creationCompte.jsp").forward(req, resp);
	}
}
