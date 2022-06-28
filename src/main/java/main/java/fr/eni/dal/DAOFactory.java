package main.java.fr.eni.dal;

public class DAOFactory {

	public static UtilisateursDAO getDaoUtilisateurs() {
		return new UtilisateursDAOImpl();
	}

}
