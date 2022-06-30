package main.java.fr.eni.bll;

import java.time.LocalDate;

import main.java.fr.eni.bo.Categorie;
import main.java.fr.eni.bo.Enchere;
import main.java.fr.eni.bo.Utilisateur;

public interface ManagerEnchere {

	void ajouterEnchere (Enchere enchere, Utilisateur utilisateur, Categorie categorie) throws BLLException;
	
	void updateEnchere(Enchere enchere, Utilisateur utilisateur, Categorie categorie) throws BLLException;
	
	void supprimerEnchere(Enchere enchere) throws BLLException;
}
