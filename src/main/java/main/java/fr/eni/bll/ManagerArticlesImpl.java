package main.java.fr.eni.bll;

import main.java.fr.eni.bo.Articles;
import main.java.fr.eni.bo.Categorie;
import main.java.fr.eni.bo.Utilisateur;
import main.java.fr.eni.dal.ArticlesDAO;
import main.java.fr.eni.dal.DAOFactory;

import java.util.List;

public class ManagerArticlesImpl implements ManagerArticles{

    private ArticlesDAO articlesDAO;

    public ManagerArticlesImpl(){ articlesDAO = DAOFactory.getDaoArticles();}

    public Articles selectParId(int id){
        Articles articles = articlesDAO.selectById(id);
        return articles;
    }

    public void insertArticles(Articles articles, Utilisateur utilisateur, Categorie categorie){
        articlesDAO.insertArticles(articles,utilisateur,categorie);
    }

    public void deleteArticle(int id){
        articlesDAO.deleteArticle(id);
    }

    public Articles updateArticle(Articles articles, Utilisateur utilisateur, Categorie categorie){
        Articles articles1 = articlesDAO.updateArticle(articles,utilisateur,categorie);
        return articles1;
    }

    public List<Articles> getAllArticles(int typeObjet, int id){
        List<Articles> liste = articlesDAO.getAllArticles(typeObjet, id);

        return liste;
    }
}
