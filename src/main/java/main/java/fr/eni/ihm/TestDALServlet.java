package main.java.fr.eni.ihm;

import main.java.fr.eni.bo.Categorie;
import main.java.fr.eni.dal.CategorieDAO;
import main.java.fr.eni.dal.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/testDal")
public class TestDALServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Hello");
        CategorieDAO categorieDAO = DAOFactory.getDaoCategorie();

        Categorie categorie = new Categorie("horreur");

        categorieDAO.deleteCategorie(categorie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
