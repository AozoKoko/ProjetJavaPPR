package main.java.fr.eni.dal;

import main.java.fr.eni.bo.Articles;
import main.java.fr.eni.bo.Categorie;
import main.java.fr.eni.bo.Enchere;
import main.java.fr.eni.bo.Utilisateur;

import java.util.List;

public interface ArticlesDAO {

    Articles selectById(int id);
    void insertArticles(Articles articles, Utilisateur utilisateur, Categorie categorie);

    void deleteArticle(int id);

    Articles updateArticle(Articles articles, Utilisateur utilisateur, Categorie categorie);

    List<Articles> getAllArticles(int typeObjet, int id);

    List<Articles> getAllEncheresOuvertes();

    List<Articles> getAllUtilisateurEnchere(Utilisateur user, Enchere enchere);

    List<Articles> getAllUtilisateurEnchereRemportees(Utilisateur user, List<Enchere> listeEnchere);

    List<Articles> getAllVentesUser(Utilisateur user, int mode);
    //rajout de lamethode non implementee
    String getUserByIdArticle(int idArticle);

    List<Articles> getArticleByName(String name);
}
