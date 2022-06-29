package main.java.fr.eni.dal;

import main.java.fr.eni.bo.Categorie;

import java.sql.*;

public class CategorieDAOImpl {

    private static String INSERT = "INSERT INTO CATEGORIES (libelle) VALUES (?)";

    public void insertCategorie(Categorie categorie){

        try (Connection conn = ConnectionProvider.getConnection()){

            PreparedStatement stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1,categorie.getLibelle());

            //Execute le prepared statement pour insérer les données renseignées dans la base de donnée
            stmt.executeUpdate();

            //Récupère les données générées suite à l'insert
            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                categorie.setNoCategorie(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCategorie(Categorie categorie){

    }

}
