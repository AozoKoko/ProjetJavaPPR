package main.java.fr.eni.dal;

import main.java.fr.eni.bo.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArticlesDAOImpl implements ArticlesDAO{

    private static String SELECT_BY_ID = "SELECT * FROM ARTICLES_VENDUS WHERE no_article = ?";
    private static String INSERT = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, url_image) VALUES (?, ?, ?, ?, ?, ?, " +
            "(SELECT no_utilisateur FROM UTILISATEURS WHERE no_utilisateur = ?)," +
            "(SELECT no_categorie FROM CATEGORIES WHERE no_categorie = ?)," +
            " ?) ";

    private static String DELETE = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";
    private static String UPDATE =  "UPDATE ARTICLES_VENDUS SET nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, prix_vente = ?, no_utilisateur = ?, no_categorie = ?, url_image = ? WHERE no_article = ?";

    private static String GET_ALL_ARTICLE_UTILISATEUR = "SELECT * FROM ARTICLES_VENDUS av INNER JOIN UTILISATEURS u ON av.no_utilisateur = u.no_utilisateur WHERE u.no_utilisateur = ?";
    private static String GET_ALL_ARTICLE_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS av INNER JOIN CATEGORIES c ON av.no_categorie = c.no_categorie WHERE c.no_categorie = ? ";
    private static String GET_ALL_ARTICLE = "SELECT * FROM ARTICLES_VENDUS";
    //rajout pour afficher le detenteur de l'article
    private static String GET_PSEUDO_BY_ARTICLE ="SELECT pseudo FROM ARTICLES_VENDUS av INNER JOIN UTILISATEURS u ON av.no_utilisateur = u.no_utilisateur WHERE av.no_article = ?";
    private static  String GET_ARTICLE_BY_NAME = "SELECT * FROM ARTICLES_VENDUS av INNER JOIN CATEGORIES c ON av.no_categorie = c.no_categorie WHERE nom_article LIKE ?";

    //rajout fonction pour recuperer lepseudo de l'artticle
    public String getUserByIdArticle(int idArticle) {
    	String pseudo = null;
    	try ( Connection conn = ConnectionProvider.getConnection()) {

            //D??claration de la commande SQL utilis??e
            PreparedStatement stmt = conn.prepareStatement(GET_PSEUDO_BY_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);

            //Valorisation du param??tre
            stmt.setInt(1,idArticle);

            //R??cup??ration des r??sultats dans un resultset
            ResultSet rs =  stmt.executeQuery();

            //V??rifie la pr??sence de donn??es dans le resultset
            if(rs.next()){
                pseudo = rs.getString(1);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    	System.out.println("pseudo en sortie de dal  "+pseudo);
    	return pseudo;
    }
    
    //Fonction utilis??e pour renvoyer un aricle en fonction de son article
    public Articles selectById(int id){
        Articles articles = null;
        //D??claration de la connexion ?? la base SQL
        try ( Connection conn = ConnectionProvider.getConnection()) {

            //D??claration de la commande SQL utilis??e
            PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID, PreparedStatement.RETURN_GENERATED_KEYS);

            //Valorisation du param??tre
            stmt.setInt(1,id);

            //R??cup??ration des r??sultats dans un resultset
            ResultSet rs =  stmt.executeQuery();

            //V??rifie la pr??sence de donn??es dans le resultset
            if(rs.next()){
                //Cr??ation de l'article ?? renvoyer en cas de pr??sence de donn??es
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
        //Renvoi de l'article
        return articles;
    }

    //Fonction permettant l'insert d'un article dans la base de donn??e SQL
    public void insertArticles(Articles articles, Utilisateur utilisateur, Categorie categorie){
        //Tentative de connexion ?? la base de donn??es SQL
        try (Connection conn = ConnectionProvider.getConnection()){
            //D??clatation du statement utilis?? pour effectuer l'insert dans la base SQL
            PreparedStatement stmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

            //Valorisation des param??tres de la requ??te
            stmt.setString(1,articles.getNomArticle());
            stmt.setString(2, articles.getDescription());
            stmt.setDate(3,java.sql.Date.valueOf(articles.getDateDebutEncheres()));
            stmt.setDate(4,java.sql.Date.valueOf(articles.getDateFinEncheres()));
            stmt.setInt(5,articles.getMiseAPrix());
            stmt.setInt(6,articles.getPrixVente());
            stmt.setInt(7,utilisateur.getNoUtilisateur());
            stmt.setInt(8,categorie.getNoCategorie());
            stmt.setString(9, articles.getUrlImage());

            //Execution de la requ??te
            stmt.executeUpdate();

            //R??cup??ration de l'objet g??n??r?? par la requ??te
            ResultSet rs = stmt.getGeneratedKeys();

            //Si l'objet a ??t?? cr??e correctement
            if (rs.next()){
                //Attribue le num??ro article g??n??r?? dans la base SQL ?? l'objet Articles
                articles.setNoArticle(rs.getInt(1));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //Fonction permettant la suppression d'un article de la base de donn??e SQL
    public void deleteArticle(int id){

        //Tentative de connexion ?? la base de donn??e SQL
        try (Connection conn = ConnectionProvider.getConnection()){
            //D??claration du statement utilis?? pour la suppression de donn??e dans la base SQL
            PreparedStatement stmt = conn.prepareStatement(DELETE, PreparedStatement.RETURN_GENERATED_KEYS);

            //Valorisation du param??tre
            stmt.setInt(1,id);

            //Execution de la mise ?? jour de la base de donn??e
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    //Fonction permettant la mise ?? jour d'un article dans la base de donn??e SQL
    public void updateArticle(Articles articles, Utilisateur utilisateur, Categorie categorie){

        //Tentative de connexion ?? la base de donn??e SQL
        try (Connection conn = ConnectionProvider.getConnection()){

            //D??claration du statement utilis?? pour la mise ?? jour des donn??es dans la base SQL
            PreparedStatement stmt = conn.prepareStatement(UPDATE,PreparedStatement.RETURN_GENERATED_KEYS);

            //Valorisation des param??tres de la requ??te
            stmt.setString(1, articles.getNomArticle());
            stmt.setString(2,articles.getDescription());
            stmt.setDate(3,java.sql.Date.valueOf(articles.getDateDebutEncheres()));
            stmt.setDate(4,java.sql.Date.valueOf(articles.getDateFinEncheres()));
            stmt.setInt(5,articles.getMiseAPrix());
            stmt.setInt(6,articles.getPrixVente());
            stmt.setInt(7,utilisateur.getNoUtilisateur());
            stmt.setInt(8,categorie.getNoCategorie());
            stmt.setString(9, articles.getUrlImage());
            stmt.setInt(10,articles.getNoArticle());

            //Execution de la mise ?? jour en base de donn??e
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param typeObjet Utilis?? pour d??terminer le type de requ??te ?? utiliser
     *                  0 renvoie tous les articles existants dans la table
     *                  1 renvoie tous les objets dont l'id cat??gorie correspond ?? l'id rentr??
     *                  2 renvoie tous les objets dont l'id utilisateur correspond ?? l'id rentr??
     * @param id Utilis?? pour d??t??rminer quel ??l??ment aller chercher dans la base de donn??e, dans le cas o??
     *           l'utilisateur veut tous les objets, ce param??tres n'est pas utilis?? et peut donc ??tre
     *           renseign?? d'un 0
     * @return
     */
    public List<Articles> getAllArticles(int typeObjet,int id){

        List<Articles> liste = new ArrayList<Articles>();

        //Tentative de connexion ?? la base de donn??e SQL
        try (Connection conn = ConnectionProvider.getConnection()){
            //Si l'utilisateur veut renvoyer tous les articles
            if (typeObjet == 0){
                //Cr??ation du statetement utilis?? pour la requ??te SQL
                Statement stmt = conn.createStatement();

                //Execution de la requ??te
                ResultSet rs = stmt.executeQuery(GET_ALL_ARTICLE);

                //Si il existe des objets dans la base de donn??e
                while (rs.next()){

                    //Cr??ation d'un objet article et valorisation des param??tres n??cessaire ?? sa cr??ation
                    Articles articles = new Articles(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getDate(4).toLocalDate(),
                            rs.getDate(5).toLocalDate(),
                            rs.getInt(6),
                            rs.getInt(7),
                            rs.getString(10));
                    //Ajout d'un article dans la liste renvoy??e par la fonction
                    liste.add(articles);
                }
            }

            //Si l'utilisateur veut renvoyer tous les objets d'une certaine cat??gorie
            if (typeObjet == 1){
                //Cr??ation du statement utilis?? pour la requ??te SQL
                PreparedStatement stmt = conn.prepareStatement(GET_ALL_ARTICLE_CATEGORIE,PreparedStatement.RETURN_GENERATED_KEYS);

                //Valorisation des param??tres de la requ??te
                stmt.setInt(1, id);

                //Execution de la requ??te et stockage des donn??es renvoy??es dans un resultset
                ResultSet rs = stmt.executeQuery();

                //Si la cat??gorie pross??de des articles associ??s
                while (rs.next()){

                    //Cr??e un article par objet dans la base de donn??e
                    Articles articles = new Articles(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getDate(4).toLocalDate(),
                            rs.getDate(5).toLocalDate(),
                            rs.getInt(6),
                            rs.getInt(7),
                            rs.getString(10));
                    //Stock l'article cr??e dans la liste renvoy??e ?? l'utilisateur
                    liste.add(articles);
                }
            }

            //Si l'utilisateur souhaite renvoyer tous les articles li??s ?? un utilisateur
            if (typeObjet == 2){
                //Cr??ation du statement utilis?? pour la requ??te SQL
                PreparedStatement stmt = conn.prepareStatement(GET_ALL_ARTICLE_UTILISATEUR,PreparedStatement.RETURN_GENERATED_KEYS);

                //Valorisation des param??tres de la requ??te
                stmt.setInt(1, id);

                //Execution de la requ??te et stockage des donn??es renvoy??es dans un resultset
                ResultSet rs = stmt.executeQuery();

                //Si la cat??gorie pross??de des articles associ??s
                while (rs.next()){

                    //Cr??e un article par objet dans la base de donn??e
                    Articles articles = new Articles(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getDate(4).toLocalDate(),
                            rs.getDate(5).toLocalDate(),
                            rs.getInt(6),
                            rs.getInt(7),
                            rs.getString(10));
                    //Stock l'article cr??e dans la liste renvoy??e ?? l'utilisateur
                    liste.add(articles);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return liste;
    }


    //Fonction permettant la r??cup??ration de tous les ench??res en cours au moment de la requ??te
    public List<Articles> getAllEncheresOuvertes(){

        List<Articles> liste = new ArrayList<Articles>();
        //Tentative de connexion ?? la base de donn??e SQL
        try (Connection conn = ConnectionProvider.getConnection()){

                //Cr??ation du statement utilis?? pour la requ??te
                Statement stmt = conn.createStatement();

                //Execution de la requ??te et stockage des donn??es dans un resulset
                ResultSet rs = stmt.executeQuery(GET_ALL_ARTICLE);

                //Cr??ation de la variable permettant de v??rifier la date actuelle
                 LocalDate date =  LocalDate.now();

                 //V??rification de l'existence des donn??es dans le resultset
                while (rs.next()){

                    //Cr??ation dun objet article en fonction des donn??es r??cup??r??es dans le resultset
                    Articles articles = new Articles(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getDate(4).toLocalDate(),
                            rs.getDate(5).toLocalDate(),
                            rs.getInt(6),
                            rs.getInt(7),
                            rs.getString(10));

                    //V??rification que l'ench??re n'est pas termin??e
                    if ( date.isBefore(articles.getDateFinEncheres())){
                        //Ajout de l'article ?? la liste renvoy??e ?? l'utilisateur
                        liste.add(articles);
                    }

                }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return liste;
    }

    //Fonction renvoyant une liste contenant tous les objets dans une ench??re li??e ?? un utilisateur
    public List<Articles> getAllUtilisateurEnchere(Utilisateur user, Enchere enchere){
        List<Articles> liste = new ArrayList<Articles>();

        //Tentative de connexion ?? la base de donn??e SQL
        try (Connection conn = ConnectionProvider.getConnection()){

            //Cr??ation du statement utilis?? pour la requ??te SQL
            Statement stmt = conn.createStatement();

            //Execution du statement et stockage des donn??es dans un resultset
            ResultSet rs = stmt.executeQuery(GET_ALL_ARTICLE);


            //V??rification de l'existence de donn??es dans le resultset
            while (rs.next()){
                //Cr??ation d'un article ?? partir des donn??es r??cup??r??es
                Articles articles = new Articles(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4).toLocalDate(),
                        rs.getDate(5).toLocalDate(),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(10));

                //V??rification de l'existence des donn??es
                    if(user.getNoUtilisateur() == enchere.getNoEncherisseur() && enchere.getNoArticle() == articles.getNoArticle()){
                        //ajout de l'article ?? la liste renvoy??e ?? l'utilisateur
                        liste.add(articles);
                    }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return liste;
    }


    //Fonction renvoyant tous les articles issus d'une ench??re remport??e par l'utilisateur
    public List<Articles> getAllUtilisateurEnchereRemportees(Utilisateur user, List<Enchere> listeEnchere){
        List<Articles> liste = new ArrayList<Articles>();

        //Tentative de connexion ?? la base de donn??e SQL
        try (Connection conn = ConnectionProvider.getConnection()){

            //Cr??ation du statement utilis?? pour la requ??te SQL
            Statement stmt = conn.createStatement();

            //Execution du statement et stockage des donn??es dans un resultset
            ResultSet rs = stmt.executeQuery(GET_ALL_ARTICLE);

            //Cr??ation de la variable permettant de v??rifier la date actuelle
            LocalDate date =  LocalDate.now();

            //V??rification de l'existence de donn??es dans le resultset
            while (rs.next()){

                //Cr??ation d'un article ?? partir des donn??es r??cup??r??es
                Articles articles = new Articles(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4).toLocalDate(),
                        rs.getDate(5).toLocalDate(),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(10));

                //V??rification pour savoir si l'utilisateur est le dernier encherisseur, que l'article correspond bien ?? l'article encherri et que l'ench??re est toujours en cours
                for (int i = 0 ; i < listeEnchere.size(); i++) {
                    if(user.getNoUtilisateur() == listeEnchere.get(i).getNoEncherisseur()){
                        if ( listeEnchere.get(i).getNoArticle() == articles.getNoArticle() && date.isAfter(articles.getDateFinEncheres())){
                            liste.add(articles);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return liste;
    }

    /**
     *
     * @param user Utilisateur donnant ses param??tres utilis??s dans la requ??te
     * @param mode param??tre utilis?? pour d??terminer le trype de requ??te ?? utiliser
     *             0 renvoie tous les articles li??es ?? une vente en cours de l'utilisateur
     *             1 renvoie tous les articles dont la vente n'a pas encore commenc??
     *             2 renvoie tous les articles dont la vente est termin??e
     * @return
     */
    public List<Articles> getAllVentesUser(Utilisateur user, int mode){
        List<Articles> liste = new ArrayList<Articles>();

        //Tentative de connexion ?? la base de donn??e SQL
        try (Connection conn = ConnectionProvider.getConnection()){

            //Cr??ation du statement utilis?? pour la requ??te SQL
            PreparedStatement stmt = conn.prepareStatement(GET_ALL_ARTICLE_UTILISATEUR,PreparedStatement.RETURN_GENERATED_KEYS);

            //Valorisation des param??tres de la requ??te SQL
            stmt.setInt(1, user.getNoUtilisateur());

            //Execution du statement et stockage des donn??es dans un resultset
            ResultSet rs = stmt.executeQuery();

            //Cr??ation de la variable permettant de v??rifier la date actuelle
            LocalDate date =  LocalDate.now();

            //V??rification de l'existence de donn??es dans le resultset
            while (rs.next()){

                //Cr??ation d'un article ?? partir des donn??es r??cup??r??es
                Articles articles = new Articles(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4).toLocalDate(),
                        rs.getDate(5).toLocalDate(),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(10));

                //Si l'utilisateur souhaite r??cup??rer les ventes en cours
                if (mode == 0 && date.isBefore(articles.getDateFinEncheres()) && date.isAfter(articles.getDateDebutEncheres())){
                    liste.add(articles);
                }
                //Si l'utilisateur sougaite r??cup??rer les vente non commenc??es
                else if (mode == 1 && date.isBefore(articles.getDateDebutEncheres())) {
                    liste.add(articles);
                }
                //Si l'utilisateur souhaite r??cup??rer les ventes termin??es
                else if (mode == 2 && date.isAfter(articles.getDateFinEncheres())) {
                    liste.add(articles);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return liste;
    }


    public List<Articles> getArticleByName(String name){
        List<Articles> liste = new ArrayList<Articles>();

        try (Connection conn = ConnectionProvider.getConnection()){
          PreparedStatement stmt = conn.prepareStatement(GET_ARTICLE_BY_NAME, PreparedStatement.RETURN_GENERATED_KEYS);

          stmt.setString(1,"%" + name + "%");

          ResultSet rs = stmt.executeQuery();

            while (rs.next()){

                //Cr??e un article par objet dans la base de donn??e
                Articles articles = new Articles(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4).toLocalDate(),
                        rs.getDate(5).toLocalDate(),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(10));
                //Stock l'article cr??e dans la liste renvoy??e ?? l'utilisateur
                liste.add(articles);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return liste;
    }
    public Articles getObjectArticleByName(String name){
        Articles articles = null;

        try (Connection conn = ConnectionProvider.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(GET_ARTICLE_BY_NAME, PreparedStatement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, "%" + name + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){

                //Cr??e un article par objet dans la base de donn??e
                Articles article = new Articles(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4).toLocalDate(),
                        rs.getDate(5).toLocalDate(),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(10));
                //Stock l'article cr??e dans la liste renvoy??e ?? l'utilisateur
                articles = article;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return articles;
    }

}
