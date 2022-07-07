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
 * Servlet implementation class MonProfil
 */
@WebServlet("/monProfil")
public class MonProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManagerUtilisateurs mgr;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonProfilServlet() {
    	mgr = BLLFactory.getUtilisateursManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Utilisateur user = new Utilisateur();
		Integer idUser = Integer.parseInt(req.getParameter("param1"));
		try {
			user = mgr.selectById(idUser);
			req.setAttribute("profil", user);
			req.getRequestDispatcher("/WEB-INF/modifProfil.jsp").forward(req, resp);
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
		Utilisateur user = new Utilisateur();
		Integer idUser = Integer.parseInt(req.getParameter("idUtilisateur"));
	
		if (req.getParameter("buttonFunction").equals("delete")) {
			try {
				user = mgr.selectById(idUser);
				mgr.deleteUser(user);
				req.getRequestDispatcher("/pageAccueil").forward(req, resp);
			} catch (DALException e) {
				e.printStackTrace();
			} catch (BLLException e) {
				e.printStackTrace();
			}
		}
		if (req.getParameter("buttonFunction").equals("save")) {
			Utilisateur userModify = new Utilisateur(idUser,req.getParameter("pseudo"),req.getParameter("nom"),req.getParameter("prenom"),
					req.getParameter("email"),req.getParameter("telephone"),req.getParameter("rue"),req.getParameter("codePostal"),
					req.getParameter("ville"),req.getParameter("password"));
			
			mgr.updateUser(userModify);
			req.getRequestDispatcher("/pageAccueil").forward(req, resp);
		}
	}

}
