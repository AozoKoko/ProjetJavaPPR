package main.java.fr.eni.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import main.java.fr.eni.bll.BLLException;
import main.java.fr.eni.bo.Enchere;

public class EnchereDAOImpl implements EnchereDAO {
	
	  private static String INSERT = "INSERT INTO ENCHERE (dateEnchere) VALUES (?)";
	  private static String UPDATE = "UPDATE ENCHERE SET montantEnchere = ? WHERE dateEnchere = ?";
	  private static String REMOVE = "DELETE FROM ENCHERE WHERE dateEnchere = ?";
	  
	  
	@Override
	public void insertEnchere (Enchere enchere) {
		 try (Connection conn = ConnectionProvider.getConnection()){

	            PreparedStatement stmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

	            stmt.setDate(1, java.sql.Date.valueOf(enchere.getDateEnchere()));

	            //Execute le prepared statement pour insérer les données renseignées dans la base de donnée
	            stmt.executeUpdate();

	            //Récupère les données générées suite à l'insert
	            ResultSet rs = stmt.getGeneratedKeys();

	            if(rs.next()){
	                enchere.setMontantEnchere(rs.getInt(1));
	            }

	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	}

	@Override
	public void updateEnchere (Enchere enchere) {
	     try (Connection conn = ConnectionProvider.getConnection()){
	    	 
	            PreparedStatement stmt = conn.prepareStatement(UPDATE, PreparedStatement.RETURN_GENERATED_KEYS);

	            stmt.setDate(1,java.sql.Date.valueOf(enchere.getDateEnchere()));
	            stmt.setInt(2,enchere.getMontantEnchere());

	            stmt.executeUpdate();

	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }

	@Override
	public void removeEnchere (Enchere enchere, LocalDate dateEnchere) throws BLLException {
	      try (Connection conn = ConnectionProvider.getConnection()){

	            PreparedStatement statement = conn.prepareStatement(REMOVE,PreparedStatement.RETURN_GENERATED_KEYS);

	            statement.setInt(1,enchere.getMontantEnchere());

	            statement.executeUpdate();

	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
		
	}


	
}
