package main.java.fr.eni.bll;

public class BLLFactory {

    public static ManagerUtilisateurs getUtilisateursManager() {
        return new ManagerUtilisateursImpl();
    }
    
    public static ManagerArticles getArticlesManager(){
    	return new ManagerArticlesImpl();
	}

    public static ManagerCategorie getCategorieManager(){
    	return new ManagerCategorieImpl();
	}

	public static ManagerEnchere getEnchereManager() {
		return new ManagerEnchereImpl();
	}
}