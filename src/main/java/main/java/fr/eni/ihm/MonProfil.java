package main.java.fr.eni.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.fr.eni.bll.BLLFactory;
import main.java.fr.eni.bll.ManagerUtilisateurs;

/**
 * Servlet implementation class MonProfil
 */
@WebServlet("/monProfil")
public class MonProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManagerUtilisateurs mgr;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonProfil() {
    	mgr = BLLFactory.getUtilisateursManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/modifProfil.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*if (req.getParameter("delete").equals("delete")) {
			mgr.deleteUser(user);
		}else if (req.getParameter("delete").equals("save")) {
			mgr.updateUser(user);
		}*/
		req.getRequestDispatcher("/WEB-INF/pageAccueil.jsp").forward(req, resp);
	}

}
