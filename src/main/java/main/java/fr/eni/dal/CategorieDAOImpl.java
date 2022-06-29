package main.java.fr.eni.dal;

import main.java.fr.eni.bo.Categorie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CategorieDAOImpl {

    private static String INSERT = "INSERT INTO CATEGORIES (libelle) VALUES (?)";

    public void insertCategorie(Categorie categorie){

        try (Connection conn = ConnectionProvider.getConnection()){

            PreparedStatement stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1,categorie.getLibelle());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
