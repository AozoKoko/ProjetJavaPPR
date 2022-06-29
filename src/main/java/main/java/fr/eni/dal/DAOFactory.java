package main.java.fr.eni.dal;

public class DAOFactory {

	public static UtilisateursDAO getDaoUtilisateurs() {
		return new UtilisateursDAOImpl();
	}

	public static CategorieDAO getDaoCategorie(){ return  new CategorieDAOImpl();}
	
	public static EnchereDAO getDaoEnchere() {
		return new EnchereDAOImpl();
	}

	public static ArticlesDAO getDaoArticles(){ return  new ArticlesDAOImpl();}
}
