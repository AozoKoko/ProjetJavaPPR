package main.java.fr.eni.bll;

import java.time.LocalDate;

import main.java.fr.eni.bo.Enchere;
import main.java.fr.eni.dal.DAOFactory;
import main.java.fr.eni.dal.EnchereDAO;

public class ManagerEnchereImpl implements ManagerEnchere {

	private EnchereDAO enchereDAO;
	
	ManagerEnchereImpl(){
		enchereDAO = DAOFactory.getDaoEnchere();
	}
	
	@Override
	public void ajouterEnchere(Enchere enchere) throws BLLException {
		enchereDAO.insertEnchere(enchere);
	}

	@Override
	public void updateEnchere(Enchere enchere) throws BLLException {
		enchereDAO.updateEnchere(enchere);
		
	}

	@Override
	public void supprimerEnchere(Enchere enchere, LocalDate dateEnchere) throws BLLException {
		enchereDAO.removeEnchere(enchere, dateEnchere);
		
	}

}
