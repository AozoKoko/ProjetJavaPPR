package main.java.fr.eni.bll;

public class BLLFactory {

    public static ManagerUtilisateurs getUtilisateursManager() {
        return new ManagerUtilisateursImpl();
    }

    public static ManagerCategorie getCategorieManager(){
    	return new ManagerCategorieImpl();
    }
}
