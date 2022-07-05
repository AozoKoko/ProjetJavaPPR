package main.java.fr.eni.bll;

import java.util.List;

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
	public Enchere selectById(int id) throws BLLException {
		Enchere enchere = enchereDAO.selectById(id);
		return enchere;
	}
	
	@Override
	public void ajouterEnchere(Enchere enchere) throws BLLException {
		enchereDAO.insertEnchere(enchere);
	}

	@Override
	public Enchere updateEnchere(Enchere enchere, int noUtilisateur, int nouveauMontant) throws BLLException {
		Enchere enchere1 = enchereDAO.updateEnchere(enchere, noUtilisateur, nouveauMontant);
		 return enchere1;
	}

	@Override
	public void supprimerEnchere(Enchere enchere) throws BLLException {
		enchereDAO.removeEnchere(enchere);
		
	}

	public List<Enchere> getEncheresByEncherisseur(Utilisateur user){
		List<Enchere> list;
		list = enchereDAO.getEncheresByEncherisseur(user);
		return list;
	}

	

}
