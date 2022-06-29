package main.java.fr.eni.bll;

import main.java.fr.eni.bo.Categorie;

public interface ManagerCategorie {

    void ajouterCategorie(Categorie categorie);

    void supprimerCategorie(Categorie categorie);

    void updateCategorie(Categorie categorie);

    Categorie selectById(int id);
}
