package main.java.fr.eni.bll;

import java.time.LocalDate;

import main.java.fr.eni.bo.Categorie;
import main.java.fr.eni.bo.Enchere;
import main.java.fr.eni.bo.Utilisateur;
import main.java.fr.eni.dal.DAOFactory;
import main.java.fr.eni.dal.EnchereDAO;

public class ManagerEnchereImpl implements ManagerEnchere {

	private EnchereDAO enchereDAO;
	
	ManagerEnchereImpl(){
		enchereDAO = DAOFactory.getDaoEnchere();
	}
	
	@Override
	public void ajouterEnchere(Enchere enchere, Utilisateur utilisateur, Categorie categorie) throws BLLException {
		enchereDAO.insertEnchere(enchere, utilisateur, categorie);
	}

	@Override
	public void updateEnchere(Enchere enchere, Utilisateur utilisateur, Categorie categorie) throws BLLException {
		enchereDAO.updateEnchere(enchere, utilisateur, categorie);
		
	}

	@Override
	public void supprimerEnchere(Enchere enchere) throws BLLException {
		enchereDAO.removeEnchere(enchere);
		
	}

}
