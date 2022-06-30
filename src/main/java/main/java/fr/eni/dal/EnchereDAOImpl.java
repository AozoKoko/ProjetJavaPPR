package main.java.fr.eni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import main.java.fr.eni.bll.BLLException;
import main.java.fr.eni.bo.Articles;
import main.java.fr.eni.bo.Categorie;
import main.java.fr.eni.bo.Enchere;
import main.java.fr.eni.bo.Utilisateur;

public class EnchereDAOImpl implements EnchereDAO {
	

	private static String INSERT = "INSERT INTO ENCHERE (date_enchere, montant_enchere, no_article, no_utilisateur) VALUES (?, ?, ?, ?)";
	private static String UPDATE = "UPDATE ENCHERE SET date_enchere ?, montant_enchere = ?, no_article = ?, no_utilisateur = ?) WHERE date_enchere = ?, montant_enchere = ?, no_article = ?, no_utilisateur = ?";
	private static String REMOVE = "DELETE FROM ENCHERE WHERE date_enchere = ?";
	  
	  
	@Override
	public void insertEnchere (Enchere enchere, Utilisateur utilisateur, Categorie categorie) {
		 try (Connection conn = ConnectionProvider.getConnection()){

	            PreparedStatement stmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
	            
	            stmt.setDate(1,java.sql.Date.valueOf(enchere.getDateEnchere()));
	            stmt.setInt(2,enchere.getMontantEnchere());
	            stmt.setInt(3,utilisateur.getNoUtilisateur());
	            stmt.setInt(4,categorie.getNoCategorie());
	            
	            
	            //Execute le prepared statement pour insérer les données renseignées dans la base de donnée
	            stmt.executeUpdate();

	            //Récupère les données générées suite à l'insert
	            ResultSet rs = stmt.getGeneratedKeys();

	            if(rs.next()){
	                enchere.setNoEnchere(rs.getInt(1));
	            }

	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
		}
	
	@Override
	public Enchere updateEnchere (Enchere enchere, Utilisateur utilisateur, Categorie categorie) {
		Enchere enchere1 = null;
		
		try (Connection conn = ConnectionProvider.getConnection()){
	    	 
	            PreparedStatement stmt = conn.prepareStatement(UPDATE, PreparedStatement.RETURN_GENERATED_KEYS);

	            stmt.setDate(1,java.sql.Date.valueOf(enchere1.getDateEnchere()));
	            stmt.setInt(2,enchere1.getMontantEnchere());
	            stmt.setInt(3,utilisateur.getNoUtilisateur());
	            stmt.setInt(4,categorie.getNoCategorie());

	            stmt.executeUpdate();

	          //Récup des données
	            ResultSet rs = stmt.getGeneratedKeys();
	            
	            if(rs.next()){
	            	enchere1 = new Enchere(rs.getInt(1),
	                        rs.getDate(2).toLocalDate(),
	                        rs.getInt(3));
	            }
	            
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	     	return enchere1;
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
	
	


	
}
