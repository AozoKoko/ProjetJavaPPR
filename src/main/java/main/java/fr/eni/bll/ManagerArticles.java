package main.java.fr.eni.bll;

import main.java.fr.eni.bo.Articles;
import main.java.fr.eni.bo.Categorie;
import main.java.fr.eni.bo.Enchere;
import main.java.fr.eni.bo.Utilisateur;

import java.util.List;

public interface ManagerArticles {
    Articles selectParId(int id);
    void insertArticles(Articles articles, Utilisateur utilisateur, Categorie categorie);

    void deleteArticle(int id);

    Articles updateArticle(Articles articles, Utilisateur utilisateur, Categorie categorie);

    List<Articles> getAllArticles(int typeObjet, int id);

    List<Articles> getAllEncheresOpen();

    List<Articles> getAllUserEnchere(Utilisateur user, Enchere enchere);

    List<Articles> getAllUserEnchereRemportees(Utilisateur user, List<Enchere> enchere);

    List<Articles> getAllVentesUtilisateur(Utilisateur user, int mode);
	String getPseudoByIdArticle(Integer idArticle);

    List<Articles> getArticleByName(String name);

    Articles getObjectArticleByName(String name);
}
