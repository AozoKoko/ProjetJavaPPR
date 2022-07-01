package main.java.fr.eni.dal;

import main.java.fr.eni.bo.Retraits;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RetraitsDAOImpl implements RetraitsDAO{

    private final String INSERT = "INSERT INTO RETRAITS (no_article,rue, code_postal, ville) VALUES ((SELECT no_article FROM ARTICLE_VENDUS WHERE no_article = ?), ?, ?, ?) ";

    private final String GET_BY_ID = "SELECT * FROM RETRAITS WHERE no_article = ?";

    private final String UPDATE = "UPDATE RETRAITS  SET no_article = ?, rue = ?, code_postal = ?, ville = ? WHERE no_article = ?";
    public void insert(Retraits retraits){

        try ( Connection conn = ConnectionProvider.getConnection();){

            PreparedStatement stmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, retraits.getNoArticle());
            stmt.setString(2,retraits.getRue());
            stmt.setString(3,retraits.getCodePostal());
            stmt.setString(4,retraits.getVille());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Retraits selectById (int id){
        Retraits retraits = null;
        try (Connection conn = ConnectionProvider.getConnection();){
            PreparedStatement stmt = conn.prepareStatement(GET_BY_ID, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1,id);

            ResultSet rs = stmt.executeQuery();

            retraits = new Retraits(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return retraits;
    }

    public Retraits updateRetraits(Retraits retraits){
        Retraits retraits1 = null;
        try ( Connection conn = ConnectionProvider.getConnection();) {

            PreparedStatement stmt = conn.prepareStatement(UPDATE, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setInt(1,retraits.getNoArticle());
            stmt.setString(2,retraits.getRue());
            stmt.setString(3,retraits.getCodePostal());
            stmt.setString(4,retraits.getVille());
            stmt.setInt(5,retraits.getNoArticle());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            retraits1 = new Retraits(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return retraits1;
    }
}
