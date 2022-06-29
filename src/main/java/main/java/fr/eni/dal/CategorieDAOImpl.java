package main.java.fr.eni.dal;

import main.java.fr.eni.bo.Categorie;

import java.sql.Connection;
import java.sql.SQLException;

public class CategorieDAOImpl {

    private static String INSERT = "INSERT INTO CATEGORIES (libelle) VALUES ?";

    public void insertCategorie(Categorie categorie){

        try (Connection conn = ConnectionProvider.getConnection()){

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
