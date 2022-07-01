package main.java.fr.eni.dal;

import main.java.fr.eni.bo.Retraits;

public interface RetraitsDAO {

    void insert(Retraits retraits);

    Retraits selectById (int id);

    Retraits updateRetraits(Retraits retraits);
}
