package main.java.fr.eni.bll;

import main.java.fr.eni.bo.Categorie;
import main.java.fr.eni.dal.CategorieDAO;
import main.java.fr.eni.dal.DAOFactory;

public class ManagerCategorieImpl implements  ManagerCategorie{

    private CategorieDAO categorieDAO;

    public ManagerCategorieImpl() {
        categorieDAO = DAOFactory.getDaoCategorie();
    }

    public void ajouterCategorie(Categorie categorie){
         categorieDAO.insertCategorie(categorie);
    }

    public void supprimerCategorie(Categorie categorie){
        categorieDAO.deleteCategorie(categorie);
    }

    public void updateCategorie(Categorie categorie){
        categorieDAO.updateCategorie(categorie);
    }

    public Categorie selectById(int id){
        Categorie categorie = categorieDAO.selectById(id);

        return categorie;
    }
}
