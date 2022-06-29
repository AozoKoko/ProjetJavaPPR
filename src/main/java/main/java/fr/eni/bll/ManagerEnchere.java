package main.java.fr.eni.bll;

import java.time.LocalDate;

import main.java.fr.eni.bo.Enchere;

public interface ManagerEnchere {

	void ajouterEnchere (Enchere enchere) throws BLLException;
	
	void updateEnchere (Enchere enchere) throws BLLException;
	
	void supprimerEnchere (Enchere enchere, LocalDate dateEnchere)throws BLLException;
	
}
