package main.java.fr.eni.dal;

import main.java.fr.eni.bo.Articles;
import main.java.fr.eni.bo.Categorie;
import main.java.fr.eni.bo.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticlesDAOImpl implements ArticlesDAO{

    private static String SELECT_BY_ID = "SELECT * FROM ARTICLES_VENDUS WHERE no_article = ?";
    private static String INSERT = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, url_image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
    private static String DELETE = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";
    private static String UPDATE =  "UPDATE ARTICLES_VENDUS SET nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, prix_vente = ?, no_utilisateur = ?, no_categorie = ?, url_image = ? WHERE no_article = ?";
    private static String GET_ALL_ARTICLE_UTILISATEUR = "SELECT * FROM ARTICLES_VENDUS av INNER JOIN UTILISATEURS u ON av.no_utilisateur = u.no_utilisateur WHERE u.no_utilisateur = ?";
    private static String GET_ALL_ARTICLE_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS av INNER JOIN CATEGORIES c ON av.no_categorie = c.no_categorie WHERE c.no_categorie = ? ";
    private static String GET_ALL_ARTICLE = "SELECT * FROM ARTICLES_VENDUS";

    public Articles selectById(int id){
        Articles articles = null;
        try ( Connection conn = ConnectionProvider.getConnection()) {

            PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setInt(1,id);

            ResultSet rs =  stmt.executeQuery();

            if(rs.next()){
                articles = new Articles(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4).toLocalDate(),
                        rs.getDate(5).toLocalDate(),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(10));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return articles;
    }

    public void insertArticles(Articles articles, Utilisateur utilisateur, Categorie categorie){
        try (Connection conn = ConnectionProvider.getConnection()){

            PreparedStatement stmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setString(1,articles.getNomArticle());
            stmt.setString(2, articles.getDescription());
            stmt.setDate(3,java.sql.Date.valueOf(articles.getDateDebutEncheres()));
            stmt.setDate(4,java.sql.Date.valueOf(articles.getDateFinEncheres()));
            stmt.setInt(5,articles.getMiseAPrix());
            stmt.setInt(6,articles.getPrixVente());
            stmt.setInt(7,utilisateur.getNoUtilisateur());
            stmt.setInt(8,categorie.getNoCategorie());
            stmt.setString(9, articles.getUrlImage());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()){
                articles.setNoArticle(rs.getInt(1));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteArticle(int id){

        try (Connection conn = ConnectionProvider.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(DELETE, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1,id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public Articles updateArticle(Articles articles, Utilisateur utilisateur, Categorie categorie){

        Articles articled = null;
        try (Connection conn = ConnectionProvider.getConnection()){

            PreparedStatement stmt = conn.prepareStatement(UPDATE,PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setString(1, articles.getNomArticle());
            stmt.setString(2,articles.getDescription());
            stmt.setDate(3,java.sql.Date.valueOf(articles.getDateDebutEncheres()));
            stmt.setDate(4,java.sql.Date.valueOf(articles.getDateFinEncheres()));
            stmt.setInt(5,articles.getMiseAPrix());
            stmt.setInt(6,articles.getPrixVente());
            stmt.setInt(7,utilisateur.getNoUtilisateur());
            stmt.setInt(8,categorie.getNoCategorie());
            stmt.setString(9, articles.getUrlImage());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()){
                articled = new Articles(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4).toLocalDate(),
                        rs.getDate(5).toLocalDate(),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(10));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return articled;
    }

    public List<Articles> getAllArticles(int typeObjet,int id){

        List<Articles> liste = new ArrayList<Articles>();
        try (Connection conn = ConnectionProvider.getConnection()){
            if (typeObjet == 0){
                Statement stmt = conn.createStatement();

                ResultSet rs = stmt.executeQuery(GET_ALL_ARTICLE);

                while (rs.next()){

                    Articles articles = new Articles(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getDate(4).toLocalDate(),
                            rs.getDate(5).toLocalDate(),
                            rs.getInt(6),
                            rs.getInt(7),
                            rs.getString(10));

                    liste.add(articles);
                }
            }

            if (typeObjet == 1){
                PreparedStatement stmt = conn.prepareStatement(GET_ALL_ARTICLE_CATEGORIE,PreparedStatement.RETURN_GENERATED_KEYS);

                stmt.setInt(1, id);

                ResultSet rs = stmt.executeQuery();

                while (rs.next()){

                    Articles articles = new Articles(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getDate(4).toLocalDate(),
                            rs.getDate(5).toLocalDate(),
                            rs.getInt(6),
                            rs.getInt(7),
                            rs.getString(10));

                    liste.add(articles);
                }
            }

            if (typeObjet == 3){
                PreparedStatement stmt = conn.prepareStatement(GET_ALL_ARTICLE_UTILISATEUR,PreparedStatement.RETURN_GENERATED_KEYS);

                stmt.setInt(1, id);

                ResultSet rs = stmt.executeQuery();
                while (rs.next()){

                    Articles articles = new Articles(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getDate(4).toLocalDate(),
                            rs.getDate(5).toLocalDate(),
                            rs.getInt(6),
                            rs.getInt(7),
                            rs.getString(10));

                    liste.add(articles);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return liste;
    }
}
