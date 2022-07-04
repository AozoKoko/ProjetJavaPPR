package main.java.fr.eni.bll;

import java.time.LocalDate;

import main.java.fr.eni.bo.Categorie;
import main.java.fr.eni.bo.Enchere;
import main.java.fr.eni.bo.Utilisateur;

public interface ManagerEnchere {

	void supprimerEnchere(Enchere enchere) throws BLLException;

	void updateEnchere(Enchere enchere, int noUtlisateur, int nouveauMontant) throws BLLException;

	void ajouterEnchere(Enchere enchere) throws BLLException;
}
