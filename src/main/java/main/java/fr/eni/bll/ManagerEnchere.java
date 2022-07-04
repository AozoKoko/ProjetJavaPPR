package main.java.fr.eni.bll;

import java.time.LocalDate;
import java.util.List;

import main.java.fr.eni.bo.Categorie;
import main.java.fr.eni.bo.Enchere;
import main.java.fr.eni.bo.Utilisateur;

public interface ManagerEnchere {

	Enchere selectById(int id) throws BLLException;
	
	void supprimerEnchere(Enchere enchere) throws BLLException;

	Enchere updateEnchere(Enchere enchere, int noUtlisateur, int nouveauMontant) throws BLLException;

	void ajouterEnchere(Enchere enchere) throws BLLException;

	List<Enchere> getEncheresByEncherisseur(Utilisateur user);
}
