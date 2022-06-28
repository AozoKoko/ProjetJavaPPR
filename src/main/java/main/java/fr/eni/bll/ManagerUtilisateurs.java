package main.java.fr.eni.bll;

import java.util.List;

import main.java.fr.eni.bo.Utilisateur;
import main.java.fr.eni.dal.DALException;

public interface ManagerUtilisateurs {

	Utilisateur ajouterUser (Utilisateur user) throws BLLException;
	Utilisateur selectById(Integer id) throws DALException, BLLException;
	Utilisateur verifLogin(String pseudo, String motDePasse) throws DALException, BLLException;

}
