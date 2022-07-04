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


    //Fonction utilisée pour renvoyer un aricle en fonction de son article
    public Articles selectById(int id){
        Articles articles = null;
        //Déclaration de la connexion à la base SQL
        try ( Connection conn = ConnectionProvider.getConnection()) {

            //Déclaration de la commande SQL utilisée
            PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID, PreparedStatement.RETURN_GENERATED_KEYS);

            //Valorisation du paramètre
            stmt.setInt(1,id);

            //Récupération des résultats dans un resultset
            ResultSet rs =  stmt.executeQuery();

            //Vérifie la présence de données dans le resultset
            if(rs.next()){
                //Création de l'article à renvoyer en cas de présence de données
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

    //Fonction permettant l'insert d'un article dans la base de donnée SQL
    public void insertArticles(Articles articles, Utilisateur utilisateur, Categorie categorie){
        //Tentative de connexion à la base de données SQL
        try (Connection conn = ConnectionProvider.getConnection()){
            //Déclatation du statement utilisé pour effectuer l'insert dans la base SQL
            PreparedStatement stmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

            //Valorisation des paramètres de la requête
            stmt.setString(1,articles.getNomArticle());
            stmt.setString(2, articles.getDescription());
            stmt.setDate(3,java.sql.Date.valueOf(articles.getDateDebutEncheres()));
            stmt.setDate(4,java.sql.Date.valueOf(articles.getDateFinEncheres()));
            stmt.setInt(5,articles.getMiseAPrix());
            stmt.setInt(6,articles.getPrixVente());
            stmt.setInt(7,utilisateur.getNoUtilisateur());
            stmt.setInt(8,categorie.getNoCategorie());
            stmt.setString(9, articles.getUrlImage());

            //Execution de la requête
            stmt.executeUpdate();

            //Récupération de l'objet généré par la requête
            ResultSet rs = stmt.getGeneratedKeys();

            //Si l'objet a été crée correctement
            if (rs.next()){
                //Attribue le numéro article généré dans la base SQL à l'objet Articles
                articles.setNoArticle(rs.getInt(1));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //Fonction permettant la suppression d'un article de la base de donnée SQL
    public void deleteArticle(int id){

        //Tentative de connexion à la base de donnée SQL
        try (Connection conn = ConnectionProvider.getConnection()){
            //Déclaration du statement utilisé pour la suppression de donnée dans la base SQL
            PreparedStatement stmt = conn.prepareStatement(DELETE, PreparedStatement.RETURN_GENERATED_KEYS);

            //Valorisation du paramètre
            stmt.setInt(1,id);

            //Execution de la mise à jour de la base de donnée
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    //Fonction permettant la mise à jour d'un article dans la base de donnée SQL
    public Articles updateArticle(Articles articles, Utilisateur utilisateur, Categorie categorie){

        Articles articled = null;

        //Tentative de connexion à la base de donnée SQL
        try (Connection conn = ConnectionProvider.getConnection()){

            //Déclaration du statement utilisé pour la mise à jour des données dans la base SQL
            PreparedStatement stmt = conn.prepareStatement(UPDATE,PreparedStatement.RETURN_GENERATED_KEYS);

            //Valorisation des paramètres de la requête
            stmt.setString(1, articles.getNomArticle());
            stmt.setString(2,articles.getDescription());
            stmt.setDate(3,java.sql.Date.valueOf(articles.getDateDebutEncheres()));
            stmt.setDate(4,java.sql.Date.valueOf(articles.getDateFinEncheres()));
            stmt.setInt(5,articles.getMiseAPrix());
            stmt.setInt(6,articles.getPrixVente());
            stmt.setInt(7,utilisateur.getNoUtilisateur());
            stmt.setInt(8,categorie.getNoCategorie());
            stmt.setString(9, articles.getUrlImage());

            //Execution de la mise à jour en base de donnée
            stmt.executeUpdate();

            //Récupération de l'objet midifié en base de donnée
            ResultSet rs = stmt.getGeneratedKeys();

            //Vérification que l'objet modifié existe bien
            if (rs.next()){
                //Création de l'article à renvoyer à partir du resultset généré
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

    /**
     *
     * @param typeObjet Utilisé pour déterminer le type de requète à utiliser
     *                  0 renvoie tous les articles existants dans la table
     *                  1 renvoie tous les objets dont l'id catégorie correspond à l'id rentré
     *                  2 renvoie tous les objets dont l'id utilisateur correspond à l'id rentré
     * @param id Utilisé pour détérminer quel élément aller chercher dans la base de donnée, dans le cas où
     *           l'utilisateur veut tous les objets, ce paramêtres n'est pas utilisé et peut donc être
     *           renseigné d'un 0
     * @return
     */
    public List<Articles> getAllArticles(int typeObjet,int id){

        List<Articles> liste = new ArrayList<Articles>();

        //Tentative de connexion à la base de donnée SQL
        try (Connection conn = ConnectionProvider.getConnection()){
            //Si l'utilisateur veut renvoyer tous les articles
            if (typeObjet == 0){
                //Création du statetement utilisé pour la requète SQL
                Statement stmt = conn.createStatement();

                //Execution de la requète
                ResultSet rs = stmt.executeQuery(GET_ALL_ARTICLE);

                //Si il existe des objets dans la base de donnée
                while (rs.next()){

                    //Création d'un objet article et valorisation des paramètres nécessaire à sa création
                    Articles articles = new Articles(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getDate(4).toLocalDate(),
                            rs.getDate(5).toLocalDate(),
                            rs.getInt(6),
                            rs.getInt(7),
                            rs.getString(10));
                    //Ajout d'un article dans la liste renvoyée par la fonction
                    liste.add(articles);
                }
            }

            //Si l'utilisateur veut renvoyer tous les objets d'une certaine catégorie
            if (typeObjet == 1){
                //Création du statement utilisé pour la requête SQL
                PreparedStatement stmt = conn.prepareStatement(GET_ALL_ARTICLE_CATEGORIE,PreparedStatement.RETURN_GENERATED_KEYS);

                //Valorisation des paramètres de la requête
                stmt.setInt(1, id);

                //Execution de la requète et stockage des données renvoyées dans un resultset
                ResultSet rs = stmt.executeQuery();

                //Si la catégorie prossède des articles associés
                while (rs.next()){

                    //Crée un article par objet dans la base de donnée
                    Articles articles = new Articles(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getDate(4).toLocalDate(),
                            rs.getDate(5).toLocalDate(),
                            rs.getInt(6),
                            rs.getInt(7),
                            rs.getString(10));
                    //Stock l'article crée dans la liste renvoyée à l'utilisateur
                    liste.add(articles);
                }
            }

            //Si l'utilisateur souhaite renvoyer tous les articles liés à un utilisateur
            if (typeObjet == 2){
                //Création du statement utilisé pour la requête SQL
                PreparedStatement stmt = conn.prepareStatement(GET_ALL_ARTICLE_UTILISATEUR,PreparedStatement.RETURN_GENERATED_KEYS);

                //Valorisation des paramètres de la requête
                stmt.setInt(1, id);

                //Execution de la requète et stockage des données renvoyées dans un resultset
                ResultSet rs = stmt.executeQuery();

                //Si la catégorie prossède des articles associés
                while (rs.next()){

                    //Crée un article par objet dans la base de donnée
                    Articles articles = new Articles(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getDate(4).toLocalDate(),
                            rs.getDate(5).toLocalDate(),
                            rs.getInt(6),
                            rs.getInt(7),
                            rs.getString(10));
                    //Stock l'article crée dans la liste renvoyée à l'utilisateur
                    liste.add(articles);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return liste;
    }


    //Fonction permettant la récupération de tous les enchères en cours au moment de la requète
    public List<Articles> getAllEncheresOuvertes(){

        List<Articles> liste = new ArrayList<Articles>();
        //Tentative de connexion à la base de donnée SQL
        try (Connection conn = ConnectionProvider.getConnection()){

                //Création du statement utilisé pour la requête
                Statement stmt = conn.createStatement();

                //Execution de la requète et stockage des données dans un resulset
                ResultSet rs = stmt.executeQuery(GET_ALL_ARTICLE);

                //Création de la variable permettant de vérifier la date actuelle
                 LocalDate date =  LocalDate.now();

                 //Vérification de l'existence des données dans le resultset
                while (rs.next()){

                    //Création dun objet article en fonction des données récupérées dans le resultset
                    Articles articles = new Articles(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getDate(4).toLocalDate(),
                            rs.getDate(5).toLocalDate(),
                            rs.getInt(6),
                            rs.getInt(7),
                            rs.getString(10));

                    //Vérification que l'enchère n'est pas terminée
                    if ( date.isBefore(articles.getDateFinEncheres())){
                        //Ajout de l'article à la liste renvoyée à l'utilisateur
                        liste.add(articles);
                    }

                }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return liste;
    }

    //Fonction renvoyant une liste contenant tous les objets dans une enchère liée à un utilisateur
    public List<Articles> getAllUtilisateurEnchere(Utilisateur user, Enchere enchere){
        List<Articles> liste = new ArrayList<Articles>();

        //Tentative de connexion à la base de donnée SQL
        try (Connection conn = ConnectionProvider.getConnection()){

            //Création du statement utilisé pour la requête SQL
            Statement stmt = conn.createStatement();

            //Execution du statement et stockage des données dans un resultset
            ResultSet rs = stmt.executeQuery(GET_ALL_ARTICLE);


            //Vérification de l'existence de données dans le resultset
            while (rs.next()){
                //Création d'un article à partir des données récupérées
                Articles articles = new Articles(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4).toLocalDate(),
                        rs.getDate(5).toLocalDate(),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(10));

                //Vérification de l'existence des données
                    if(user.getNoUtilisateur() == enchere.getNoEncherisseur() && enchere.getNoArticle() == articles.getNoArticle()){
                        //ajout de l'article à la liste renvoyée à l'utilisateur
                        liste.add(articles);
                    }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return liste;
    }


    //Fonction renvoyant tous les articles issus d'une enchère remportée par l'utilisateur
    public List<Articles> getAllUtilisateurEnchereRemportees(Utilisateur user, List<Enchere> listeEnchere){
        List<Articles> liste = new ArrayList<Articles>();

        //Tentative de connexion à la base de donnée SQL
        try (Connection conn = ConnectionProvider.getConnection()){

            //Création du statement utilisé pour la requête SQL
            Statement stmt = conn.createStatement();

            //Execution du statement et stockage des données dans un resultset
            ResultSet rs = stmt.executeQuery(GET_ALL_ARTICLE);

            //Création de la variable permettant de vérifier la date actuelle
            LocalDate date =  LocalDate.now();

            //Vérification de l'existence de données dans le resultset
            while (rs.next()){

                //Création d'un article à partir des données récupérées
                Articles articles = new Articles(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4).toLocalDate(),
                        rs.getDate(5).toLocalDate(),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(10));

                //Vérification pour savoir si l'utilisateur est le dernier encherisseur, que l'article correspond bien à l'article encherri et que l'enchère est toujours en cours
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
     * @param user Utilisateur donnant ses paramètres utilisés dans la requète
     * @param mode paramètre utilisé pour déterminer le trype de requète à utiliser
     *             0 renvoie tous les articles liées à une vente en cours de l'utilisateur
     *             1 renvoie tous les articles dont la vente n'a pas encore commencé
     *             2 renvoie tous les articles dont la vente est terminée
     * @return
     */
    public List<Articles> getAllVentesUser(Utilisateur user, int mode){
        List<Articles> liste = new ArrayList<Articles>();

        //Tentative de connexion à la base de donnée SQL
        try (Connection conn = ConnectionProvider.getConnection()){

            //Création du statement utilisé pour la requête SQL
            PreparedStatement stmt = conn.prepareStatement(GET_ALL_ARTICLE_UTILISATEUR,PreparedStatement.RETURN_GENERATED_KEYS);

            //Valorisation des paramètres de la requète SQL
            stmt.setInt(1, user.getNoUtilisateur());

            //Execution du statement et stockage des données dans un resultset
            ResultSet rs = stmt.executeQuery();

            //Création de la variable permettant de vérifier la date actuelle
            LocalDate date =  LocalDate.now();

            //Vérification de l'existence de données dans le resultset
            while (rs.next()){

                //Création d'un article à partir des données récupérées
                Articles articles = new Articles(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4).toLocalDate(),
                        rs.getDate(5).toLocalDate(),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(10));

                //Si l'utilisateur souhaite récupérer les ventes en cours
                if (mode == 0 && date.isBefore(articles.getDateFinEncheres()) && date.isAfter(articles.getDateDebutEncheres())){
                    liste.add(articles);
                }
                //Si l'utilisateur sougaite récupérer les vente non commencées
                else if (mode == 1 && date.isBefore(articles.getDateDebutEncheres())) {
                    liste.add(articles);
                }
                //Si l'utilisateur souhaite récupérer les ventes terminées
                else if (mode == 2 && date.isAfter(articles.getDateFinEncheres())) {
                    liste.add(articles);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return liste;
    }

}
