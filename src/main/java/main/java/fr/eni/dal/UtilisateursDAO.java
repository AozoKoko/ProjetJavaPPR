package main.java.fr.eni.dal;

import main.java.fr.eni.bll.BLLException;
import main.java.fr.eni.bo.Utilisateur;

public interface UtilisateursDAO {
	void insert (Utilisateur user) throws BLLException;
	Utilisateur selectById(Integer id) throws DALException, BLLException;
	Boolean verifLogin(String pseudo, String motDePasse) throws DALException, BLLException;
}
