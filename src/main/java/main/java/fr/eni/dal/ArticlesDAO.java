package main.java.fr.eni.dal;

import main.java.fr.eni.bo.Articles;
import main.java.fr.eni.bo.Categorie;
import main.java.fr.eni.bo.Utilisateur;

import java.util.List;

public interface ArticlesDAO {

    Articles selectById(int id);
    void insertArticles(Articles articles, Utilisateur utilisateur, Categorie categorie);

    void deleteArticle(int id);

    Articles updateArticle(Articles articles, Utilisateur utilisateur, Categorie categorie);

    List<Articles> getAllArticles(int typeObjet, int id);
}
