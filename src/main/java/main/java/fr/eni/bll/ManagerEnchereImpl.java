package main.java.fr.eni.bll;

import java.time.LocalDate;
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
	public void updateEnchere(Enchere enchere, int noUtilisateur, int nouveauMontant) throws BLLException {
		enchereDAO.updateEnchere(enchere, noUtilisateur, nouveauMontant);
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

	public  Enchere getEnchereByNumArticle(int idArticle){
		return enchereDAO.getEnchereByNumArticle(idArticle);
	}

	

}
