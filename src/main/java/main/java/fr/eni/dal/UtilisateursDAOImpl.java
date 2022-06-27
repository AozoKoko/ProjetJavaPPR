package main.java.fr.eni.dal;

import java.util.List;

import main.java.fr.eni.bll.BLLException;
import main.java.fr.eni.bo.Utilisateur;

public class UtilisateursDAOImpl implements UtilisateursDAO {
	
		private static final String INSERT = "insert into UTILISATEURS (nom,, )" + " values (?, ?, ?, ?)";
		private static final String SELECT_ALL = "SELECT * FROM UTILISATEURS";
		private static final String SELECT_ID = "SELECT * FROM USERS WHERE noUTILISATEUR = ?";
		private static final String SELECT_INFOS_USER = "SELECT username,password FROM USERS WHERE username = ? AND password = ?";
	
	@Override
	public List<Utilisateur> selectAll() throws BLLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Utilisateur selectById(Integer id) throws DALException, BLLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Utilisateur getUser(String pseudo, String motDePasse) throws DALException, BLLException {
		// TODO Auto-generated method stub
		return null;
	}

}
