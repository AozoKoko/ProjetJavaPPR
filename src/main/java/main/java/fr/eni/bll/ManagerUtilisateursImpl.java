package main.java.fr.eni.bll;

import java.util.List;

import main.java.fr.eni.bo.Utilisateur;
import main.java.fr.eni.dal.DALException;
import main.java.fr.eni.dal.DAOFactory;
import main.java.fr.eni.dal.UtilisateursDAO;

public class ManagerUtilisateursImpl implements ManagerUtilisateurs {

	 private UtilisateursDAO daoUtilisateurs;
	 
	    public ManagerUtilisateursImpl() {
	        daoUtilisateurs = DAOFactory.getDaoUtilisateurs();
	    }
	    
		@Override
		public Utilisateur ajouterUser(Utilisateur user) throws BLLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Utilisateur selectById(Integer id) throws DALException, BLLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Utilisateur verifLogin(String pseudo, String motDePasse) throws DALException, BLLException {
			// TODO Auto-generated method stub
			return null;
		}


}
