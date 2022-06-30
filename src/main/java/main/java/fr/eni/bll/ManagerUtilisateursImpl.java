package main.java.fr.eni.bll;



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
		public Boolean ajouterUser(Utilisateur user) throws BLLException {
			return daoUtilisateurs.insert(user);
		}

		@Override
		public Utilisateur selectById(Integer id) throws DALException, BLLException {
			return daoUtilisateurs.selectById(id);
		}

		@Override
		public Integer verifLogin(String pseudo, String motDePasse) throws DALException, BLLException {

			return daoUtilisateurs.verifLogin(pseudo,motDePasse);
		}

		public void deleteUser(Utilisateur user){
			 daoUtilisateurs.deleteUser(user);
		}

		public void updateUser(Utilisateur user){
			daoUtilisateurs.updateUser(user);
		}
}
