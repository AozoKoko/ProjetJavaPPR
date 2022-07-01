package main.java.fr.eni.dal;

import main.java.fr.eni.bo.Categorie;

import java.sql.*;

public class CategorieDAOImpl implements  CategorieDAO{

    private static String INSERT = "INSERT INTO CATEGORIES (libelle) VALUES (?)";

    private static String REMOVE = "DELETE FROM CATEGORIES WHERE no_categorie = ?";

    private static String UPDATE = "UPDATE CATEGORIES SET libelle = ? WHERE no_categorie = ?";

    private static String SELECT_BY_ID = "SELECT * FROM CATEGORIES WHERE no_categorie = ?";

    public void insertCategorie(Categorie categorie){

        try (Connection conn = ConnectionProvider.getConnection()){

            PreparedStatement stmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

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
        try (Connection conn = ConnectionProvider.getConnection()){

            PreparedStatement statement = conn.prepareStatement(REMOVE,PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setInt(1,categorie.getNoCategorie());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCategorie(Categorie categorie){
        try (Connection conn = ConnectionProvider.getConnection()){

            PreparedStatement statement = conn.prepareStatement(UPDATE, PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setString(1,categorie.getLibelle());
            statement.setInt(2,categorie.getNoCategorie());

            statement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Categorie selectById(int id){

        Categorie categorie = null;

        try (Connection conn = ConnectionProvider.getConnection()){

            PreparedStatement statement = conn.prepareStatement(SELECT_BY_ID,PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()){
                categorie = new Categorie(rs.getInt("no_categorie"),rs.getString("libelle") );

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return categorie;
    }

}
