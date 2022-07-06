package main.java.fr.eni.bll;

import main.java.fr.eni.bo.Articles;
import main.java.fr.eni.bo.Categorie;
import main.java.fr.eni.bo.Enchere;
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

    public List<Articles> getAllEncheresOpen(){
        List<Articles> liste = articlesDAO.getAllEncheresOuvertes();

        return liste;
    }

    public List<Articles> getAllUserEnchere(Utilisateur user, Enchere enchere){
        List<Articles> liste = articlesDAO.getAllUtilisateurEnchere(user, enchere);

        return liste;
    }

    public List<Articles> getAllUserEnchereRemportees(Utilisateur user, List<Enchere> enchere){
        List<Articles> liste = articlesDAO.getAllUtilisateurEnchereRemportees(user, enchere);
        return liste;
    }

    public List<Articles> getAllVentesUtilisateur(Utilisateur user, int mode){
        List<Articles> liste = articlesDAO.getAllVentesUser(user, mode);

        return liste;
    }
    //rajout pour recup psueod par id article
	@Override
	public String getPseudoByIdArticle(Integer idArticle) {
		String pseudo = articlesDAO.getUserByIdArticle(idArticle);
		return pseudo;
	}
}
