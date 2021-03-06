package main.java.fr.eni.dal;

import java.util.List;

import main.java.fr.eni.bll.BLLException;
import main.java.fr.eni.bo.Enchere;
import main.java.fr.eni.bo.Utilisateur;

public interface EnchereDAO {
	
	Enchere selectById(int id);
	
	void removeEnchere (Enchere enchere)throws BLLException;

	void updateEnchere(Enchere enchere, int noUtilisateur, int nouveauMontant);

	void insertEnchere(Enchere enchere);

	List<Enchere> getEncheresByEncherisseur(Utilisateur user);

	Enchere getEnchereByNumArticle(int idArticle);
	
}
