package main.java.fr.eni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.fr.eni.bll.BLLException;
import main.java.fr.eni.bo.Utilisateur;

public class UtilisateursDAOImpl implements UtilisateursDAO {

		//Déclaration des constantes Strings de commandes SQL
		private static final String VERIF_INSERT = "SELECT pseudo FROM UTILISATEURS WHERE pseudo = ?";
		private static final String INSERT = "insert into UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		private static final String SELECT_ID = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = ?";
		private static final String VERIF_INFOS_USER = "SELECT pseudo, mot_de_passe FROM UTILISATEURS WHERE pseudo = ? AND mot_de_passe = ?";


	//Fonction permettant l'insertion de nouveaux utilisateurs dans la base de donnée
	@Override
	public void insert(Utilisateur user) throws BLLException {

		//Tentative de connexion à la base de donnée

		try (Connection conn = ConnectionProvider.getConnection();
			 //Initialisation du prepared statement
				PreparedStatement stmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
				PreparedStatement prStmt = conn.prepareStatement(VERIF_INSERT,PreparedStatement.RETURN_GENERATED_KEYS)){

			prStmt.setString(1,user.getPseudo());

			ResultSet resultSet = prStmt.getGeneratedKeys();

			if (!resultSet.next()){
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
			}



		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	//Fonction permettant la sélection d'un utilisateur dans la base de données en se basant sur son numéro utilisateur
	@Override
	public Utilisateur selectById(Integer id) throws DALException, BLLException {

		//Création de l'objet utilisateur utilisé pour stocker le résultat de la query
		Utilisateur user = new Utilisateur();

		try (Connection conn = ConnectionProvider.getConnection()){

			//Création du prepared statement
			PreparedStatement stmt = conn.prepareStatement(SELECT_ID, PreparedStatement.RETURN_GENERATED_KEYS);

			//Valorisation des paramètres
			stmt.setInt(1,id);

			//Execution de la requète
			stmt.executeQuery();

			//Récupération du résultat de la requète
			ResultSet rs = stmt.getGeneratedKeys();

			//Mise à jour des paramètres de l'objet Utilisateur avec les résultats de la requète
			if (rs.next()){
				user.setNoUtilisateur(rs.getInt(1));
				user.setPseudo(rs.getString(2));
				user.setNom(rs.getString(3));
				user.setPrenom(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.setTelephone(rs.getString(6));
				user.setRue(rs.getString(7));
				user.setCodePostal(rs.getString(8));
				user.setVille(rs.getString(9));
				user.setMotDePasse(rs.getString(10));
				user.setCredit(rs.getInt(11));
				user.setAdminsitrateur(rs.getBoolean(12));
			}


		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return user;
	}


	//Fonction servant à vérifier si l'utilisateur existe dans la base de données
	@Override
	public Boolean verifLogin(String pseudo, String motDePasse) throws DALException, BLLException {
		//Déclaration de la boolean à renvoyer
		Boolean userExist = false;

		try (Connection conn = ConnectionProvider.getConnection()){
			//Déclaration du prepared statement
			PreparedStatement stmt = conn.prepareStatement(VERIF_INFOS_USER, PreparedStatement.RETURN_GENERATED_KEYS);

			//Valorisation des paramètres
			stmt.setString(1,pseudo);
			stmt.setString(2,motDePasse);
			//Récupération des résultats de la requète Execution de la requète
			 ResultSet rs = stmt.executeQuery();

			 //Mise à jour de la boolean si l'utilisateur existe
			 if (rs.next()){
				 userExist = true;
			 }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return userExist;
	}
}

