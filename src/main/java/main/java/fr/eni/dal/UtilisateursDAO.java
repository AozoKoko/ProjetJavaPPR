package main.java.fr.eni.dal;

import java.util.List;

import main.java.fr.eni.bll.BLLException;
import main.java.fr.eni.bo.Utilisateur;

public interface UtilisateursDAO {
	Utilisateur insert (Utilisateur user) throws BLLException;
	List<Utilisateur> selectAll() throws BLLException;
	Utilisateur selectById(Integer id) throws DALException, BLLException;
	Utilisateur verifLogin(String pseudo, String motDePasse) throws DALException, BLLException;
}
