package main.java.fr.eni.dal;

import main.java.fr.eni.bo.Categorie;

public interface CategorieDAO {

    void insertCategorie(Categorie categorie);

    void deleteCategorie(Categorie categorie);

    void updateCategorie(Categorie categorie);

    Categorie selectById(int id);

}
