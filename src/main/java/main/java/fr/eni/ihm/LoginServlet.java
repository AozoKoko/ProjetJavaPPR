package main.java.fr.eni.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.fr.eni.bll.BLLException;
import main.java.fr.eni.bll.BLLFactory;
import main.java.fr.eni.bll.ManagerUtilisateurs;
import main.java.fr.eni.dal.DALException;

@WebServlet({"/login"})
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private ManagerUtilisateurs mgr ;
	
	public LoginServlet() {
		mgr = BLLFactory.getUtilisateursManager();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer idUser= null;
		boolean fail = false;
		String pseudo = req.getParameter("pseudo");
		String password = req.getParameter("password");
			
		System.out.println("pseudo   " + pseudo);
		System.out.println("password   " + password);
		HttpSession session = req.getSession();
		try {
			idUser = mgr.verifLogin(pseudo, password);
			System.out.println("iduser   " + idUser);
			if (idUser != null) {
				// creation exprression language variable mode connecte pour utiliser une seule page accueil
				session.setAttribute("modeConnecte", idUser);
				this.getServletContext().getRequestDispatcher("/pageAccueil").forward(req, resp);
			}else {
				req.setAttribute("modeConnecte", idUser);
				fail = true;
				req.setAttribute("fail", fail);
				req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
			}
		} catch (DALException e) {
			e.printStackTrace();
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
	}
	

}
