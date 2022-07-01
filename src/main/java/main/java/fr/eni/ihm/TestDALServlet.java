package main.java.fr.eni.ihm;

import main.java.fr.eni.bll.BLLException;
import main.java.fr.eni.bo.Articles;
import main.java.fr.eni.bo.Categorie;
import main.java.fr.eni.bo.Utilisateur;
import main.java.fr.eni.dal.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/testDal")
public class TestDALServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        CategorieDAO categorieDAO = DAOFactory.getDaoCategorie();


        Categorie categorie1 = new Categorie("Mobilier");
        Categorie categorie2 = new Categorie("Informatique");
        Categorie categorie3 = new Categorie("Jeux vidéos");
        Categorie categorie4 = new Categorie("Sports");
        Categorie categorie5 = new Categorie("Santé");
        Categorie categorie6 = new Categorie("Culture");
        Categorie categorie7 = new Categorie("Cuisine");
        Categorie categorie8 = new Categorie("Jardin");
        Categorie categorie9 = new Categorie("Véhicule");

        categorieDAO.insertCategorie(categorie1);
        categorieDAO.insertCategorie(categorie2);
        categorieDAO.insertCategorie(categorie3);
        categorieDAO.insertCategorie(categorie4);
        categorieDAO.insertCategorie(categorie5);
        categorieDAO.insertCategorie(categorie6);
        categorieDAO.insertCategorie(categorie7);
        categorieDAO.insertCategorie(categorie8);
        categorieDAO.insertCategorie(categorie9);


        Utilisateur utilisateur = new Utilisateur("JeanJean","Jean","Jean","Jean.jean@Jeanmail.jean","0606060606","Rue Jean Jean", "44444", "JeanVille","GeanGean", 1500,false);
        Utilisateur utilisateur1 = new Utilisateur("VicRam","Ramen","Victor","Victor.ramen@gmail.com","0707070707","Rue Victor Hugo", "55555", "Nantes","1234", 500,false);
        Utilisateur utilisateur2 = new Utilisateur("Jeann","Jeuhuan","Jeryran","Jeaerhyrhn.jean@Jhrehereanmail.jean","060607706","Rue Jean Jean", "46644", "JeanVille","GeanGean", 1000,false);
        Utilisateur utilisateur3 = new Utilisateur("Jen","Jeazdaizadin","Jeaionqfioqzfuioe","Jean.jean@buzuifzeqbfJeanmail.jean","0688860606","Rue Jean Jean", "44664", "JeanVille","GeanGean", 1500,false);
        Utilisateur utilisateur4 = new Utilisateur("JeanJbfiqbn","an","Jqfefq","Jean.jean@Jeaazfazfl.jean","0606060606","Rue Jean Jean", "44994", "JeanVille","GeanGean", 1500,false);
        UtilisateursDAO    utilisateursDAO = DAOFactory.getDaoUtilisateurs();

        Utilisateur user = null;
        try {
            utilisateursDAO.insert(utilisateur);
            utilisateursDAO.insert(utilisateur1);
            utilisateursDAO.insert(utilisateur2);
            utilisateursDAO.insert(utilisateur3);
            utilisateursDAO.insert(utilisateur4);


        } catch (BLLException e) {
            throw new RuntimeException(e);
        }


        ArticlesDAO articlesDAO = DAOFactory.getDaoArticles();

        articlesDAO.getAllArticles(0,1);

        Articles articles = new Articles("Vélo Bleu","Vélo de couleur bleu, bon état", LocalDate.now(), LocalDate.now().plusDays(3),200,200,false,"https://cdn.apesanteur.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/v/e/velo-bleu-24-pouces-bobbin-gingersnap-2349-2020-01.jpg");
        Articles articles2 = new Articles("Armoire Rouge","Armoire rouge, état discutable", LocalDate.now(), LocalDate.now().plusDays(3),2500,2500,false,"https://www.luckyfind.fr/sites/luckyfind/files/classifieds/006900-56be00adac348-56bdffe825a7d-image.jpeg");
        Articles articles3 = new Articles("Ordinateur Portable","I7, 1660 ti, 250go SSD", LocalDate.now(), LocalDate.now().plusDays(3),1000,1500,true,"https://media.paruvendu.fr/image/portable/WB16/0/9/WB160991150_1.jpeg");
        Articles articles4 = new Articles("Playstation 6","Exclusivité mondiale", LocalDate.now(), LocalDate.now().plusDays(3),20000,150000,true,"https://marketresearchtelecast.com/wp-content/uploads/2022/04/image-8-1160x676.jpeg");
        Articles articles5 = new Articles("Huiles essentielles périmées","Elle trainent dans mon placard depuis 6 ans", LocalDate.now(), LocalDate.now().plusDays(3),15,15,false,"https://resize.prod.docfr.doc-media.fr/rcrop/1200,902,center-middle/img/var/doctissimo/storage/images/fr/www/sante/diaporamas/huiles-essentielles-indispensables-dans-votre-pharmacie/3082490-1-fre-FR/10-huiles-essentielles-indispensables-dans-votre-pharmacie.jpg");
        Articles articles6 = new Articles("1984","Livre de lycée de mon fils", LocalDate.now(), LocalDate.now().plusDays(3),50,50,false,"https://images-na.ssl-images-amazon.com/images/I/51sdWhtHOwL.jpg");
        Articles articles7 = new Articles("Couteau de cuisine japonais","Hérité de mon père", LocalDate.now(), LocalDate.now().plusDays(3),300,650,true,"https://www.sabatier-k.com/cache/images/product/6d1696bee7835dd96f75f90fc20b01bf-334.jpg");
        Articles articles8 = new Articles("Sécateur","Lorem Ipsum", LocalDate.now(), LocalDate.now().plusDays(3),600,600,false,"https://upload.wikimedia.org/wikipedia/commons/b/b1/Secateur_ouvert.jpg");
        Articles articles9 = new Articles("Fiat Multipla","LE BOLIDE", LocalDate.now(), LocalDate.now().plusDays(3),15000,15000,false,"https://images.caradisiac.com/logos/0/4/0/7/260407/S8-fiat-multipla-1998-2010-un-ovni-genial-des-1-500-eur-183228.jpg");

        try {
            articlesDAO.insertArticles(articles,utilisateursDAO.selectById(1),categorieDAO.selectById(4));
            articlesDAO.insertArticles(articles2,utilisateursDAO.selectById(3),categorieDAO.selectById(1));
            articlesDAO.insertArticles(articles3,utilisateursDAO.selectById(2),categorieDAO.selectById(2));
            articlesDAO.insertArticles(articles4,utilisateursDAO.selectById(1),categorieDAO.selectById(3));
            articlesDAO.insertArticles(articles5,utilisateursDAO.selectById(5),categorieDAO.selectById(5));
            articlesDAO.insertArticles(articles6,utilisateursDAO.selectById(4),categorieDAO.selectById(6));
            articlesDAO.insertArticles(articles7,utilisateursDAO.selectById(3),categorieDAO.selectById(7));
            articlesDAO.insertArticles(articles8,utilisateursDAO.selectById(5),categorieDAO.selectById(8));
            articlesDAO.insertArticles(articles9,utilisateursDAO.selectById(1),categorieDAO.selectById(9));
            

        } catch (DALException e) {
            throw new RuntimeException(e);
        } catch (BLLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
