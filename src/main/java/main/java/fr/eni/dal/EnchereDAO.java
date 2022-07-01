package main.java.fr.eni.dal;

import java.time.LocalDate;

import main.java.fr.eni.bll.BLLException;
import main.java.fr.eni.bo.Categorie;
import main.java.fr.eni.bo.Enchere;
import main.java.fr.eni.bo.Utilisateur;

public interface EnchereDAO {
	
	void removeEnchere (Enchere enchere)throws BLLException;

	Enchere updateEnchere(Enchere enchere, int noUtilisateur, int nouveauMontant);

	void insertEnchere(Enchere enchere);
	
}
