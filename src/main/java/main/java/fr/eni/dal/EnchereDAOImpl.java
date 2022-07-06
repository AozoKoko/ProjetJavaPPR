package main.java.fr.eni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import main.java.fr.eni.bll.BLLException;
import main.java.fr.eni.bo.Articles;
import main.java.fr.eni.bo.Categorie;
import main.java.fr.eni.bo.Enchere;
import main.java.fr.eni.bo.Utilisateur;

public class EnchereDAOImpl implements EnchereDAO {
	
	// à faire : create select by article

	private static String SELECT_BY_ID = "SELECT * FROM ENCHERES WHERE no_enchere = ?";
	private static String INSERT = "INSERT INTO ENCHERES (date_enchere, montant_enchere, no_article, no_utilisateur, no_encherisseur) VALUES (?, ?, " + 
			"(SELECT no_article FROM ARTICLES_VENDUS WHERE no_article = ?)," +
			"(SELECT no_utilisateur FROM UTILISATEURS WHERE no_utilisateur = ?)," +            
			" ?) ";
	private static String UPDATE = "UPDATE ENCHERES SET date_enchere = ?, montant_enchere = ?, no_article = ?, no_utilisateur = ?, no_encherisseur = ? WHERE no_enchere = ?";
	private static String REMOVE = "DELETE FROM ENCHERES WHERE no_enchere = ?";

	private static String GET_USER = "SELECT * FROM ENCHERES WHERE no_encherisseur = ?";

	private static String GET_ENCHERE_BY_ID_ARTICLE = "SELECT * FROM ENCHERES e INNER JOIN UTILISATEURS u ON e.no_utilisateur = u.no_utilisateur INNER JOIN ARTICLES_VENDUS av ON e.no_article = av.no_article WHERE e.no_article = ?";
	
	 // Renvoie l'enchere en fonction de son id enchere
    public Enchere selectById (int id) {
    	
        Enchere enchere = null;
        //Déclaration de la connexion à la base SQL
        try ( Connection conn = ConnectionProvider.getConnection()) {

            //Décla de la commande SQL utilisée
            PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID, PreparedStatement.RETURN_GENERATED_KEYS);

            //Valorisation du param
            stmt.setInt(1,id);

            //Récup des résultats dans un resultset
            ResultSet rs =  stmt.executeQuery();

            //Vérif la présence de données dans le resultset
            if(rs.next()){
                //Création de l'enchere à renvoyer en cas de présence de données
                enchere = new Enchere (rs.getDate(1).toLocalDate(),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Renvoi de l'enchere
        return enchere;
    }
	  
	@Override
	public void insertEnchere (Enchere enchere) {
		 try (Connection conn = ConnectionProvider.getConnection()){

	            PreparedStatement stmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
	            
	            stmt.setDate(1,java.sql.Date.valueOf(enchere.getDateEnchere()));
	            stmt.setInt(2, enchere.getMontantEnchere());
	            stmt.setInt(3,enchere.getNoArticle());
	            stmt.setInt(4,enchere.getNoUtilisateur());
	            stmt.setInt(5,enchere.getNoEncherisseur());
	            
	            ResultSet rs =  stmt.executeQuery();

	            if(rs.next()){
	                enchere.setNoEnchere(rs.getInt(1));
	            }

	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
		}
	
	@Override
	public void updateEnchere (Enchere enchere, int noUtilisateur, int nouveauMontant) {


		
		try (Connection conn = ConnectionProvider.getConnection()){
	    	 
	            PreparedStatement stmt = conn.prepareStatement(UPDATE, PreparedStatement.RETURN_GENERATED_KEYS);

	            stmt.setDate(1,java.sql.Date.valueOf(enchere.getDateEnchere()));
	            stmt.setInt(2, nouveauMontant);
	            stmt.setInt(3,enchere.getNoArticle());
	            stmt.setInt(4,enchere.getNoUtilisateur());
	            stmt.setInt(5,noUtilisateur);
	            stmt.setInt(6,enchere.getNoEnchere());

	            stmt.executeUpdate();       
	            
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }

	@Override
	public void removeEnchere (Enchere enchere) throws BLLException {
	      try (Connection conn = ConnectionProvider.getConnection()){

	            PreparedStatement stmt = conn.prepareStatement(REMOVE, PreparedStatement.RETURN_GENERATED_KEYS);

	            stmt.setInt(1,enchere.getNoEnchere());

	            stmt.executeUpdate();

	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	}
	
	
		public List<Enchere> getEncheresByEncherisseur(Utilisateur user){
		List<Enchere> listeEnchere = null;
			try(Connection conn = ConnectionProvider.getConnection()) {

				PreparedStatement stmt = conn.prepareStatement(GET_USER, PreparedStatement.RETURN_GENERATED_KEYS);

				stmt.setInt(1,user.getNoUtilisateur());
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Enchere enchere = new Enchere(rs.getInt(1),
							rs.getDate(2).toLocalDate(),
							rs.getInt(3),
							rs.getInt(4),
							rs.getInt(5),
							rs.getInt(6));

					listeEnchere.add(enchere);
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

	return listeEnchere;
		}

	public Enchere getEnchereByNumArticle(int idArticle){
		Enchere enchere = null;
		try (Connection conn = ConnectionProvider.getConnection()){

			PreparedStatement stmt = conn.prepareStatement(GET_ENCHERE_BY_ID_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setInt(1,idArticle);

			ResultSet resultSet = stmt.executeQuery();

			if(resultSet.next()){
				Enchere encheres = new Enchere(resultSet.getInt(1),
						resultSet.getDate(2).toLocalDate(),
						resultSet.getInt(3),
						resultSet.getInt(4),
						resultSet.getInt(5),
						resultSet.getInt(6));

				enchere = encheres;
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return enchere;
	}
}
