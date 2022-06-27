package main.java.fr.eni.bll;

import java.util.List;

import main.java.fr.eni.bo.Utilisateur;
import main.java.fr.eni.dal.DALException;

public interface ManagerUtilisateurs {

	 List<Utilisateur> selectAll() throws BLLException;
	 Utilisateur selectById(Integer id) throws DALException, BLLException;
	 Utilisateur getUser(String pseudo, String motDePasse) throws DALException, BLLException;

}
