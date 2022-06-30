package main.java.fr.eni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import main.java.fr.eni.bll.BLLException;
import main.java.fr.eni.bo.Utilisateur;

public class UtilisateursDAOImpl implements UtilisateursDAO {

		//Déclaration des constantes Strings de commandes SQL
		private static final String VERIF_INSERT = "SELECT pseudo FROM UTILISATEURS WHERE pseudo = ?";
		private static final String INSERT = "insert into UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		private static final String SELECT_ID = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = ?";
		private static final String VERIF_INFOS_USER = "SELECT no_utilisateur, pseudo,email, mot_de_passe FROM UTILISATEURS WHERE pseudo = ? OR email = ? AND mot_de_passe = ?";		
		private static final String DELETE_USER = "DELETE FROM UTILISATEURS WHERE no_utilisateur = ?";

		private static final String UPDATE_USER = "UPDATE UTILISATEURS SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ?, credit = ? WHERE  no_utilisateur = ?";
	//Fonction permettant l'insertion de nouveaux utilisateurs dans la base de donnée
	@Override
	public Boolean insert(Utilisateur user) throws BLLException {

		Boolean inscriptionReussie = false;
		//Tentative de connexion à la base de donnée
	
		try (Connection conn = ConnectionProvider.getConnection();
			 //Initialisation du prepared statement
				PreparedStatement stmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
				PreparedStatement prStmt = conn.prepareStatement(VERIF_INSERT,PreparedStatement.RETURN_GENERATED_KEYS)){

			prStmt.setString(1,user.getPseudo());

			ResultSet resultSet = prStmt.executeQuery();
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

				inscriptionReussie = true;
			}
		



		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return inscriptionReussie;
	}

	//Fonction permettant la sélection d'un utilisateur dans la base de données en se basant sur son numéro utilisateur
	@Override
	public Utilisateur selectById(Integer id) throws DALException, BLLException {

		//Création de l'objet utilisateur utilisé pour stocker le résultat de la query
		Utilisateur user = null;

		try (Connection conn = ConnectionProvider.getConnection();){

			//Création du prepared statement
			PreparedStatement stmt = conn.prepareStatement(SELECT_ID, PreparedStatement.RETURN_GENERATED_KEYS);

			//Valorisation des paramètres
			stmt.setInt(1,id);

			//Execution de la requète

			//Récupération du résultat de la requète
			ResultSet rs =  stmt.executeQuery();
		
			//Mise à jour des paramètres de l'objet Utilisateur avec les résultats de la requète
			if (rs.next()){
				user = new Utilisateur(rs.getInt("no_utilisateur"),rs.getString("pseudo"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),
						rs.getString("telephone"),rs.getString("rue"),rs.getString("code_postal"),rs.getString("ville"),rs.getString("mot_de_passe"),
						rs.getInt("credit"),rs.getBoolean("administrateur"));
			}


		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return user;
	}

	//Fonction servant à vérifier si l'utilisateur existe dans la base de données
	@Override
	public Integer verifLogin(String pseudo, String motDePasse) throws DALException, BLLException {
		//Déclaration de la boolean à renvoyer
		Integer userExist = null;

		try (Connection conn = ConnectionProvider.getConnection()){
			//Déclaration du prepared statement
			PreparedStatement stmt = conn.prepareStatement(VERIF_INFOS_USER, PreparedStatement.RETURN_GENERATED_KEYS);

			//Valorisation des paramètres
			stmt.setString(1,pseudo);
			stmt.setString(2, pseudo);
			stmt.setString(3,motDePasse);
			//Récupération des résultats de la requète Execution de la requète
			 ResultSet rs = stmt.executeQuery();

			 //Mise à jour de la boolean si l'utilisateur existe
			 if (rs.next()){
				 userExist = rs.getInt(1);
			 }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return userExist;
	}


	//Fonction permettant la suppression d'un utilisateur
	public void deleteUser(Utilisateur user){

		//Essai de la connexion
		try (Connection conn = ConnectionProvider.getConnection()){

			//Création du prepared statement
			PreparedStatement stmt = conn.prepareStatement(DELETE_USER,PreparedStatement.RETURN_GENERATED_KEYS);

			//Valorisation des paramètres
			stmt.setInt(1,user.getNoUtilisateur());

			//Execution du prepared Statement
			stmt.executeUpdate();


		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//Fonction permettant la mise à jour d'un profil utilisateur
	public void updateUser(Utilisateur user){

		//Essai de la connexion
		try (Connection conn = ConnectionProvider.getConnection()){

			//Création du prepared statement
			PreparedStatement stmt = conn.prepareStatement(UPDATE_USER,PreparedStatement.RETURN_GENERATED_KEYS);

			//Valorisation des paramètres
			
			stmt.setString(1,user.getPseudo());
			stmt.setString(2,user.getNom());
			stmt.setString(3,user.getPrenom());
			stmt.setString(4,user.getEmail());
			stmt.setString(5,user.getTelephone());
			stmt.setString(6,user.getRue());
			stmt.setString(7,user.getCodePostal());
			stmt.setString(8,user.getVille());
			stmt.setString(9,user.getMotDePasse());
			stmt.setInt(10,user.getCredit());
			stmt.setInt(11,user.getNoUtilisateur());

			//Execution du prepared statement
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Utilisateur> getAllUsers(){
		List<Utilisateur> listeUtilisateurs = new ArrayList<Utilisateur>();

		return listeUtilisateurs;
	}
}

