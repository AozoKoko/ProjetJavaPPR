package main.java.fr.eni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.fr.eni.bll.BLLException;
import main.java.fr.eni.bo.Utilisateur;

public class UtilisateursDAOImpl implements UtilisateursDAO {

		//Déclaration des constantes Strings de commandes SQL
		private static final String INSERT = "insert into UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		private static final String SELECT_ID = "SELECT * FROM USERS WHERE noUTILISATEUR = ?";
		private static final String VERIF_INFOS_USER = "SELECT username,password FROM USERS WHERE username = ? AND password = ?";
	
	@Override
	public void insert(Utilisateur user) throws BLLException {
		//Tentative de connexion à la base de donnée
		//Initialisation du prepared statement
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement stmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);	){
			//Valorisation des paramètres
			stmt.setString(1, user.getPseudo());
			stmt.setString(2,user.getNom());
			stmt.setString(3,user.getPrenom());
			stmt.setString(4, user.getEmail());
			stmt.setString(5, user.getTelephone());
			stmt.setString(6, user.getRue());
			stmt.setString(7, user.getCodePostal());
			stmt.setString(8, user.getVille());
			stmt.setString(9, user.getMotDePasse());
			stmt.setInt(10,100);
			stmt.setBoolean(11,user.isAdministrateur());

			//Execute le prepared statement pour insérer les données renseignées dans la base de donnée
			stmt.executeUpdate();

			//Récupère les données générées suite à l'insert
			ResultSet rs = stmt.getGeneratedKeys();

			//Met à jour le numéro utilisateur de l'objet user
			if(rs.next()){
				user.setNoUtilisateur(rs.getInt(1));
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

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

